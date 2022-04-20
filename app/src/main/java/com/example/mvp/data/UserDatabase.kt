package com.example.mvp.data

import androidx.room.AutoMigration
import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.mvp.data.dao.NoteDao
import com.example.mvp.data.dao.UserDao
import com.example.mvp.data.entities.Note
import com.example.mvp.data.entities.User
import com.example.mvp.data.migration.AutoMigrationSpec1To2

@Database(
    entities = [
        User::class,
        Note::class
    ],
    version = 3,
    autoMigrations = [
        AutoMigration(
            from = 1,
            to = 2,
            spec = AutoMigrationSpec1To2::class
        ),
        AutoMigration(
            from = 2,
            to = 3
        )
    ]
)
abstract class UserDatabase:RoomDatabase() {
    abstract fun userDao(): UserDao
    abstract fun noteDao(): NoteDao
}