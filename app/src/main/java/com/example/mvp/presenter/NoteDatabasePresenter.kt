package com.example.mvp.presenter

import com.example.mvp.data.entities.Note
import com.example.mvp.data.entities.relations.NoteAndUser
import kotlinx.coroutines.flow.Flow

interface NoteDatabasePresenter {
   suspend fun insertNote(note:Note)
    fun realmAllNote():Flow<List<Note>>
    fun realmAllNoteUser(idUser:Int):Flow<NoteAndUser>
}