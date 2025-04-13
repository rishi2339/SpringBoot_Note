package com.springBoot.note_app.database.repository

import com.springBoot.note_app.database.model.Note
import org.bson.types.ObjectId
import org.springframework.data.mongodb.repository.MongoRepository

interface NoteRepository : MongoRepository<Note,ObjectId>{
    fun findByOwnerId(ownerId : ObjectId): List<Note>
}