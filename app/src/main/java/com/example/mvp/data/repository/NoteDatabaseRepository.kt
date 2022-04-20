package com.example.mvp.data.repository

import com.example.mvp.data.dao.NoteDao
import com.example.mvp.data.entities.Note
import com.example.mvp.data.entities.relations.NoteAndUser
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class NoteDatabaseRepository @Inject constructor(
    private val noteDao: NoteDao
){
    suspend fun insertNote(note: Note) {
        noteDao.insertNote(note)
    }

    fun realmAllNote(): Flow<List<Note>> {
        return noteDao.realmAllNote()
    }

    fun realmAllNoteUser(idUser: Int):Flow<NoteAndUser>{
        return flow {  }
    }
}