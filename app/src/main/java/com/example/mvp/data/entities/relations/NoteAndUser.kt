package com.example.mvp.data.entities.relations

import androidx.room.Embedded
import androidx.room.Relation
import com.example.mvp.data.entities.Note
import com.example.mvp.data.entities.User

data class NoteAndUser(
    @Embedded val user: User,
    @Relation(
        parentColumn = "id",
        entityColumn = "user_id"
    ) val notes:List<Note>
)