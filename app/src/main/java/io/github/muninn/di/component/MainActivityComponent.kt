package io.github.muninn.di.component


import dagger.Subcomponent
import dagger.android.AndroidInjector
import io.github.muninn.MainActivity
import io.github.muninn.di.module.MainActivityModule

/**
 * Created by viacheslavokolitiy on 02.12.2017.
 */
@Subcomponent(modules = arrayOf(MainActivityModule::class))
interface MainActivityComponent : AndroidInjector<MainActivity> {
    @Subcomponent.Builder
    abstract class Builder : AndroidInjector.Builder<MainActivity>()
}
