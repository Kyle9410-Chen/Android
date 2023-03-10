package com.example.a1006

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
        fun3.setOnClickListener {
            startActivity(Intent(this,MainActivity3::class.java))
        }

        fun4.setOnClickListener {
            startActivity(Intent(this,MainActivity4::class.java))
        }
    }
}