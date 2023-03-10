package com.example.a1222

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.core.widget.addTextChangedListener
import kotlinx.android.synthetic.main.activity_week22.*
import kotlinx.android.synthetic.main.activity_week22.week2_2Data
import kotlinx.android.synthetic.main.activity_week3.*
import kotlinx.android.synthetic.main.activity_week3_information.*

class Week3 : AppCompatActivity() {

    var itemList = ArrayList<Item>()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_week3)

        getData()
        b()
    }

    private fun b() {
        add.setOnClickListener {
            val intent = Intent(this, Week3Information::class.java)
            intent.putExtra("mode",1)
            startActivity(intent)
        }

        week3Search.addTextChangedListener {
            if (week3Search.text.toString().isBlank()) {
                itemList = sql.getData("select * from Items")
                week3Data.adapter = Week3Adapter(this,itemList)
                return@addTextChangedListener
            }

            itemList = sql.getData("select * from Items where name like '%${week3Search.text}%'")
            week3Data.adapter = Week3Adapter(this,itemList)
        }

        refresh.setOnClickListener {
            sql.postData("delete from Items")
            sql.postData("insert into Items(id,name,price) values(1,'item1',20)")
            sql.postData("insert into Items(id,name,price) values(2,'item2',40)")
            sql.postData("insert into Items(id,name,price) values(3,'item3',60)")
            sql.postData("insert into Items(id,name,price) values(4,'item4',80)")
            sql.postData("insert into Items(id,name,price) values(5,'item5',100)")
            itemList = sql.getData("select * from Items")
            week3Data.adapter = Week3Adapter(this,itemList)
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
        week3Data.adapter = Week3Adapter(this,itemList)
    }

    fun deleteData(id : Int){
        sql.postData("delete from Items where id = $id")
        itemList = sql.getData("select * from Items")
        week3Data.adapter = Week3Adapter(this,itemList)
    }

    override fun onResume() {
        super.onResume()
        getData()
    }
}