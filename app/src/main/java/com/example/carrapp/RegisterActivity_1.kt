package com.example.carrapp

import android.content.Intent
import android.os.Bundle
import android.text.TextUtils
import android.widget.Button
import android.widget.CheckBox
import android.widget.EditText
import android.widget.ImageButton
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity

class RegisterActivity_1 : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.register_first_page)

        val linkReturn: ImageButton = findViewById(R.id.imageButton)
        val linkNext: Button = findViewById(R.id.buttonNext)
        val emailData: EditText = findViewById(R.id.EmailAddress)
        val passwordData1: EditText = findViewById(R.id.password)
        val passwordData2: EditText = findViewById(R.id.password2)
        val ticket: CheckBox = findViewById(R.id.checkBox)

        linkReturn.setOnClickListener ({
            val intent = Intent(this@RegisterActivity_1, HomePageActivity::class.java)
            startActivity(intent)
        })

        linkNext.setOnClickListener ({
            val email = emailData.text.toString().trim()
            val password1 = passwordData1.text.toString().trim()
            val password2 = passwordData2.text.toString().trim()

            val intent = Intent(this@RegisterActivity_1, RegisterActivity_2::class.java)
            if(!email.isEmailValid())
                Toast.makeText(this, "Введите корректный адрес электронной почты", Toast.LENGTH_SHORT).show()
            else
                if(password1 == "")
                    Toast.makeText(this, "Пароль не введен", Toast.LENGTH_SHORT).show()
                else
                    if(password1 != password2)
                        Toast.makeText(this, "Пароли не совпадают", Toast.LENGTH_SHORT).show()
                    else
                        if(!ticket.isChecked())
                            Toast.makeText(this, "Необходимо согласиться с условиями обслуживания и политикой конфиденциальности", Toast.LENGTH_SHORT).show()
                        else
                            startActivity(intent)
        })


    }
    fun String.isEmailValid(): Boolean {
        return !TextUtils.isEmpty(this) && android.util.Patterns.EMAIL_ADDRESS.matcher(this).matches()
    }

}