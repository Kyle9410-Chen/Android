package com.example.a1103

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import kotlinx.android.synthetic.main.activity_main.view.*
import kotlinx.android.synthetic.main.data_list.view.*
import kotlinx.android.synthetic.main.data_list.view.name

class adapter(
    val context : Context,
    val data : ArrayList<Speed>,
) : BaseAdapter() {
    override fun getCount(): Int {
        return data.size
    }

    override fun getItem(p0: Int): Any {
        return data[p0]
    }

    override fun getItemId(p0: Int): Long {
        return 0
    }

    override fun getView(p0: Int, p1: View?, p2: ViewGroup?): View {
        val v = LayoutInflater.from(context)
            .inflate(
                R.layout.data_list,
                null,
                false
            )
        val d = data[p0]
        v.name.text = "姓名: " + d.name
        v.speed1.text = "英哩: " + d.speed_1.toString()
        v.speed2.text = "公里: " + d.speed_2.toString()

        return v
    }
}

class Speed(
    val name : String,
    val speed_1 : Double,
    val speed_2 : Double
){

}