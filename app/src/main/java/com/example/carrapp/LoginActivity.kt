package com.example.carrapp

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.carrapp.ui.notifications.NotificationsFragment

class LoginActivity : AppCompatActivity() {
    private var emailData: EditText? = null
    private var passwordData1: EditText? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContentView(R.layout.login_page)
        val linkRegister: Button = findViewById(R.id.buttonRegister)
        val linkLogin: Button = findViewById(R.id.logInButton)

        emailData = findViewById(R.id.EmailAddress)
        passwordData1 = findViewById(R.id.password)


        linkRegister.setOnClickListener {
            val intent = Intent(this@LoginActivity, RegisterActivity1::class.java)
            startActivity(intent)
        }

        linkLogin.setOnClickListener {
            val email = emailData?.text.toString().trim()
            val password1 = passwordData1?.text.toString().trim()
            val intent = Intent(this@LoginActivity, MainActivity2::class.java)
            if (email == "" || password1 == "")
                Toast.makeText(this, "Не все поля заполнены", Toast.LENGTH_LONG).show()
            else {
                val db = DbHelper(this, null)
                val isAuth = db.getUser(email, password1)
                if (isAuth) {
                    startActivity(intent)
                } else {
                    Toast.makeText(this, "Почта или пароль неверны", Toast.LENGTH_LONG).show()
                }
            }


        }
    }
}