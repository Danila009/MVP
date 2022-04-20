package com.example.mvp.di

import android.content.Context
import com.example.mvp.presenter.NoteDatabasePresenterImpl
import com.example.mvp.presenter.UserDatabasePresenterImpl
import dagger.BindsInstance
import dagger.Component
import javax.inject.Singleton

@Component(
    modules = [
        DatabaseModule::class
    ]
)
@Singleton
interface AppComponent{
    fun userDatabasePresenter(): UserDatabasePresenterImpl
    fun noteDatabasePresenter(): NoteDatabasePresenterImpl

    @Component.Builder
    interface Builder{

        @BindsInstance
        fun context(context:Context):Builder

        fun build():AppComponent
    }
}