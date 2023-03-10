package com.example.a1103

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import kotlinx.android.synthetic.main.activity_main.*
import java.lang.NullPointerException

class MainActivity : AppCompatActivity() {

    val data = ArrayList<Speed>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        b()
    }

    private fun b() {

        calculator.setOnClickListener {
            try {
                if (name.text.isBlank()){
                    throw NullPointerException()
                }
                val data = speed.text.toString().toInt() * 1.61
                Toast.makeText(this,"${name.text}, 時速= ${data}公里 (${speed.text}英哩)", Toast.LENGTH_LONG).show()
            } catch (e: Exception) {
                Toast.makeText(this, "請輸入完整資料", Toast.LENGTH_SHORT).show()
            }
        }

        save.setOnClickListener {
            try {
                if (name.text.isBlank()) {
                    throw NullPointerException()
                }
                data.add(
                    Speed(
                        name.text.toString(),
                        speed.text.toString().toDouble(),
                        speed.text.toString().toDouble() * 1.61
                    )
                )

                dataList.adapter = adapter(this,data)
            }
            catch (e : Exception){
                Toast.makeText(this, "請輸入完整資料", Toast.LENGTH_SHORT).show()
            }
        }
    }
}