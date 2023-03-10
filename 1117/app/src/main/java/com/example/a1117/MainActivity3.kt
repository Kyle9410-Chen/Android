package com.example.a1117

import android.content.Intent
import android.content.SharedPreferences
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import kotlinx.android.synthetic.main.activity_main3.*
import org.json.JSONArray
import org.json.JSONObject
import kotlin.collections.ArrayList

class MainActivity3 : AppCompatActivity() {

    var data = ArrayList<user>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main3)

        getdata()
        b()
    }

    private fun b() {

        login.setOnClickListener {
            val intent = Intent(this@MainActivity3,MainActivity::class.java)
            val jsondata = JSONArray()
            for(i in 0..data.size-1){
                val json = JSONObject()
                json.put("account",data[i].account)
                json.put("password",data[i].password)
                jsondata.put(json)
            }
            intent.putExtra("data",jsondata.toString())
            startActivity(intent)
        }

        add.setOnClickListener {
            val intent = Intent(this@MainActivity3,MainActivity2::class.java)
            val jsondata = JSONArray()
            for(i in 0..data.size-1){
                val json = JSONObject()
                json.put("account",data[i].account)
                json.put("password",data[i].password)
                jsondata.put(json)
            }
            intent.putExtra("data",jsondata.toString())
            startActivity(intent)
        }


    }

    private fun getdata() {
        val sp = getSharedPreferences("data", MODE_PRIVATE)
        data = JSONArray(sp.getString("data","")).let{
            val list = ArrayList<user>()
            for (i in 0..it.length()-1){
                list.add(user(it.getJSONObject(i).getString("account"),it.getJSONObject(i).getString("password")))
            }
            list
        }

        listview.adapter = adapter(this,data,this)
    }

    override fun onResume() {
        super.onResume()
        getdata()
    }

    fun delete(index : Int){
        data.removeAt(index)
        listview.adapter = adapter(this,data,this)
        val jsondata = JSONArray()
        for (i in 0..data.size-1){
            val json = JSONObject()
            json.put("account",data[i].account)
            json.put("password",data[i].password)
            jsondata.put(json)
        }
        val sp = getSharedPreferences("data", MODE_PRIVATE)
        sp.edit().putString("data",jsondata.toString()).apply()
    }
}