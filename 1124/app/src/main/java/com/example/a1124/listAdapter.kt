package com.example.a1124

import android.app.Activity
import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import kotlinx.android.synthetic.main.list_adapter.view.*

class listAdapter(
    val context : Context,
    val data : ArrayList<String>
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
                R.layout.list_adapter,
                null,
                false
            )

        v.text.text = data[p0]
        v.checked.setOnCheckedChangeListener{ c , bool ->
            (context as MainActivity).Q4Check(data[p0],bool)
        }

        return v
    }
}