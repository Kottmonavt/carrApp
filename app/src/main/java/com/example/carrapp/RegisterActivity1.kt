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

class RegisterActivity1 : AppCompatActivity() {

    private var emailData: EditText? = null
    private var passwordData1: EditText? = null
    private var passwordData2: EditText? = null
    private var isAllFieldsChecked = false

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.register_first_page)

        val linkReturn: ImageButton = findViewById(R.id.imageButton)
        val linkNext: Button = findViewById(R.id.buttonNext)
        emailData = findViewById(R.id.EmailAddress)
        passwordData1 = findViewById(R.id.password)
        passwordData2 = findViewById(R.id.password2)
        val ticket: CheckBox = findViewById(R.id.checkBox)

        linkReturn.setOnClickListener {
            val intent = Intent(this@RegisterActivity1, HomePageActivity::class.java)
            startActivity(intent)
        }

        linkNext.setOnClickListener {
            val email = emailData?.text.toString().trim()
            val password1 = passwordData1?.text.toString().trim()
            val password2 = passwordData2?.text.toString().trim()
            isAllFieldsChecked = CheckAllFields()

            val intent = Intent(this@RegisterActivity1, RegisterActivity2::class.java)
            if ((email.isEmailValid()) and (password1 == password2) and ticket.isChecked() and isAllFieldsChecked){
                val user = User(email, password2)

                val db = DbHelper(this, null)
                db.addUser(user)
                startActivity(intent)

            }
            else
                if (!email.isEmailValid())
                    Toast.makeText(
                        this,
                        "Введите корректный адрес электронной почты",
                        Toast.LENGTH_SHORT
                    ).show()
                else
                    if ((password1 != password2))
                        Toast.makeText(this, "Пароли не совпадают", Toast.LENGTH_SHORT).show()
                    else
                        if (!ticket.isChecked())
                            Toast.makeText(
                                this,
                                "Необходимо согласиться с условиями обслуживания и политикой конфиденциальности",
                                Toast.LENGTH_LONG
                            ).show()

        }


    }

    fun String.isEmailValid(): Boolean {
        return !TextUtils.isEmpty(this) && android.util.Patterns.EMAIL_ADDRESS.matcher(this)
            .matches()
    }

    fun CheckAllFields(): Boolean {
        if (emailData?.length() == 0) {
            emailData?.error = "Введите почту"
            return false
        }
        if (passwordData1?.length() == 0) {
            passwordData1?.error = "Введите пароль"
            return false
        }
        if (passwordData2?.length() == 0) {
            passwordData2?.error = "Введите пароль"
            return false
        }
        // after all validation return true.
        return true
    }


}