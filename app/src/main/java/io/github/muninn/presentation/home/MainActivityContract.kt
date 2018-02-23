package io.github.muninn.presentation.home

/**
 * Created by viacheslavokolitiy on 12.02.2018.
 */
interface MainActivityContract {
    interface View {
        /**
         * Shows placeholder about no to-do entries
         */
        fun showPlaceholder()

        /**
         * Shows create to-do dialog fragment
         */
        fun showCreateTodoDialogFragment()
    }

    interface Presenter {
        /**
         * Searches for todos
         */
        fun searchTodos()

        /**
         * Makes create todos dialog
         */
        fun makeCreateTodoDialog()
    }
}