package com.example.a1208

import android.content.Intent
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import kotlinx.android.synthetic.main.activity_home_work1.*

class HomeWork1 : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_home_work1)

        b()
    }

    private fun b() {

        close.setOnClickListener {
            finish()
        }

        another.setOnClickListener {
            startActivity(Intent(this,HomeWork1_Another::class.java))
        }

        call.setOnClickListener {
            val intent = Intent(Intent.ACTION_DIAL)
            intent.setData(Uri.parse("tel:0000000000"))
            startActivity(intent)
        }

    }
}