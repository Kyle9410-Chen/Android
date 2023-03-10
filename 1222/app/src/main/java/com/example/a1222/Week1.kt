package com.example.a1222

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.activity_week1.*

class Week1 : AppCompatActivity() {

    var itemList = ArrayList<Item>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_week1)

        getData()
        b()
    }

    private fun b() {
        week1Submit.setOnClickListener {
            try{
                sql.postData(week1SQL.text.toString())
                itemList = sql.getData("select * from Items")
                week1Data.adapter = Week1Adapter(this,itemList)
            }
            catch (e : Exception){
                Toast.makeText(this, "請輸入合法的SQL陳述句", Toast.LENGTH_SHORT).show()
            }

        }
    }

    private fun getData() {
        sql.default(this)
        sql.createTable()

        itemList = sql.getData("select * from Items")
        week1Data.adapter = Week1Adapter(this,itemList)
    }

    fun deleteData(id : Int){
        sql.postData("delete from Items where id = $id")
        itemList = sql.getData("select * from Items")
        week1Data.adapter = Week1Adapter(this,itemList)
    }
}