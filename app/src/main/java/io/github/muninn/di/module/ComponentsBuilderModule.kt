package io.github.muninn.di.module

import android.app.Activity
import android.app.Fragment

import dagger.Binds
import dagger.Module
import dagger.android.ActivityKey
import dagger.android.AndroidInjector
import dagger.android.FragmentKey
import dagger.multibindings.IntoMap
import io.github.muninn.MainActivity
import io.github.muninn.di.component.CreateTodoDialogComponent
import io.github.muninn.di.component.MainActivityComponent
import io.github.muninn.presentation.home.todo.create.CreateTodoDialogFragment

/**
 * Created by viacheslavokolitiy on 02.12.2017.
 */
@Module
abstract class ComponentsBuilderModule {
    @Binds
    @IntoMap
    @ActivityKey(MainActivity::class)
    internal abstract fun bindMainActivity(builder: MainActivityComponent.Builder): AndroidInjector.Factory<out Activity>

    @Binds
    @IntoMap
    @FragmentKey(CreateTodoDialogFragment::class)
    internal abstract fun bindCreateTodoDialogFragment(builder: CreateTodoDialogComponent.Builder): AndroidInjector.Factory<out Fragment>
}
