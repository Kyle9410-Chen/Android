package com.example.a0112

import android.graphics.Color
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.AdapterView
import android.widget.AdapterView.OnItemSelectedListener
import android.widget.ArrayAdapter
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.class_list.view.*
import kotlin.math.pow

class MainActivity : AppCompatActivity() {
    var classList = ArrayList<String>()
    var searchList = ArrayList<String>()
    var cityList = ArrayList<String>()
    var iList = ArrayList<String>()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)


        getData()
        b()
    }

    private fun getData() {
        cityList = resources.getStringArray(R.array.cityList).toList() as ArrayList<String>
        city.adapter = ArrayAdapter(this, com.google.android.material.R.layout.support_simple_spinner_dropdown_item, cityList)
        city.setSelection(0)
        classList = arrayListOf("國文","數學","英文","電子學","數位邏輯")
        searchClass.adapter = Adapter(this, classList)
    }

    private fun b() {
        male.setOnCheckedChangeListener { compoundButton, b ->
            if (b) {
                genderImg.setImageDrawable(resources.getDrawable(R.drawable.man))
            }
        }
        female.setOnCheckedChangeListener { compoundButton, b ->
            if (b) {
                genderImg.setImageDrawable(resources.getDrawable(R.drawable.woman))
            }
        }
        i1.setOnCheckedChangeListener {c, b ->
            if (b) {
                iList.add(i1.text.toString())
            }
            else {
                iList.remove(i1.text.toString())
            }
            result1.text = "興趣結果: " + iList.joinToString(",")
        }
        i2.setOnCheckedChangeListener {c, b ->
            if (b) {
                iList.add(i2.text.toString())
            }
            else {
                iList.remove(i2.text.toString())
            }
            result1.text = "興趣結果: " + iList.joinToString(",")
        }
        i3.setOnCheckedChangeListener {c, b ->
            if (b) {
                iList.add(i3.text.toString())
            }
            else {
                iList.remove(i3.text.toString())
            }
            result1.text = "興趣結果: " + iList.joinToString(",")
        }
        i4.setOnCheckedChangeListener {c, b ->
            if (b) {
                iList.add(i4.text.toString())
            }
            else {
                iList.remove(i4.text.toString())
            }
            result1.text = "興趣結果: " + iList.joinToString(",")
        }
        city.onItemSelectedListener = object : OnItemSelectedListener {
            override fun onItemSelected(p0: AdapterView<*>?, p1: View?, p2: Int, p3: Long) {
                (p1 as TextView).setTextColor(Color.WHITE)
                result2.text = "居住縣市結果: " + (p1 as TextView).text
            }

            override fun onNothingSelected(p0: AdapterView<*>?) {}
        }

        b1.setOnClickListener {
            name.setText("")
            weight.setText("")
            height.setText("")
            male.isChecked = false
            female.isChecked = false
            i1.isChecked = false
            i2.isChecked = false
            i3.isChecked = false
            i4.isChecked = false
            city.setSelection(0)
            searchList = ArrayList()
            searchClass.adapter = Adapter(this, classList)
            genderImg.setImageDrawable(null)
            result1.text = "興趣結果: "
            result2.text = "居住縣市結果: "
            result3.text = "選課結果: "
        }

        b2.setOnClickListener {
            try {
                Toast.makeText(this, "${name.text} 你好 \n你的身高: ${height.text} \n體重: ${weight.text}\n BMI: ${(weight.text.toString().toInt()/(height.text.toString().toDouble()/100).pow(2))}", Toast.LENGTH_SHORT).show()
            } catch (e: Exception) {
                Toast.makeText(this, "請輸入正整數", Toast.LENGTH_SHORT).show()
            }
        }

        b3.setOnClickListener {
            if (name.text.isBlank() || (!male.isChecked && !female.isChecked)) {
                Toast.makeText(this, "請輸入完整資料", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }
            Toast.makeText(this, "${name.text} 你好\n你的性別是: ${if (male.isChecked) "男" else "女"}\n興趣: ${iList.joinToString(",")}\n居住縣市: ${city.selectedItem}\n選課結果: ${searchList.joinToString(",")}", Toast.LENGTH_SHORT).show()
        }

        b4.setOnClickListener {
            AlertDialog.Builder(this)
                .setMessage("是否結束程式")
                .setPositiveButton("確定", {d, i -> finish()})
                .setNegativeButton("取消", {d, i -> })
                .show()
        }
    }

    fun searchChange(b : Boolean, d : String){
        if (b){
            searchList.add(d)
        }
        else{
            searchList.remove(d)
        }
        result3.text = "選課結果: " + searchList.joinToString(",")
    }
}