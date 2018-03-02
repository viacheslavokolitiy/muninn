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
         * @param choice [DateTimeTemporaryChoice]
         */
        fun showStartDate(choice: DateTimeTemporaryChoice)

        /**
         * Shows task due date
         * @param dueDateChoice [DateTimeTemporaryChoice]
         */
        fun showDueDate(dueDateChoice: DateTimeTemporaryChoice)
    }

    interface Presenter : Presentable<View> {
        /**
         * Inits calendar
         * @return [Calendar]
         */
        fun prepareCalendar(): Calendar

        /**
         * Sets calendar's minimum date
         * @return [Calendar]
         */
        fun setCalendarDefaultMinDate(): Calendar

        /**
         * Saves start date choice
         * @param year [Calendar.YEAR]
         * @param monthOfYear [Calendar.MONTH]
         * @param dayOfMonth [Calendar.DAY_OF_MONTH]
         */
        fun saveStartDateChoice(year: Int, monthOfYear: Int, dayOfMonth: Int)

        /**
         * Saves due date choice
         * @param year [Calendar.YEAR]
         * @param monthOfYear [Calendar.MONTH]
         * @param dayOfMonth [Calendar.DAY_OF_MONTH]
         */
        fun saveDueDateChoice(year: Int, monthOfYear: Int, dayOfMonth: Int)

        /**
         * Handles edit text text changes
         * @param editTitle edit text to-do title
         * @param editDescription to-do description
         * @param editStartDate to-do start date
         * @param dueDate to-dp due date
         */
        fun handleTextChanges(editTitle: EditText?, editDescription: EditText?, editStartDate: EditText, dueDate: EditText)

        /**
         * Sets calendar based on choice
         * @param calendar [Calendar]
         * @param month month of year
         */
        fun setCalendarFromChoice(calendar: Calendar, month: Int): Calendar
    }
}