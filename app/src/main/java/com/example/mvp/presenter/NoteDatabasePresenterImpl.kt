package com.example.mvp.presenter

import com.example.mvp.data.entities.Note
import com.example.mvp.data.entities.relations.NoteAndUser
import com.example.mvp.data.repository.NoteDatabaseRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class NoteDatabasePresenterImpl @Inject constructor(
    private val noteDatabaseRepository: NoteDatabaseRepository
):NoteDatabasePresenter {
    override suspend fun insertNote(note: Note) {
        noteDatabaseRepository.insertNote(note)
    }

    override fun realmAllNote(): Flow<List<Note>> {
        TODO("Not yet implemented")
    }

    override fun realmAllNoteUser(idUser:Int): Flow<NoteAndUser> {
        return noteDatabaseRepository.realmAllNoteUser(idUser = idUser)
    }
}