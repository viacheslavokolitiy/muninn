package io.github.muninn.persistence.dao

import android.arch.persistence.room.Dao
import android.arch.persistence.room.Query
import io.github.muninn.persistence.Todo
import io.reactivex.Flowable
import io.reactivex.Maybe

/**
 * Created by viacheslavokolitiy on 16.02.2018.
 */
@Dao
interface TodoDao {
    @Query("SELECT * FROM Todo")
    fun queryForTodos(): Maybe<Todo>

    @Query("SELECT * FROM Todo")
    fun loadTodos(): Flowable<Todo>
}