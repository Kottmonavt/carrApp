package com.example.carrapp

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity

class Onboarding1 : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContentView(R.layout.onboarding_first)

        val linkToNext: Button = findViewById(R.id.buttonNext)
        val linkToHome: Button = findViewById(R.id.missButton)

        linkToNext.setOnClickListener {
            val intent = Intent(this@Onboarding1, Onboarding2::class.java)
            startActivity(intent)
        }
        linkToHome.setOnClickListener {
            val intent = Intent(this@Onboarding1, HomePageActivity::class.java)
            startActivity(intent)
        }
    }
}