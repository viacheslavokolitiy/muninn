package io.github.muninn.presentation.home.todo.create

import android.text.TextUtils
import android.widget.EditText
import com.jakewharton.rxbinding2.widget.RxTextView
import io.github.muninn.model.DateTimeTemporaryChoice
import io.github.muninn.presentation.base.BasePresenter
import io.reactivex.Observable
import io.reactivex.functions.Function4
import java.util.*

/**
 * Created by viacheslavokolitiy on 19.02.2018.
 */

class CreateTodoDialogPresenter : BasePresenter<CreateTodoDialogContract.View>(),
        CreateTodoDialogContract.Presenter {

    private var choice: DateTimeTemporaryChoice? = null
    private var dueDateChoice: DateTimeTemporaryChoice? = null
    private var _minDate: Calendar? = null
    var minDate: Calendar?
        get() = _minDate
        set(value) {
            _minDate = value
        }
    private var _maxDate: Calendar? = null
    var maxDate: Calendar?
        get() = _maxDate
        set(value) {
            _maxDate = value
        }

    override fun prepareCalendar(): Calendar = Calendar.getInstance()
    override fun setCalendarDefaultMinDate(): Calendar = prepareCalendar()

    override fun saveStartDateChoice(year: Int, monthOfYear: Int, dayOfMonth: Int) {
        choice = DateTimeTemporaryChoice(year, monthOfYear, dayOfMonth)
        minDate = updateCalendar(year, monthOfYear, dayOfMonth)
        view!!.showStartDate(choice!!)
    }

    private fun updateCalendar(year: Int, monthOfYear: Int, dayOfMonth: Int): Calendar {
        val calendar = prepareCalendar()
        calendar.apply {
            set(Calendar.YEAR, year)
            set(Calendar.MONTH, monthOfYear)
            set(Calendar.DAY_OF_MONTH, dayOfMonth)
        }

        return calendar
    }

    override fun saveDueDateChoice(year: Int, monthOfYear: Int, dayOfMonth: Int) {
        dueDateChoice = DateTimeTemporaryChoice(year, monthOfYear, dayOfMonth)
        maxDate = updateCalendar(year, monthOfYear, dayOfMonth)
        view!!.showDueDate(dueDateChoice!!)
    }

    override fun handleTextChanges(editTitle: EditText?, editDescription: EditText?, editStartDate: EditText, dueDate: EditText) {
        val editTitleObservable = RxTextView.textChanges(editTitle!!).map { t -> t.length > 8 }
        val editDescriptionObservable = RxTextView.textChanges(editDescription!!).map { t -> !TextUtils.isEmpty(t) }
        val editStartDateObservable = RxTextView.textChanges(editStartDate).map { t -> !TextUtils.isEmpty(t) }
        val dueDateObservable = RxTextView.textChanges(dueDate).map { t -> !TextUtils.isEmpty(t) }

        Observable.combineLatest(editTitleObservable, editDescriptionObservable,
                editStartDateObservable, dueDateObservable,
                Function4<Boolean, Boolean, Boolean, Boolean, Boolean>
                { editTitleValid, descriptionValid, startDateValid, dueDateValid
                    ->
                    editTitleValid && descriptionValid && startDateValid && dueDateValid
                })
                .subscribe { result -> if (result) view!!.enableDialogButton() else view!!.disableDialogButton() }
    }

    override fun setCalendarFromChoice(calendar: Calendar, month: Int): Calendar {
        calendar.set(Calendar.MONTH, month)

        return calendar
    }
}