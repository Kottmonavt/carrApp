package com.example.carrapp

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.ImageButton
import androidx.appcompat.app.AppCompatActivity

class RegisterActivity_3 : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.register_third_page)

        val linkReturn: ImageButton = findViewById(R.id.imageButton)
        val linkNext: Button = findViewById(R.id.buttonNext)


        linkReturn.setOnClickListener ({
            val intent = Intent(this@RegisterActivity_3, RegisterActivity_2::class.java)
            startActivity(intent)
        })

        linkNext.setOnClickListener ({
            val intent = Intent(this@RegisterActivity_3, SuccessfulReg::class.java)
            startActivity(intent)
        })



    }
}