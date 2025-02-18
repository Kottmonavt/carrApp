package com.example.carrapp

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity

class HomePageActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContentView(R.layout.gettind_started)

        val linkToLogin: Button = findViewById(R.id.logInButton)
        val linkToRegister: Button = findViewById(R.id.registerButton)

        linkToLogin.setOnClickListener ({
            val intent = Intent(this@HomePageActivity, LoginActivity::class.java)
            startActivity(intent)
        })
        linkToRegister.setOnClickListener ({
            val intent = Intent(this@HomePageActivity, RegisterActivity_1::class.java)
            startActivity(intent)
        })
    }
}