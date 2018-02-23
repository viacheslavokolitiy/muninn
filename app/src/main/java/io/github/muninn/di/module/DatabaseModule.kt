package io.github.muninn.di.module

import android.app.Application
import dagger.Module
import dagger.Provides
import io.github.muninn.persistence.AppDatabase
import io.github.muninn.persistence.dao.TodoDao
import javax.inject.Singleton

/**
 * Created by viacheslavokolitiy on 12.02.2018.
 */
@Module
class DatabaseModule {

    @Provides
    @Singleton
    fun provideAppDatabase(app: Application): AppDatabase = AppDatabase.getAppDatabase(app.applicationContext, "muninn")

    @Provides
    @Singleton
    fun provideTodoDao(database: AppDatabase): TodoDao = database.todoDao()
}