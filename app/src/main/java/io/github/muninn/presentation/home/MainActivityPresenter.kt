package io.github.muninn.presentation.home

import io.github.muninn.persistence.dao.TodoDao
import io.github.muninn.presentation.base.BasePresenter
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers

/**
 * Created by viacheslavokolitiy on 12.02.2018.
 */
class MainActivityPresenter(private val todoDao: TodoDao)
    : BasePresenter<MainActivityContract.View>(), MainActivityContract.Presenter {

    override fun makeCreateTodoDialog() {
        view!!.showCreateTodoDialogFragment()
    }

    override fun searchTodos() {
        todoDao.queryForTodos()
                .doOnComplete({ view!!.showPlaceholder() })
                .doOnSuccess({ loadTodos() })
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe()
    }

    private fun loadTodos() {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }
}