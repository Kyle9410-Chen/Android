package com.example.a1222

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import kotlinx.android.synthetic.main.activity_week22.*
import kotlinx.android.synthetic.main.activity_week3_information.*

class Week3Information : AppCompatActivity() {

    var mode = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_week3_information)

        getData()
        b()
    }

    private fun b() {
        week3Submit.setOnClickListener {
            if (week3Id.text.isBlank() || week3Name.text.isBlank() || week3Price.text.isBlank()) {
                Toast.makeText(this, "請輸入完整資料", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }
            if (mode == 1){
                try{
                    sql.postData("insert into Items(id,name,price) values(${week3Id.text},'${week3Name.text}',${week3Price.text})")
                    finish()
                }
                catch (e : Exception){
                    Toast.makeText(this, "資料已存在", Toast.LENGTH_SHORT).show()
                }
            }
            else{
                try{
                    sql.postData("update Items set name ='${week3Name.text}', price = ${week3Price.text} where id = ${week3Id.text}")
                    finish()
                }
                catch (e : Exception){
                    Toast.makeText(this, "資料已存在", Toast.LENGTH_SHORT).show()
                }
            }
        }

        week3Cancel.setOnClickListener {
            finish()
        }
    }

    private fun getData(){

        mode = intent.extras!!.getInt("mode")
        if (mode == 2){
            week3Id.isEnabled = false
            week3Id.setText(intent.extras!!.getString("id"))
            week3Name.setText(intent.extras!!.getString("name"))
            week3Price.setText(intent.extras!!.getInt("price").toString())
        }
    }
}