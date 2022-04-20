package com.example.mvp.data.repository

import com.example.mvp.data.dao.UserDao
import com.example.mvp.data.entities.User
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class UserDatabaseRepository @Inject constructor(
    private val userDao: UserDao
) {

    val realmAllUser = userDao.realmAllUser()

    suspend fun insertUser(user: User){
        userDao.insertUser(user)
    }

    fun realmUser(email:String): Flow<User?> {
        return userDao.realmUser(email)
    }
}