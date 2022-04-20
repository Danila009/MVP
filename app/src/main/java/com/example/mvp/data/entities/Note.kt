package com.example.mvp.data.entities

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(
    tableName = "notes"
)
data class Note(
    @PrimaryKey(autoGenerate = true) val id:Int,
    @ColumnInfo(name = "user_id") val idUser:Int,
    @ColumnInfo(name = "note_title") val title:String,
    @ColumnInfo(name = "note_description") val description:String,
    @ColumnInfo(name = "note_date") val date:String
)