package com.example.a1222

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        b()
    }

    private fun b() {

        week1.setOnClickListener {
            startActivity(Intent(this,Week1::class.java))
        }
        week2_1.setOnClickListener {
            startActivity(Intent(this,Week2_1::class.java))
        }
        week2_2.setOnClickListener {
            startActivity(Intent(this,Week2_2::class.java))
        }
        week3.setOnClickListener {
            startActivity(Intent(this,Week3::class.java))
        }

    }
}