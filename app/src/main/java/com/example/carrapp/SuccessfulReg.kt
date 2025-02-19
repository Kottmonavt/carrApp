package com.example.carrapp

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class SuccessfulReg : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.success_page)

        val linkNext: Button = findViewById(R.id.buttonNext)
        linkNext.setOnClickListener ({
            val intent = Intent(this@SuccessfulReg, LoginActivity::class.java)
            startActivity(intent)

        })
    }
}