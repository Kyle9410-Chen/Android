package com.example.a1222

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import kotlinx.android.synthetic.main.activity_week2.*

class Week2_1 : AppCompatActivity() {
    var itemList = ArrayList<Item>()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_week2)

        getData()
        b()
    }

    private fun b() {
        week2Filter.setOnClickListener {
            try{
                itemList = sql.getData("select * from Items where id = ${week2Id.text}")
                week2Data.adapter = Week2Adapter(this,itemList)
            }
            catch (e : Exception){
                Toast.makeText(this, "請輸入正確資料", Toast.LENGTH_SHORT).show()
            }

        }
        week2Clear.setOnClickListener {
            itemList = sql.getData("select * from Items")
            week2Data.adapter = Week2Adapter(this,itemList)
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
        week2Data.adapter = Week2Adapter(this,itemList)
    }
}