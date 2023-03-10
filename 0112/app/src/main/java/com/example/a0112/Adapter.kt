package com.example.a0112

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import kotlinx.android.synthetic.main.city_list.view.*
import kotlinx.android.synthetic.main.class_list.view.*

class Adapter(val context : Context, val data : ArrayList<String>) : BaseAdapter() {
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
                R.layout.class_list,
                null,
                false
            )

        val d = data[p0]
        v.className.text = d
        v.checked.setOnCheckedChangeListener { compoundButton, b ->
            (context as MainActivity).searchChange(b, d)
        }
        return v
    }
}