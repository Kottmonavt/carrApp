package com.example.carrapp

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class homeActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContentView(R.layout.gettind_started)

        val linkToLogin: Button = findViewById(R.id.logInButton)

        linkToLogin.setOnClickListener ({
            val intent = Intent(this@homeActivity, login_activity::class.java)
            startActivity(intent)
        })
    }
}