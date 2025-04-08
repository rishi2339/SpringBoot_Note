package com.springBoot.note_app.controllers

import com.springBoot.note_app.controllers.NoteController.NoteResponse
import com.springBoot.note_app.database.model.Note
import com.springBoot.note_app.database.repository.NoteRepository
import org.bson.types.ObjectId
import org.springframework.web.bind.annotation.*
import java.time.Instant

@RestController
@RequestMapping("/notes")
class NoteController(
    private val repository: NoteRepository
) {

    data class NoteRequest(
        val id : String?,
        val title : String,
        val content : String,
        val color : Long
    )

    data class NoteResponse(
        val id : String,
        val title : String,
        val content : String,
        val color : Long,
        val createdAt : Instant
    )

    @PostMapping
    fun save(
        @RequestBody noteRequest: NoteRequest
    ) : NoteResponse{
        val note = repository.save(
            Note(
                id = noteRequest.id?.let { ObjectId(it) } ?: ObjectId.get(),
                title = noteRequest.title,
                content = noteRequest.content,
                color = noteRequest.color,
                createdAt = Instant.now(),
                ownerId = ObjectId()
            )
        )
        return note.toNoteResponse()
    }

    @GetMapping
    fun findByOwnerId(
        @RequestParam(required = true) ownerId: String
    ): List<NoteResponse> {
        return repository.findByOwnerId(ObjectId(ownerId)).map {
            it.toNoteResponse()
        }
    }

    @DeleteMapping(path = ["/{id}"])
    fun deleteById(@PathVariable id:String){
        repository.deleteById(ObjectId(id))
    }

}

private fun Note.toNoteResponse():NoteController.NoteResponse{
    return NoteResponse(
        id = id.toHexString(),
        title = title,
        content = content,
        color = color,
        createdAt = createdAt
    )
}
