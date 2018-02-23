package io.github.muninn.di.component

import android.app.Application

import dagger.BindsInstance
import dagger.Component
import dagger.android.AndroidInjectionModule
import io.github.muninn.App
import io.github.muninn.di.module.*
import javax.inject.Singleton

/**
 * Created by viacheslavokolitiy on 02.12.2017.
 */
@Component(modules = arrayOf(AndroidInjectionModule::class, AppModule::class, DatabaseModule::class,
        PresentersModule::class, RxModule::class, ComponentsBuilderModule::class))
@Singleton
interface AppComponent {
    @Component.Builder
    interface Builder {
        @BindsInstance
        fun application(application: Application): Builder

        fun build(): AppComponent
    }

    fun inject(app: App)
}
