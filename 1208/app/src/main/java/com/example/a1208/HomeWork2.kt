package com.example.a1208

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import kotlinx.android.synthetic.main.activity_home_work2.*
import kotlin.math.pow

class HomeWork2 : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_home_work2)

        b()
    }

    private fun b() {

        calculate.setOnClickListener {
            try {
                val result = number1.text.toString().toInt() / number2.text.toString().toInt()
                answer.text = number1.text.toString() + " / " + number2.text.toString() + " = " + result.toString() + " ... " + (number1.text.toString().toInt() - result*number2.text.toString().toInt()).toString()
            } catch (e: Exception) {
                Toast.makeText(this, "除數不可為0", Toast.LENGTH_SHORT).show()
            }
        }

        bmi.setOnClickListener {
            try {
                val result = weight.text.toString().toDouble() / (height.text.toString().toDouble()/100).pow(2)
                bmiAnswer.text = "BMI: $result"
            } catch (e: Exception) {
                Toast.makeText(this, "除數不可為0", Toast.LENGTH_SHORT).show()
            }
        }

    }
}