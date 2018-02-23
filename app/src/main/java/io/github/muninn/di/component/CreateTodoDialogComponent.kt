package io.github.muninn.di.component

import dagger.Subcomponent
import dagger.android.AndroidInjector
import io.github.muninn.di.module.CreateTodoDialogModule
import io.github.muninn.presentation.home.todo.create.CreateTodoDialogFragment

/**
 * Created by viacheslavokolitiy on 19.02.2018.
 */
@Subcomponent(modules = arrayOf(CreateTodoDialogModule::class))
interface CreateTodoDialogComponent : AndroidInjector<CreateTodoDialogFragment>{
    @Subcomponent.Builder
    abstract class Builder : AndroidInjector.Builder<CreateTodoDialogFragment>()
}