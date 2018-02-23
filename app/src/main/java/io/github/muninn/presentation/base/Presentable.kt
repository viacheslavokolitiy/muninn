package io.github.muninn.presentation.base

/**
 * Created by viacheslavokolitiy on 12.02.2018.
 */
interface Presentable<in V> {
    fun onCreate(view: V)
    fun onDestroy()
}