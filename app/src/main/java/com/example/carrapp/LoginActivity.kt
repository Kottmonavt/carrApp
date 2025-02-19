package com.example.carrapp

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity

class LoginActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContentView(R.layout.login_page)
        val linkRegister: Button = findViewById(R.id.buttonRegister)

        linkRegister.setOnClickListener ({
            val intent = Intent(this@LoginActivity, RegisterActivity_1::class.java)
            startActivity(intent)
        })
    }
}