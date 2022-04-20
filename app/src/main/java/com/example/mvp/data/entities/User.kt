package com.example.mvp.data.entities

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.Index
import androidx.room.PrimaryKey

@Entity(
    tableName = "users",
    indices = [
        Index("user_email", unique = true)
    ]
)
data class User(
    @PrimaryKey(autoGenerate = true) val id:Int,
    @ColumnInfo(name = "user_name") val username:String,
    @ColumnInfo(name = "user_age") val age:Int,
    @ColumnInfo(name = "user_email", collate = ColumnInfo.NOCASE) val email:String,
)