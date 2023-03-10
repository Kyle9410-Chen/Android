package com.example.a0929

import android.app.DatePickerDialog
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.DatePicker
import android.widget.Toast
import kotlinx.android.synthetic.main.activity_main.*
import java.util.*

class MainActivity : AppCompatActivity() {
    var t = 0
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        b()
    }

    private fun b() {

        bd_button.setOnClickListener {

            if(name.text.toString().trim() == "") {
                Toast.makeText(this, "請輸入名字", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }

            if(t == 5){
                Toast.makeText(this, "只能查詢五次", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }

            DatePickerDialog(this, { datePicker, year, month, day ->
                run {
                    val it = Intent(this,MainActivity2::class.java)
                    it.putExtra("name",name.text.toString())
                    it.putExtra("year",year)
                    it.putExtra("month",month+1)
                    it.putExtra("day",day)
                    startActivity(it)
                    t += 1
                    times.text = t.toString()
                }
            },Calendar.getInstance().get(Calendar.YEAR),Calendar.getInstance().get(Calendar.MONTH),Calendar.getInstance().get(Calendar.DAY_OF_MONTH)).show()
        }

        hw2_button.setOnClickListener {
            startActivity(Intent(this,MainActivity3::class.java))
        }

    }
}