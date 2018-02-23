package io.github.muninn.presentation.home.todo.create

import android.app.AlertDialog
import android.app.Dialog
import android.app.DialogFragment
import android.content.DialogInterface
import android.os.Bundle
import android.view.LayoutInflater
import android.view.MotionEvent
import android.widget.EditText
import com.wdullaer.materialdatetimepicker.date.DatePickerDialog
import dagger.android.AndroidInjection
import io.github.muninn.R
import io.github.muninn.model.DateTimeTemporaryChoice
import java.util.*
import javax.inject.Inject


/**
 * Created by viacheslavokolitiy on 19.02.2018.
 */
class CreateTodoDialogFragment : DialogFragment(), CreateTodoDialogContract.View {
    @Inject
    lateinit var presenter: CreateTodoDialogPresenter
    private var dialog: AlertDialog? = null

    companion object {
        fun newInstance(): CreateTodoDialogFragment {
            return CreateTodoDialogFragment()
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        AndroidInjection.inject(this)
        super.onCreate(savedInstanceState)
    }

    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
        presenter.onCreate(this)
        val builder = AlertDialog.Builder(activity)
        val inflater = LayoutInflater.from(activity)
        with(builder){
            setTitle(getString(R.string.text_title_new_todo))
            setPositiveButton(getString(R.string.text_btn_create_todo), { dialog, _ -> onPositiveButtonClick(dialog) })
            setNegativeButton(getString(R.string.text_btn_cancel_create_todo), { dialog, _ -> onNegativeButtonClick(dialog) })
        }
        val created = builder.create()
        val view = inflater.inflate(R.layout.fragment_create_todo, null, false)
        created.setView(view)
        created.setCanceledOnTouchOutside(false)
        dialog = created

        created.setOnShowListener({ dialog ->
            run {
                val positiveButton = (dialog as AlertDialog)
                        .getButton(AlertDialog.BUTTON_POSITIVE)
                positiveButton.isEnabled = false
            }
        })

        val editTitle = view?.findViewById<EditText>(R.id.edt_title)
        val editDescription = view?.findViewById<EditText>(R.id.edt_description)
        val editStartDate = view?.findViewById<EditText>(R.id.edt_start_date)
        val dueDate = view?.findViewById<EditText>(R.id.edt_due_date)

        editStartDate!!.setOnTouchListener({ _, e -> onStartDateWasTouched(e) })
        dueDate!!.setOnTouchListener({_, e -> onDueDateWasTouched(e)})

        presenter.handleTextChanges(editTitle, editDescription, editStartDate, dueDate)

        return created
    }

    override fun enableDialogButton() {
        val positiveButton = dialog!!.getButton(DialogInterface.BUTTON_POSITIVE)
        positiveButton?.isEnabled = true
    }

    override fun disableDialogButton() {
        val positiveButton = dialog!!.getButton(DialogInterface.BUTTON_POSITIVE)
        positiveButton?.isEnabled = false
    }

    override fun showStartDate(choice: DateTimeTemporaryChoice) {
        val displayField = dialog?.findViewById<EditText>(R.id.edt_start_date)
        processChoice(choice, displayField!!)
    }

    override fun showDueDate(dueDateChoice: DateTimeTemporaryChoice) {
        val displayField = dialog?.findViewById<EditText>(R.id.edt_due_date)
        processChoice(dueDateChoice, displayField!!)
    }

    private fun processChoice(choice: DateTimeTemporaryChoice, displayField: EditText) {
        val calendar = presenter.prepareCalendar()
        val day = choice.dayOfMonth
        val month = choice.monthOfYear
        val year = choice.year
        val updatedCalendar = presenter.setCalendarFromChoice(calendar, month)
        val dayName = day.toString()
        val monthName = updatedCalendar.getDisplayName(Calendar.MONTH, Calendar.LONG, Locale.US)
        val yearName = year.toString()
        val builder = StringBuilder()
        with(builder){
            append(monthName)
            append(" ")
            append(dayName)
            append(" ")
            append(yearName)
        }
        displayField.setText(builder.toString())
    }

    private fun onDueDateWasTouched(event: MotionEvent?): Boolean {
        when(event!!.action){
            MotionEvent.ACTION_DOWN -> {
                showDatePickerDialog({year, month, day -> onDueDateSet(year, month, day) })
            }
        }

        return false
    }

    private fun onStartDateWasTouched(event: MotionEvent): Boolean {
        when (event.action) {
            MotionEvent.ACTION_DOWN -> {
                showDatePickerDialog({year, month, day -> onStartDateSet(year, month, day) })
            }
        }
        return false
    }

    private fun showDatePickerDialog(dateSet: (Int, Int, Int) -> Unit) {
        val calendar = presenter.prepareCalendar()
        val dialog = DatePickerDialog.newInstance({_, year, monthOfYear, dayOfMonth ->
            dateSet(year, monthOfYear, dayOfMonth) }, calendar)
        dialog.show(fragmentManager, "datePicker")
    }

    private fun onDueDateSet(year: Int, monthOfYear: Int, dayOfMonth: Int){
        presenter.saveTemporaryDueDateChoice(year, monthOfYear, dayOfMonth)
    }

    private fun onStartDateSet(year: Int, monthOfYear: Int, dayOfMonth: Int) {
        presenter.saveTemporaryStartDateChoice(year, monthOfYear, dayOfMonth)
    }

    private fun onNegativeButtonClick(dialog: DialogInterface?) {
        dialog?.dismiss()
    }

    private fun onPositiveButtonClick(dialog: DialogInterface?) {
        dialog?.dismiss()
    }
}