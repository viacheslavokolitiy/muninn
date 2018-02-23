package io.github.muninn

import android.os.Bundle
import android.support.design.widget.FloatingActionButton
import android.support.v7.app.AppCompatActivity
import android.view.View
import android.widget.TextView
import dagger.android.AndroidInjection
import io.github.muninn.presentation.home.MainActivityContract
import io.github.muninn.presentation.home.MainActivityPresenter
import io.github.muninn.presentation.home.todo.create.CreateTodoDialogFragment
import javax.inject.Inject

class MainActivity : AppCompatActivity(), MainActivityContract.View {
    @Inject
    lateinit var mainActivityPresenter: MainActivityPresenter
    lateinit var buttonAddTodo: FloatingActionButton

    override fun onCreate(savedInstanceState: Bundle?) {
        AndroidInjection.inject(this)
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        buttonAddTodo = findViewById(R.id.fab_add_todo)
        buttonAddTodo.setOnClickListener(View.OnClickListener { v -> mainActivityPresenter.makeCreateTodoDialog() })
        mainActivityPresenter.onCreate(this)
        mainActivityPresenter.searchTodos()
    }

    override fun showPlaceholder() {
        findViewById<TextView>(R.id.placeholder).apply { visibility = View.VISIBLE }
    }

    override fun showCreateTodoDialogFragment() {
        val dialog = CreateTodoDialogFragment.newInstance()
        dialog.show(fragmentManager, "dialog")
    }
}
