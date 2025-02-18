package com.example.carrapp

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity

class Onboarding3 : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.onboarding_third)

        val linkToNext: Button = findViewById(R.id.buttonNext)
        val linkToHome: Button = findViewById(R.id.missButton2)

        linkToNext.setOnClickListener ({
            val intent = Intent(this@Onboarding3, HomePageActivity::class.java)
            startActivity(intent)
        })
        linkToHome.setOnClickListener ({
            val intent = Intent(this@Onboarding3, HomePageActivity::class.java)
            startActivity(intent)
        })
    }
}