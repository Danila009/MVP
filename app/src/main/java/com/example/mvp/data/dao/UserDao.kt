package com.example.mvp.data.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.mvp.data.entities.User
import kotlinx.coroutines.flow.Flow

@Dao
interface UserDao {

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insertUser(user: User)

    @Query("SELECT * FROM users ORDER BY user_name")
    fun realmAllUser():Flow<List<User>>

    @Query("SELECT * FROM users WHERE user_email = :email")
    fun realmUser(email:String):Flow<User?>
}