package com.example.a1006

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import kotlinx.android.synthetic.main.activity_main2.*

class MainActivity2 : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main2)

        b()
    }

    private fun b() {
        open_url.setOnClickListener {
            web.loadUrl(url.text.toString())
        }
    }
}