package com.example.carrapp

import android.app.DatePickerDialog
import android.content.Intent
import android.os.Bundle
import android.widget.TextView
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.ImageButton
import android.widget.DatePicker
import androidx.appcompat.app.AppCompatActivity
import java.text.SimpleDateFormat
import java.util.Calendar
import java.util.Locale

class RegisterActivity2 : AppCompatActivity() {

    private var lastNameData: EditText? = null
    private var firstNameData: EditText? = null
    private var patronymicData: EditText? = null
    private var isAllFieldsChecked = false


    private var button_date: ImageButton? = null
    private var cal = Calendar.getInstance()
    private var textview_date: TextView? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.register_second_page)


        val linkReturn: ImageButton = findViewById(R.id.imageButton)
        val linkNext: Button = findViewById(R.id.buttonNext)
        //val lastNameData: EditText = findViewById(R.id.editTextLastName)

        lastNameData = findViewById(R.id.editTextLastName)
        firstNameData = findViewById(R.id.editTextFirstName)
        patronymicData = findViewById(R.id.editTextPatronymic)
        //birthdayData = findViewById(R.id.editTextBirthday)

        button_date = findViewById(R.id.ButtonCalendar)
        textview_date = findViewById(R.id.text_view_date_1)
        textview_date?.text = "--/--/----"
        linkReturn.setOnClickListener({
            val intent = Intent(this@RegisterActivity2, RegisterActivity1::class.java)
            startActivity(intent)
        })
        linkNext.setOnClickListener {
            isAllFieldsChecked = CheckAllFields()
            if (isAllFieldsChecked) {
                val intent = Intent(this@RegisterActivity2, RegisterActivity3::class.java)
                startActivity(intent)
            }
        }
        val dateSetListener = object : DatePickerDialog.OnDateSetListener {
            override fun onDateSet(
                view: DatePicker, year: Int, monthOfYear: Int,
                dayOfMonth: Int
            ) {
                cal.set(Calendar.YEAR, year)
                cal.set(Calendar.MONTH, monthOfYear)
                cal.set(Calendar.DAY_OF_MONTH, dayOfMonth)
                updateDateInView()

            }
        }
        button_date?.setOnClickListener(object : View.OnClickListener {
            override fun onClick(view: View) {
                DatePickerDialog(
                    this@RegisterActivity2,
                    dateSetListener,
                    // set DatePickerDialog to point to today's date when it loads up
                    cal.get(Calendar.YEAR),
                    cal.get(Calendar.MONTH),
                    cal.get(Calendar.DAY_OF_MONTH)
                ).show()
            }
        })


    }

    private fun updateDateInView() {
        val myFormat = "MM/dd/yyyy" // mention the format you need
        val sdf = SimpleDateFormat(myFormat, Locale.US)
        textview_date!!.text = sdf.format(cal.getTime())
    }

    fun CheckAllFields(): Boolean {
        if (lastNameData?.length() == 0) {
            lastNameData?.error = "Введите фамилию"
            return false
        }
        if (firstNameData?.length() == 0) {
            firstNameData?.error = "Введите имя"
            return false
        }
        if (textview_date?.text == "--/--/----") {
            textview_date?.error = "Выберите дату рождения"
            return false
        }
        // after all validation return true.
        return true
    }
}