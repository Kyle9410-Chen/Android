package com.example.a220915_2

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import kotlinx.android.synthetic.main.activity_main.*
import org.w3c.dom.Text
import java.lang.Exception

class MainActivity : AppCompatActivity() {

    var number = ""
    lateinit var numbers : ArrayList<Float>
    lateinit var types : ArrayList<Int>
    lateinit var new_types : ArrayList<Int>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        numbers = ArrayList()
        types = ArrayList()
        new_types = ArrayList()

        b()
    }

    private fun b() {

        num1.setOnClickListener {
            try{
                q.setText(q.text.toString() + "1")
            }
            catch (e : Exception){
                q.setText("1")
            }
            number += "1"
        }

        num2.setOnClickListener {
            try{
                q.setText(q.text.toString() + "2")
            }
            catch (e : Exception){
                q.setText("2")
            }
            number += "2"
        }

        num3.setOnClickListener {
            try{
                q.setText(q.text.toString() + "3")
            }
            catch (e : Exception){
                q.setText("3")
            }
            number += "3"
        }

        num4.setOnClickListener {
            try{
                q.setText(q.text.toString() + "4")
            }
            catch (e : Exception){
                q.setText("4")
            }
            number += "4"
        }

        num5.setOnClickListener {
            try{
                q.setText(q.text.toString() + "5")
            }
            catch (e : Exception){
                q.setText("5")
            }
            number += "5"
        }

        num6.setOnClickListener {
            try{
                q.setText(q.text.toString() + "6")
            }
            catch (e : Exception){
                q.setText("6")
            }
            number += "6"
        }

        num7.setOnClickListener {
            try{
                q.setText(q.text.toString() + "7")
            }
            catch (e : Exception){
                q.setText("7")
            }
            number += "7"
        }

        num8.setOnClickListener {
            try{
                q.setText(q.text.toString() + "8")
            }
            catch (e : Exception){
                q.setText("8")
            }
            number += "8"
        }

        num9.setOnClickListener {
            try{
                q.setText(q.text.toString() + "9")
            }
            catch (e : Exception){
                q.setText("9")
            }
            number += "9"
        }

        num0.setOnClickListener {
            try{
                q.setText(q.text.toString() + "0")
            }
            catch (e : Exception){

            }
            number += "0"
        }

        type1.setOnClickListener {
            try{
                numbers.add(number.toFloat())
                number = ""
                types.add(1)
                q.setText(q.text.toString() + "+")
            }
            catch (e : Exception){

            }

        }

        type2.setOnClickListener {
            try{
                numbers.add(number.toFloat())
                number = ""
                types.add(2)
                q.setText(q.text.toString() + "-")
            }
            catch (e : Exception){

            }

        }

        type3.setOnClickListener {
            try{
                numbers.add(number.toFloat())
                number = ""
                types.add(3)
                q.setText(q.text.toString() + "*")
            }
            catch (e : Exception){

            }

        }

        type4.setOnClickListener {
            try{
                numbers.add(number.toFloat())
                number = ""
                types.add(4)
                q.setText(q.text.toString() + "/")
            }
            catch (e : Exception){

            }
        }

        answer.setOnClickListener {
            try{
                numbers.add(number.toFloat())
                cal()
            }
            catch (e : Exception){
                Toast.makeText(this, "請輸入正確的算式", Toast.LENGTH_SHORT).show()
                Log.e("err",e.toString())
            }
        }

        clear.setOnClickListener {
            a.setText("")
            q.setText("")
            numbers = ArrayList()
            types = ArrayList()
            types = ArrayList()
            number = ""
        }

    }

    private fun cal() {

        var i = 0
        var count = 0

        types.forEach {
            when(it){

                3 -> {
                    numbers[i-count] = numbers[i-count] * numbers[i+1-count]
                    numbers.removeAt(i+1-count)
                    count++
                }
                4 -> {
                    numbers[i-count] = numbers[i-count] / numbers[i+1-count]
                    numbers.removeAt(i+1-count)
                    count++
                }

                else -> {
                    new_types.add(it)
                }

            }
            i++
        }

        i = 0
        count = 0

        new_types.forEach {
            when(it){

                1 -> {
                    numbers[i-count] = numbers[i-count] + numbers[i+1-count]
                    numbers.removeAt(i+1-count)
                    count++
                }
                2 -> {
                    numbers[i-count] = numbers[i-count] - numbers[i+1-count]
                    numbers.removeAt(i+1-count)
                    count++
                }
            }
            i++
        }

        if(numbers[0].toInt().toFloat() == numbers[0]){
            a.setText(numbers[0].toInt().toString())
        }
        else{
            a.setText(numbers[0].toString())
        }

        q.setText("")
        numbers = ArrayList()
        types = ArrayList()
        new_types = ArrayList()
        number = ""
    }
}