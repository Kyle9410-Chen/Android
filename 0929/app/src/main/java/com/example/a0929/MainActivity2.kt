package com.example.a0929

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.system.Os.bind
import kotlinx.android.synthetic.main.activity_main2.*

class MainActivity2 : AppCompatActivity() {

    lateinit var birthday : ArrayList<birthday_info>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main2)

        birthday = arrayListOf(
            birthday_info("水瓶座","水瓶座介紹"),
            birthday_info("雙魚座","雙魚座介紹"),
            birthday_info("牡羊座","牡羊座介紹"),
            birthday_info("金牛座","金牛座介紹"),
            birthday_info("雙子座","雙子座介紹"),
            birthday_info("巨蟹座","巨蟹座介紹"),
            birthday_info("獅子座","獅子座介紹"),
            birthday_info("處女座","處女座介紹"),
            birthday_info("天秤座","天秤座介紹"),
            birthday_info("天蠍座","天蠍座介紹"),
            birthday_info("射手座","射手座介紹"),
            birthday_info("摩羯座","摩羯座介紹")
        )

        bind()
        b()
    }

    private fun b() {

        back.setOnClickListener {
            finish()
        }

    }

    private fun bind() {

        val year = intent.getIntExtra("year",1990)
        val month = intent.getIntExtra("month",1990)
        val day = intent.getIntExtra("day",1990)

        name.text = intent.getStringExtra("name").toString()

        bd.text = year.toString() + "年" +
                month.toString() + "月" +
               day.toString() + "日"

        val index = if(month==1) if(day<=20) 11 else (month-1) else if(day<=20) (month-2) else (month-1)

        bd_name.text = birthday[index].name
        bd_info.text = birthday[index].info

    }
}

class birthday_info(name:String , info : String){
    val name = name
    val info = info
}