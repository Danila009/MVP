package com.example.mvp.di

import android.content.Context
import androidx.room.Room
import com.example.mvp.data.dao.UserDao
import com.example.mvp.data.UserDatabase
import com.example.mvp.data.dao.NoteDao
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class DatabaseModule {

    @Provides
    @Singleton
    fun providerUserDatabase(
        context: Context
    ):UserDatabase = Room.databaseBuilder(
        context.applicationContext,
        UserDatabase::class.java,
        "user_database"
    )
        .build()

    @Provides
    @Singleton
    fun providerUserDao(
        userDatabase: UserDatabase
    ): UserDao = userDatabase.userDao()

    @Provides
    @Singleton
    fun providerNoteDao(
        userDatabase: UserDatabase
    ): NoteDao = userDatabase.noteDao()
}