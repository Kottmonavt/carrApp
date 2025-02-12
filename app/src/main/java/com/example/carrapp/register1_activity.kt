package com.example.carrapp

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.ImageButton
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class register1_activity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.register_first_page)

        val linkReturn: ImageButton = findViewById(R.id.imageButton)
        val linkNext: Button = findViewById(R.id.buttonNext)

        linkReturn.setOnClickListener ({
            val intent = Intent(this@register1_activity, homeActivity::class.java)
            startActivity(intent)
        })

        linkNext.setOnClickListener ({
            val intent = Intent(this@register1_activity, register2_activity::class.java)
            startActivity(intent)
        })

    }
}