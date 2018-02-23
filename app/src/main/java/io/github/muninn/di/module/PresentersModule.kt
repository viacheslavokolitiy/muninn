package io.github.muninn.di.module

import dagger.Module
import dagger.Provides
import io.github.muninn.persistence.dao.TodoDao
import io.github.muninn.presentation.home.MainActivityPresenter
import io.github.muninn.presentation.home.todo.create.CreateTodoDialogFragment
import io.github.muninn.presentation.home.todo.create.CreateTodoDialogPresenter
import javax.inject.Singleton

/**
 * Created by viacheslavokolitiy on 12.02.2018.
 */
@Module
class PresentersModule {

    @Provides
    @Singleton
    fun provideMainActivityPresenter(todoDao: TodoDao): MainActivityPresenter {
        return MainActivityPresenter(todoDao)
    }

    @Provides
    @Singleton
    fun provideCreateTodoDialogPresenter(): CreateTodoDialogPresenter = CreateTodoDialogPresenter()
}