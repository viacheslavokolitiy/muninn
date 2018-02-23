package io.github.muninn.persistence

import android.arch.persistence.room.Database
import android.arch.persistence.room.Room
import android.arch.persistence.room.RoomDatabase
import android.content.Context
import io.github.muninn.persistence.dao.TodoDao

/**
 * Created by viacheslavokolitiy on 12.02.2018.
 */
@Database(entities = arrayOf(Todo::class), version = 4)
abstract class AppDatabase : RoomDatabase() {
    abstract fun todoDao(): TodoDao

    companion object {
        private var database: AppDatabase? = null
        private val lock = Object()

        fun getAppDatabase(ctx: Context, dbName: String): AppDatabase {
            if(database == null){
                synchronized(lock){
                    if(database == null){
                        val builder = Room.databaseBuilder(ctx, AppDatabase::class.java, dbName)
                        database = with(builder) {
                            allowMainThreadQueries()
                            build()
                        }
                    }
                }
            }

            return database!!
        }
    }
}