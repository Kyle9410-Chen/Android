package com.example.a220915

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import kotlinx.android.synthetic.main.activity_main.*
import java.lang.Exception

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        b()
    }

    private fun cal(type : Int, num1 : Int, num2 : Int) {

        when(type){
            1 -> {
                answer.text = "結果 : " + (num1 + num2)
            }

            2 -> {
                answer.text = "結果 : " + (num1 - num2)
            }

            3 -> {
                answer.text = "結果 : " + (num1 * num2)
            }

            4 -> {
                answer.text = "結果 : " + (num1.toFloat() / num2.toFloat())
            }
        }

    }

    private fun b() {

        type1.setOnClickListener {
            try{
                if(number1.text.equals(null) or number2.text.equals(null)) return@setOnClickListener
                cal(1, number1.text.toString().toInt(), number2.text.toString().toInt())
            }
            catch (e : Exception){
                Toast.makeText(this, "請輸入有效的數字", Toast.LENGTH_SHORT).show()
                Log.e("a",e.toString())
            }
        }

        type2.setOnClickListener {
            try{
                if(number1.text.equals(null) or number2.text.equals(null)) return@setOnClickListener
                cal(2, number1.text.toString().toInt(), number2.text.toString().toInt())
            }
            catch (e : Exception){
                Toast.makeText(this, "請輸入有效的數字", Toast.LENGTH_SHORT).show()
                Log.e("a",e.toString())
            }
        }

        type3.setOnClickListener {
            try{
                if(number1.text.equals(null) or number2.text.equals(null)) return@setOnClickListener
                cal(3, number1.text.toString().toInt(), number2.text.toString().toInt())
            }
            catch (e : Exception){
                Toast.makeText(this, "請輸入有效的數字", Toast.LENGTH_SHORT).show()
                Log.e("a",e.toString())
            }
        }

        type4.setOnClickListener {
            try{
                if(number1.text.equals(null) or number2.text.equals(null)) return@setOnClickListener
                cal(4, number1.text.toString().toInt(), number2.text.toString().toInt())
            }
            catch (e : Exception){
                Toast.makeText(this, "請輸入有效的數字", Toast.LENGTH_SHORT).show()
                Log.e("a",e.toString())
            }
        }

        random.setOnClickListener {
            try{
                if(number1.text.equals(null) or number2.text.equals(null)) return@setOnClickListener
                cal((1..4).random(), number1.text.toString().toInt(), number2.text.toString().toInt())
            }
            catch (e : Exception){
                Toast.makeText(this, "請輸入有效的數字", Toast.LENGTH_SHORT).show()
                Log.e("a",e.toString())
            }
        }

        clear.setOnClickListener {
            number1.setText("")
            number2.setText("")
            answer.text = ""
        }

    }
}