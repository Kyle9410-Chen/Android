package com.example.a1117

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import kotlinx.android.synthetic.main.account_list.view.*

class adapter(context : Context, data : ArrayList<user>, activity : MainActivity3) : BaseAdapter() {

    val context = context
    val data = data
    val activity = activity

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
        val v = LayoutInflater.from(context).inflate(
            R.layout.account_list,
            null,
            false
        )
        v.account.text = data[p0].account
        v.delete.setOnClickListener {
            activity.delete(p0)
        }

        return v
    }
}