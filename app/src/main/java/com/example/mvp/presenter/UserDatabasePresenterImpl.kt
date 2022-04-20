package com.example.mvp.presenter

import androidx.navigation.NavController
import com.example.mvp.data.entities.User
import com.example.mvp.data.repository.UserDatabaseRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.onEach
import javax.inject.Inject

class UserDatabasePresenterImpl @Inject constructor(
    private val userDatabaseRepository: UserDatabaseRepository
):UserDatabasePresenter {

    override val readAllUser = userDatabaseRepository.realmAllUser

    override suspend fun insertUser(
        user: User,
        navController: NavController
    ) {
        userDatabaseRepository.realmUser(user.email).onEach { responseUser ->
            if (responseUser == null){
                userDatabaseRepository.insertUser(user)
            }
            USER_EMAIL = user.email
            navController.navigate("notes_screen")
        }.collect()
    }

    suspend fun userIdByEmail():Int?{
        var idUser:Int? = null
        userDatabaseRepository.realmUser(USER_EMAIL).onEach {
            idUser = it?.id
        }.collect()
        return idUser
    }

    companion object{
        var USER_EMAIL = ""
    }
}