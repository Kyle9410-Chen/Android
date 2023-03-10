package com.example.a0929

import android.content.Intent
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import kotlinx.android.synthetic.main.activity_main3.*

class MainActivity3 : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main3)

        b()
    }

    private fun b() {

        back.setOnClickListener {
            finish()
        }

        web_open.setOnClickListener {
            val it = Intent()
            it.action = Intent.ACTION_VIEW
            it.data = Uri.parse(web_text.text.toString())
            startActivity(it)
        }

        map_open.setOnClickListener {
            val it = Intent()
            it.action = Intent.ACTION_VIEW
            it.data = Uri.parse("geo:" + map_x.text.toString() + ", " + map_y.text.toString())
            startActivity(it)
        }

        phone_open.setOnClickListener {
            val it = Intent(Intent.ACTION_DIAL, Uri.parse("tel:${phone_text.text}"))
            startActivity(it)
        }

        mail_open.setOnClickListener {
            val it = Intent()
            it.action = Intent.ACTION_SENDTO
            it.data = Uri.parse("mailto:${mail_text.text}")
            startActivity(it)
        }
    }
}