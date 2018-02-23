package io.github.muninn.di.module

import android.app.Application
import android.content.Context

import javax.inject.Singleton

import dagger.Module
import dagger.Provides
import io.github.muninn.di.component.CreateTodoDialogComponent
import io.github.muninn.di.component.MainActivityComponent

/**
 * Created by viacheslavokolitiy on 02.12.2017.
 */
@Module(subcomponents = arrayOf(MainActivityComponent::class,
        CreateTodoDialogComponent::class))
class AppModule {
    @Provides
    @Singleton
    fun context(application: Application): Context {
        return application.applicationContext
    }
}
