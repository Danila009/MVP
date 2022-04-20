package com.example.mvp.data.dao

import androidx.room.*
import com.example.mvp.data.entities.Note
import com.example.mvp.data.entities.relations.NoteAndUser
import kotlinx.coroutines.flow.Flow
import org.jetbrains.annotations.NotNull

@Dao
interface NoteDao {
    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insertNote(note: Note)

    @Query("SELECT * FROM notes ORDER BY note_title")
    fun realmAllNote():Flow<List<Note>>

//    @Transaction
//    @Query("SELECT * FROM notes WHERE id = :idUser")
//    fun realmAllNoteUser(idUser: Int):Flow<NoteAndUser>
}