package com.example.carrapp

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity
import com.example.carrapp.ui.notifications.NotificationsFragment

class LoginActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContentView(R.layout.login_page)
        val linkRegister: Button = findViewById(R.id.buttonRegister)
        val linkLogin: Button = findViewById(R.id.logInButton)

        linkRegister.setOnClickListener {
            val intent = Intent(this@LoginActivity, RegisterActivity1::class.java)
            startActivity(intent)
        }

        linkLogin.setOnClickListener {
            val intent = Intent(this@LoginActivity, MainActivity2::class.java)
            startActivity(intent)
        }
    }
}