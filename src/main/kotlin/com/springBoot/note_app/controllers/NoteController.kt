package com.springBoot.note_app.controllers

import com.springBoot.note_app.controllers.NoteController.NoteResponse
import com.springBoot.note_app.database.model.Note
import com.springBoot.note_app.database.repository.NoteRepository
import jakarta.validation.Valid
import jakarta.validation.constraints.NotBlank
import org.bson.types.ObjectId
import org.springframework.security.core.context.SecurityContext
import org.springframework.security.core.context.SecurityContextHolder
import org.springframework.web.bind.annotation.*
import java.time.Instant

@RestController
@RequestMapping("/notes")
class NoteController(
    private val repository: NoteRepository
) {

    data class NoteRequest(
        val id : String?,
        @field:NotBlank(message = "Title can't be blank.")
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
        @Valid @RequestBody noteRequest: NoteRequest
    ) : NoteResponse{
        val ownerId = SecurityContextHolder.getContext().authentication.principal as String
        val note = repository.save(
            Note(
                id = noteRequest.id?.let { ObjectId(it) } ?: ObjectId.get(),
                title = noteRequest.title,
                content = noteRequest.content,
                color = noteRequest.color,
                createdAt = Instant.now(),
                ownerId = ObjectId(ownerId)
            )
        )
        return note.toNoteResponse()
    }

    @GetMapping
    fun findByOwnerId(): List<NoteResponse> {
        val ownerId = SecurityContextHolder.getContext().authentication.principal as String
        return repository.findByOwnerId(ObjectId(ownerId)).map {
            it.toNoteResponse()
        }
    }

    @DeleteMapping(path = ["/{id}"])
    fun deleteById(@PathVariable id:String){
        val note = repository.findById(ObjectId(id)).orElseThrow{
            IllegalArgumentException("Note not found")
        }
        val ownerId = SecurityContextHolder.getContext().authentication.principal as String
        if(note.ownerId.toHexString() == ownerId) {
            repository.deleteById(ObjectId(id))
        }
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
