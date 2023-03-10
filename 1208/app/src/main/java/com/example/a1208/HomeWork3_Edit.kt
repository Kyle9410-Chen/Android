package com.example.a1208

import android.graphics.Color
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.TextView
import android.widget.Toast
import kotlinx.android.synthetic.main.activity_home_work3_edit.*
import kotlinx.android.synthetic.main.activity_home_work3_edit.city
import kotlinx.android.synthetic.main.activity_home_work3_edit.he
import kotlinx.android.synthetic.main.activity_home_work3_edit.name
import kotlinx.android.synthetic.main.activity_home_work3_edit.school
import kotlinx.android.synthetic.main.activity_home_work3_edit.she
import kotlinx.android.synthetic.main.activity_home_work3_edit.they
import org.json.JSONArray
import org.json.JSONObject

class HomeWork3_Edit : AppCompatActivity() {

    var index = 0
    var data = ArrayList<Person>()
    var cityData = ArrayList<String>()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_home_work3_edit)

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

        index = intent.extras!!.getInt("index")
        name.setText(intent.extras!!.getString("name")!!)
        school.setText(intent.extras!!.getString("school")!!)
        city.setSelection(cityData.indexOf(intent.extras!!.getString("city")!!))

        val gender = intent.extras!!.getString("gender")!!
        if(gender == "男") {he.isChecked = true}
        if(gender == "女") {she.isChecked = true}
        if(gender == "其他") {they.isChecked = true}

    }

    private fun b() {
        edit.setOnClickListener {
            try {
                if (city.selectedItemId.toInt() == 0 || name.text.toString().isNullOrBlank() || name.text.toString().isNullOrBlank() || !(he.isChecked || she.isChecked || they.isChecked)) { throw NullPointerException() }

                data[index].name = name.text.toString()
                data[index].city = city.selectedItem.toString()
                data[index].gender = if (he.isChecked) "男" else if(she.isChecked) "女" else "其他"
                data[index].school = school.text.toString()

                saveData()
                finish()
            }
            catch (e : Exception){
                Toast.makeText(this, "請輸入完整資料", Toast.LENGTH_SHORT).show()
            }
        }

        city.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onItemSelected(p0: AdapterView<*>?, p1: View?, p2: Int, p3: Long) {
                (p1 as TextView).setTextColor(Color.WHITE)
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
}