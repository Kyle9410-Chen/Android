package com.example.a1208

import android.content.Intent
import android.content.SharedPreferences
import android.graphics.Color
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.AdapterView
import android.widget.AdapterView.OnItemSelectedListener
import android.widget.ArrayAdapter
import android.widget.TextView
import android.widget.Toast
import androidx.core.content.edit
import kotlinx.android.synthetic.main.activity_home_work3.*
import org.json.JSONArray
import org.json.JSONObject

class HomeWork3 : AppCompatActivity() {

    var data = ArrayList<Person>()
    var cityData = ArrayList<String>()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_home_work3)



        getData()
        b()
    }

    private fun getData() {

        try {
            val sp = getSharedPreferences("data", MODE_PRIVATE)
            data = JSONArray(sp.getString("data","[]")).let {
                val list = ArrayList<Person>()
                for(i in 0 until it.length()){
                    list.add(Person(
                        it.getJSONObject(i).getString("name"),
                        it.getJSONObject(i).getString("city"),
                        it.getJSONObject(i).getString("gender"),
                        it.getJSONObject(i).getString("school")
                    ))
                }
                list
            }
            dataList.adapter = Adapter(this,data)
        }
        catch (e : Exception){

        }

        cityData = arrayListOf("選擇縣市",
            "臺北市",
            "新北市",
            "桃園市",
            "臺中市",
            "臺南市",
            "高雄市",
            "新竹縣",
            "苗栗縣",
            "彰化縣",
            "南投縣",
            "雲林縣",
            "嘉義縣",
            "屏東縣",
            "宜蘭縣",
            "花蓮縣",
            "臺東縣",
            "澎湖縣",
            "金門縣",
            "連江縣",
            "基隆市",
            "新竹市",
            "嘉義市")

        city.adapter = ArrayAdapter(this, com.google.android.material.R.layout.support_simple_spinner_dropdown_item, cityData)
    }

    private fun b() {

        add.setOnClickListener {
            startActivity(Intent(this,HomeWork3_Add::class.java))
        }

        city.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onItemSelected(p0: AdapterView<*>?, p1: View?, p2: Int, p3: Long) {
                (p1 as TextView).setTextColor(Color.WHITE)

                if (p2 == 0) {
                    dataList.adapter = Adapter(this@HomeWork3,data)
                    return
                }
                filter(p1.text.toString())
            }

            override fun onNothingSelected(p0: AdapterView<*>?) {

            }

        }
    }

    private fun saveData() {
        val sp = getSharedPreferences("data", MODE_PRIVATE)
        val jsondata = JSONArray()
        data.forEach {
            val json = JSONObject()
            json.put("name",it.name)
            json.put("city",it.city)
            json.put("gender",it.gender)
            json.put("school",it.school)
            jsondata.put(json)
        }
        sp.edit().putString("data",jsondata .toString()).apply()
    }

    fun deleteData(p : Person){
        data.remove(p)
        saveData()
        if (city.selectedItemId.toInt() == 0){
            dataList.adapter = Adapter(this,data)
        }
        else{
            filter(city.selectedItem.toString())
        }
    }

    private fun filter(selection : String){
        val data = this.data.filter {
            it.city == selection
        } as ArrayList<Person>

        dataList.adapter = Adapter(this,data)
    }

    override fun onResume() {
        super.onResume()
        getData()
    }
}