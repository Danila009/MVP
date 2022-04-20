package com.example.mvp

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.OutlinedButton
import androidx.compose.material.OutlinedTextField
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalLifecycleOwner
import androidx.compose.ui.unit.dp
import androidx.lifecycle.LifecycleCoroutineScope
import androidx.lifecycle.lifecycleScope
import androidx.navigation.NavController
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.mvp.data.entities.Note
import com.example.mvp.data.entities.User
import com.example.mvp.di.AppComponent
import com.example.mvp.di.DaggerAppComponent
import com.example.mvp.presenter.NoteDatabasePresenterImpl
import com.example.mvp.presenter.UserDatabasePresenterImpl
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.onEach

class MainActivity : ComponentActivity() {

    lateinit var appComponent:AppComponent
    lateinit var navController: NavHostController

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        appComponent = DaggerAppComponent.builder()
            .context(this)
            .build()
        setContent {
            navController = rememberNavController()

            NavHost(
                navController = navController,
                startDestination = "user_screen",
                builder = {
                    composable(
                        route = "user_screen",
                        content = {
                            UserScreen(
                                navController = navController,
                                userDatabasePresenterImpl = appComponent.userDatabasePresenter()
                            )
                        }
                    )
                    composable(
                        route = "notes_screen",
                        content = {
                            NoteScreen(
                                noteDatabasePresenterImpl = appComponent.noteDatabasePresenter(),
                                userDatabasePresenterImpl = appComponent.userDatabasePresenter()
                            )
                        }
                    )
                }
            )
        }
    }
}


@Composable
private fun UserScreen(
    lifecycleScope: LifecycleCoroutineScope = LocalLifecycleOwner.current.lifecycleScope,
    navController: NavController,
    userDatabasePresenterImpl: UserDatabasePresenterImpl
) {
    val email = remember { mutableStateOf("") }
    val username = remember { mutableStateOf("") }
    val age = remember { mutableStateOf("") }

    Column(
        modifier = Modifier.fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        BaseTextField(
            value = username,
            title = "Username"
        )

        BaseTextField(
            value = email,
            title = "Email"
        )

        BaseTextField(
            value = age,
            title = "Age"
        )

        OutlinedButton(
            modifier = Modifier.padding(5.dp),
            onClick = {
                lifecycleScope.launchWhenStarted {
                    userDatabasePresenterImpl.insertUser(
                        user = User(
                            id = 0,
                            username = username.value,
                            email = email.value,
                            age = age.value.toInt()
                        ),
                        navController = navController
                    )
                }
            }
        ) {
            Text(text = "Add user")
        }
    }
}

@Composable
private fun BaseTextField(
    value:MutableState<String>,
    title:String
) {
    OutlinedTextField(
        modifier = Modifier
            .padding(5.dp),
        value = value.value,
        onValueChange = {
            value.value = it
        }, label = {
            Text(text = title)
        }
    )
}

@Composable
fun NoteScreen(
    lifecycleScope: LifecycleCoroutineScope = LocalLifecycleOwner.current.lifecycleScope,
    noteDatabasePresenterImpl: NoteDatabasePresenterImpl,
    userDatabasePresenterImpl: UserDatabasePresenterImpl
) {
    var notes by remember { mutableStateOf(listOf<Note>()) }

    var idUser = 0

    lifecycleScope.launchWhenStarted {
        idUser = userDatabasePresenterImpl.userIdByEmail() ?: 0
        repeat(10){
            noteDatabasePresenterImpl.insertNote(
                Note(
                    idUser = idUser,
                    id = 0,
                    title = "title_$it",
                    description = "description_$it",
                    date = "$it-$it-$it"
                )
            )
        }
        noteDatabasePresenterImpl.realmAllNoteUser(idUser).onEach {
            notes = it.notes
        }.collect()
    }

    LazyColumn(content = {
        items(notes){ item ->
            Text(
                text = item.title,
                modifier = Modifier.padding(5.dp)
            )
        }
    })
}