package com.example.a1208

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        b()
    }

    private fun b() {

        hw1.setOnClickListener {
            startActivity(Intent(this,HomeWork1::class.java))
        }


        hw2.setOnClickListener {
            startActivity(Intent(this,HomeWork2::class.java))
        }


        hw3.setOnClickListener {
            startActivity(Intent(this,HomeWork3::class.java))
        }
    }
}