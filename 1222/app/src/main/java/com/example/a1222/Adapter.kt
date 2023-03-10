package com.example.a1222

import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import kotlinx.android.synthetic.main.activity_week22.*
import kotlinx.android.synthetic.main.item_listview_week1.view.*
import kotlinx.android.synthetic.main.item_listview_week2.view.*
import kotlinx.android.synthetic.main.item_listview_week2_2.view.*
import kotlinx.android.synthetic.main.item_listview_week3.view.*

class Week1Adapter(
    val context : Context,
    val data : ArrayList<Item>
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
                R.layout.item_listview_week1,
                null,
                false
            )

        v.week1ItemName.text = "項目: ${data[p0].name}"
        v.week1ItemPrice.text = "價格: ${data[p0].price}"

        v.week1Delete.setOnClickListener {
            (context as Week2_2).deleteData(data[p0].id)
        }

        return v
    }
}

class Week2Adapter(
    val context : Context,
    val data : ArrayList<Item>
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
                R.layout.item_listview_week2,
                null,
                false
            )

        v.week2ItemName.text = "項目: ${data[p0].name}"
        v.week2ItemPrice.text = "價格: ${data[p0].price}"

        return v
    }
}

class Week2_2Adapter(
    val context : Context,
    val data : ArrayList<Item>
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
                R.layout.item_listview_week2_2,
                null,
                false
            )

        v.week2_2ItemName.text = "項目: ${data[p0].name}"
        v.week2_2ItemPrice.text = "價格: ${data[p0].price}"

        v.week2_2ListContent.setOnClickListener{
            (context as Week2_2).week2_2Id.setText(data[p0].id.toString())
            (context as Week2_2).week2_2Name.setText(data[p0].name)
            (context as Week2_2).week2_2Price.setText(data[p0].price.toString())
        }

        v.week2_2Delete.setOnClickListener {
            (context as Week2_2).deleteData(data[p0].id)
        }

        return v
    }
}

class Week3Adapter(
    val context : Context,
    val data : ArrayList<Item>
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
                R.layout.item_listview_week3,
                null,
                false
            )

        v.week3ItemName.text = "項目: ${data[p0].name}"
        v.week3ItemPrice.text = "價格: ${data[p0].price}"


        v.week3Delete.setOnClickListener {
            (context as Week3).deleteData(data[p0].id)
        }

        v.week3Edit.setOnClickListener {
            val d = data[p0].id.toString()
            val intent = Intent(context,Week3Information::class.java)
            intent.putExtra("mode",2)
            intent.putExtra("id",data[p0].id.toString())
            intent.putExtra("name",data[p0].name)
            intent.putExtra("price",data[p0].price)
            (context as Week3).startActivity(intent)
        }

        return v
    }
}