package com.example.mvp.presenter

import androidx.navigation.NavController
import com.example.mvp.data.entities.User
import kotlinx.coroutines.flow.Flow

interface UserDatabasePresenter {
    suspend fun insertUser(user: User,navController: NavController)
    val readAllUser:Flow<List<User>>
}