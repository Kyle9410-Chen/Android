package com.example.a1222

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import kotlinx.android.synthetic.main.activity_week1.*
import kotlinx.android.synthetic.main.activity_week2.*
import kotlinx.android.synthetic.main.activity_week2.week2Data
import kotlinx.android.synthetic.main.activity_week22.*

class Week2_2 : AppCompatActivity() {
    var itemList = ArrayList<Item>()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_week22)

        getData()
        b()
    }

    private fun b() {

        week2_2Add.setOnClickListener {
            if (week2_2Id.text.isBlank() || week2_2Name.text.isBlank() || week2_2Price.text.isBlank()) {
                Toast.makeText(this, "請輸入完整資料", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }

            try{
                sql.postData("insert into Items(id,name,price) values(${week2_2Id.text},'${week2_2Name.text}',${week2_2Price.text})")
                itemList = sql.getData("select * from Items")
                week2_2Data.adapter = Week2_2Adapter(this,itemList)
            }
            catch (e : Exception){
                Toast.makeText(this, "資料已存在", Toast.LENGTH_SHORT).show()
            }
        }

        week2_2Edit.setOnClickListener {
            if (week2_2Id.text.isBlank() || week2_2Name.text.isBlank() || week2_2Price.text.isBlank()) {
                Toast.makeText(this, "請輸入完整資料", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }

            try{
                sql.postData("update Items set name ='${week2_2Name.text}', price = ${week2_2Price.text} where id = ${week2_2Id.text}")
                itemList = sql.getData("select * from Items")
                week2_2Data.adapter = Week2_2Adapter(this,itemList)
            }
            catch (e : Exception){
                Toast.makeText(this, "資料已存在", Toast.LENGTH_SHORT).show()
            }
        }

        week2_2Clear.setOnClickListener {
            sql.postData("delete from Items")
            itemList = sql.getData("select * from Items")
            week2_2Data.adapter = Week2_2Adapter(this,itemList)
        }


    }

    private fun getData() {
        try{
            sql.default(this)
        }
        catch (e : Exception){

        }
        sql.createTable()

        itemList = sql.getData("select * from Items")
        week2_2Data.adapter = Week2_2Adapter(this,itemList)
    }

    fun deleteData(id : Int){
        sql.postData("delete from Items where id = $id")
        itemList = sql.getData("select * from Items")
        week2_2Data.adapter = Week2_2Adapter(this,itemList)
    }
}