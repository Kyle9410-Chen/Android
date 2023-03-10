package com.example.h0908

import android.graphics.Color
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.ImageView
import android.widget.Switch
import android.widget.Toast
import kotlinx.android.synthetic.main.activity_main.*
import java.lang.Exception
import kotlin.math.ln1p
import kotlin.math.log
import kotlin.random.Random

class MainActivity : AppCompatActivity() {
    var time = 0
    var s = 0
    var c = 0
    var w = 0
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        b()
    }

    private fun b() {
        card.setOnClickListener {
            try{
                val n1 = n1.text.toString().toInt()
                if((n1<=0) or (n1>13)){
                    Toast.makeText(this, "請輸入正確的數字範圍", Toast.LENGTH_SHORT).show()
                    return@setOnClickListener
                }
                val show = (1..13).random()
                when (show){
                    1 -> card.setImageResource(R.drawable.p01)
                    2 -> card.setImageResource(R.drawable.p02)
                    3 -> card.setImageResource(R.drawable.p03)
                    4 -> card.setImageResource(R.drawable.p04)
                    5 -> card.setImageResource(R.drawable.p05)
                    6 -> card.setImageResource(R.drawable.p06)
                    7 -> card.setImageResource(R.drawable.p07)
                    8 -> card.setImageResource(R.drawable.p08)
                    9 -> card.setImageResource(R.drawable.p09)
                    10 -> card.setImageResource(R.drawable.p10)
                    11 -> card.setImageResource(R.drawable.p11)
                    12 -> card.setImageResource(R.drawable.p12)
                    13 -> card.setImageResource(R.drawable.p13)
                }
                if(n1 == show){
                    s++
                    c++
                    score.text = "分數: " + s
                    correct.text = "答對: " + c
                    wrong.text = "答錯: " + w
                }
                else{
                    s--
                    w++
                    score.text = "分數: " + s
                    wrong.text = "答錯: " + w
                    correct.text = "答對: " + c
                }
                time++
                text.setText("結果如上")
                text.setTextColor(Color.BLUE)
                times.text = "猜牌次數: " + time + "次"

            }
            catch (e:Exception){
                Log.e("test",e.toString())
            }
        }

        reset.setOnClickListener {
            n1.setText("")
            card.setImageResource(R.drawable.back)
            text.setTextColor(Color.RED)
            text.text = "請抽牌 !"
            times.text = "猜牌次數"
            score.text = "分數"
            wrong.text = "答錯"
            correct.text = "答對"
            c = 0
            w = 0
            s = 0
            time = 0
        }
    }
}