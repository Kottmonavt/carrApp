package com.example.carrapp

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity

class Onboarding2 : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.onboarding_second)

        val linkToNext: Button = findViewById(R.id.buttonNext)
        val linkToHome: Button = findViewById(R.id.missButton2)

        linkToNext.setOnClickListener {
            val intent = Intent(this@Onboarding2, Onboarding3::class.java)
            startActivity(intent)
        }
        linkToHome.setOnClickListener {
            val intent = Intent(this@Onboarding2, HomePageActivity::class.java)
            startActivity(intent)
        }
    }
}