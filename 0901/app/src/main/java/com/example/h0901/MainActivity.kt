package com.example.h0901

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.*
import java.lang.Exception

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val arr : ArrayList<String> = ArrayList()

        arr.add("台北市")
        arr.add("新北市")
        findViewById<Spinner>(R.id.i4).adapter = ArrayAdapter(this,
            androidx.appcompat.R.layout.support_simple_spinner_dropdown_item,arr)

        b()

    }

    private fun b() {
        findViewById<Button>(R.id.i5).setOnClickListener {

            try {
                val text = "班級: " + findViewById<TextView>(R.id.i1).text.toString() + " " +
                        "學號: " + findViewById<TextView>(R.id.i2).text.toString() + " " +
                        "姓名: " + findViewById<TextView>(R.id.i3).text.toString() + " " +
                        "居住縣市: " + findViewById<Spinner>(R.id.i4).selectedItem.toString() + " "
                findViewById<TextView>(R.id.t1).setText(text)
            }
            catch (e:Exception) {
                Log.e("t",e.toString())
                Toast.makeText(this, "請輸入完整資料", Toast.LENGTH_SHORT).show()
            }

        }
    }
}