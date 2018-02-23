package io.github.muninn.presentation.home.todo.create

import android.widget.EditText
import io.github.muninn.model.DateTimeTemporaryChoice
import io.github.muninn.presentation.base.Presentable
import java.util.*

/**
 * Created by viacheslavokolitiy on 19.02.2018.
 */
interface CreateTodoDialogContract {
    interface View {
        /**
         * Shows dialog button as enabled
         */
        fun enableDialogButton()

        /**
         * Shows dialog button as disabled
         */
        fun disableDialogButton()

        /**
         * Shows start date
         */
        fun showStartDate(choice: DateTimeTemporaryChoice)

        /**
         * Shows task due date
         */
        fun showDueDate(dueDateChoice: DateTimeTemporaryChoice)
    }

    interface Presenter : Presentable<View> {
        fun prepareCalendar(): Calendar
        fun saveTemporaryStartDateChoice(year: Int, monthOfYear: Int, dayOfMonth: Int)
        fun saveTemporaryDueDateChoice(year: Int, monthOfYear: Int, dayOfMonth: Int)
        fun handleTextChanges(editTitle: EditText?, editDescription: EditText?, editStartDate: EditText, dueDate: EditText)
        fun setCalendarFromChoice(calendar: Calendar, month: Int): Calendar
    }
}