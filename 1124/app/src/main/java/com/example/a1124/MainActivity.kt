package com.example.a1124

import android.graphics.Color
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.AdapterView
import android.widget.AdapterView.OnItemSelectedListener
import android.widget.ArrayAdapter
import android.widget.RadioGroup
import android.widget.RadioGroup.OnCheckedChangeListener
import android.widget.TextView
import androidx.core.widget.addTextChangedListener
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {
    var ans1 = ArrayList<String>()
    var ans2 = ArrayList<String>()
    var ans3_1 = ArrayList<String>()
    var ans3_2 = ArrayList<String>()
    var ans4 = ArrayList<String>()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        setData()
        b()
    }

    private fun b() {
        spinnerQ3_1.onItemSelectedListener = object : OnItemSelectedListener{
            override fun onItemSelected(p0: AdapterView<*>?, p1: View?, p2: Int, p3: Long) {
                if (p2 == 0){
                    (p1 as TextView).setTextColor(Color.GRAY)
                    answer3_1.text = "答案"
                }
                else{
                    (p1 as TextView).setTextColor(Color.WHITE)
                    answer3_1.text = "答案: " + ans3_1[p2]
                }
            }

            override fun onNothingSelected(p0: AdapterView<*>?) {}
        }

        spinnerQ3_2.onItemSelectedListener = object : OnItemSelectedListener{
            override fun onItemSelected(p0: AdapterView<*>?, p1: View?, p2: Int, p3: Long) {
                if (p2 == 0){
                    (p1 as TextView).setTextColor(Color.GRAY)
                    answer3_2.text = "答案"
                }
                else{
                    (p1 as TextView).setTextColor(Color.WHITE)
                    answer3_2.text = "答案: " + ans3_2[p2]
                }
            }

            override fun onNothingSelected(p0: AdapterView<*>?) {}
        }

        checkBoxQ1_1.setOnCheckedChangeListener { c , bool ->
            if(bool){ans1.add(checkBoxQ1_1.text.toString())}
            else {ans1.remove(checkBoxQ1_1.text.toString())}
            answer1.text = "答案: " + ans1.joinToString(",")
        }
        checkBoxQ1_2.setOnCheckedChangeListener { c , bool ->
            if(bool){ans1.add(checkBoxQ1_2.text.toString())}
            else {ans1.remove(checkBoxQ1_2.text.toString())}
            answer1.text = "答案: " + ans1.joinToString(",")
        }
        checkBoxQ1_3.setOnCheckedChangeListener { c , bool ->
            if(bool){ans1.add(checkBoxQ1_3.text.toString())}
            else {ans1.remove(checkBoxQ1_3.text.toString())}
            answer1.text = "答案: " + ans1.joinToString(",")
        }

        radioButtonQ2_1.setOnCheckedChangeListener { c , bool ->
            if (bool){
                answer2.text = "答案: " + radioButtonQ2_1.text.toString()
            }
        }
        radioButtonQ2_2.setOnCheckedChangeListener { c , bool ->
            if (bool){
                answer2.text = "答案: " + radioButtonQ2_2.text.toString()
            }
        }
        radioButtonQ2_3.setOnCheckedChangeListener { c , bool ->
            if (bool){
                answer2.text = "答案: " + radioButtonQ2_3.text.toString()
            }
        }


        name.addTextChangedListener {
            nameText.text = "姓名: " + name.text.toString()
        }
    }


    private fun setData() {

        ans3_1 = arrayListOf("問題3-1","籃球","足球","棒球")
        ans3_2 = resources.getStringArray(R.array.q3_2).toList() as ArrayList<String>
        val ans4_2 = arrayListOf("籃球","足球","棒球")

        spinnerQ3_1.adapter = ArrayAdapter(this, com.google.android.material.R.layout.support_simple_spinner_dropdown_item,ans3_1)
        spinnerQ3_2.adapter = ArrayAdapter(this, com.google.android.material.R.layout.support_simple_spinner_dropdown_item,ans3_2)
        listViewQ4.adapter = listAdapter(this,ans4_2)

    }

    fun Q4Check(data : String, bool : Boolean){
        if(bool){
            ans4.add(data)
            answer4.text = "答案: " + ans4.joinToString(",")
        }
        else{
            try {
                ans4.remove(data)
                answer4.text = "答案: " + ans4.joinToString(",")
            } catch (e: Exception) {
                val d = 0
            }
        }
    }
}