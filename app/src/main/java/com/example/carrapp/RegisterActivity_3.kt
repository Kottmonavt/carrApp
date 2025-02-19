package com.example.carrapp

import android.app.DatePickerDialog
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.provider.MediaStore
import android.view.View
import android.widget.Button
import android.widget.DatePicker
import android.widget.EditText
import android.widget.ImageButton
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import java.text.SimpleDateFormat
import java.util.Calendar
import java.util.Locale

class RegisterActivity_3 : AppCompatActivity() {
    // константы для картинок
    lateinit var pickImgButton: Button
    lateinit var imageIV: ImageView
    lateinit var dcButton: Button
    lateinit var imageDC: ImageView
    lateinit var passButton: Button
    lateinit var imagePass: ImageView
    // константы для проверки данных
    var dcData: EditText? = null
    var dateOfDC: TextView? = null
    var button_date: ImageButton? = null

    var cal = Calendar.getInstance()

    companion object {
        val IMAGE_REQUEST_CODE = 100
    }

    override fun onCreate(savedInstanceState: Bundle?) {


        super.onCreate(savedInstanceState)
        setContentView(R.layout.register_third_page)

        val linkReturn: ImageButton = findViewById(R.id.imageButton)
        val linkNext: Button = findViewById(R.id.buttonNext)
        // константы для картинок
        pickImgButton = findViewById(R.id.buttonImgDownload1)
        imageIV = findViewById(R.id.imageV)
        dcButton = findViewById(R.id.buttonDC)
        imageDC = findViewById(R.id.imageViewDC)
        passButton = findViewById(R.id.buttonPass)
        imagePass = findViewById(R.id.imageViewPass)
        var flagDC = false
        var flagPass = false

        dcData = findViewById(R.id.editTextNumber)
        dateOfDC = findViewById(R.id.text_view_date_2)
        button_date = findViewById(R.id.ButtonCalendar2)


        // добавление картинок
        pickImgButton.setOnClickListener({
            pickImageGallery()
        })

        dcButton.setOnClickListener({
            pickImageDC()
            flagDC = true
        })
        passButton.setOnClickListener({
            pickImagePass()
            flagPass = true
        })

        linkReturn.setOnClickListener ({
            val intent = Intent(this@RegisterActivity_3, RegisterActivity_2::class.java)
            startActivity(intent)
        })

        linkNext.setOnClickListener ({
            val intent = Intent(this@RegisterActivity_3, SuccessfulReg::class.java)
            if(CheckAllFields() && flagPass && flagDC)
                startActivity(intent)
            else
                if (!flagDC || !flagPass)
                    Toast.makeText(this, "Загрузите все необходимые фотографии", Toast.LENGTH_SHORT).show()

        })

        val dateSetListener = object : DatePickerDialog.OnDateSetListener {
            override fun onDateSet(view: DatePicker, year: Int, monthOfYear: Int,
                                   dayOfMonth: Int) {
                cal.set(Calendar.YEAR, year)
                cal.set(Calendar.MONTH, monthOfYear)
                cal.set(Calendar.DAY_OF_MONTH, dayOfMonth)
                updateDateInView()

            }
        }
        button_date!!.setOnClickListener(object : View.OnClickListener {
            override fun onClick(view: View) {
                DatePickerDialog(
                    this@RegisterActivity_3,
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
        dateOfDC!!.text = sdf.format(cal.getTime())
    }
    // проверка валидности данных и их наличие вообще
    fun CheckAllFields(): Boolean {
        if (dcData!!.length() == 0) {
            dcData!!.error = "Введите номер водительского удостоверения"
            return false
        }
        if (dcData!!.length() != 10) {
            Toast.makeText(this, "Номер некорректен", Toast.LENGTH_SHORT).show()
            return false
        }
        if (dateOfDC!!.text == "--/--/----"){
            dateOfDC!!.error = "Выберите дату выдачи"
            return false
        }
        // after all validation return true.
        return true
    }

    // обработка добавления картинок
    var code1 = 0

    private fun pickImageGallery() {
        val intent = Intent(Intent.ACTION_PICK)
        intent.type = "image/*"
        startActivityForResult(intent, IMAGE_REQUEST_CODE)
        code1 = 1
    }
    private fun pickImageDC() {
        val intent = Intent(Intent.ACTION_PICK)
        intent.type = "image/*"
        startActivityForResult(intent, IMAGE_REQUEST_CODE)
        code1 = 2

    }
    private fun pickImagePass() {
        val intent = Intent(Intent.ACTION_PICK)
        intent.type = "image/*"
        startActivityForResult(intent, IMAGE_REQUEST_CODE)
        code1 = 3
    }


    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (requestCode == IMAGE_REQUEST_CODE && resultCode == RESULT_OK && code1 == 1){
            imageIV.setImageURI(data?.data)
        }
        if (requestCode == IMAGE_REQUEST_CODE && resultCode == RESULT_OK && code1 == 2){
            imageDC.setImageURI(data?.data)
        }
        if (requestCode == IMAGE_REQUEST_CODE && resultCode == RESULT_OK && code1 == 3){
            imagePass.setImageURI(data?.data)
        }
    }

}