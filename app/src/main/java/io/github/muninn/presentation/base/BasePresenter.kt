package io.github.muninn.presentation.base

/**
 * Created by viacheslavokolitiy on 12.02.2018.
 */
abstract class BasePresenter<V> : Presentable<V> {
    protected var view: V? = null

    override fun onCreate(view: V) {
        this.view = view
    }

    override fun onDestroy() {
        this.view = null
    }
}