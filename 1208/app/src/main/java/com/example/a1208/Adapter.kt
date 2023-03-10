package com.example.a1208

import android.app.AlertDialog
import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import kotlinx.android.synthetic.main.data_list.view.*

class Adapter(
    val context: Context,
    val data : ArrayList<Person>
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

        v.name.text = d.name
        v.city.text = d.city
        v.gender.text = d.gender
        v.school.text = d.school

        v.delete.setOnClickListener {
            AlertDialog.Builder(context)
                .setTitle("Warning")
                .setMessage("This data will delete and never recover")
                .setPositiveButton("Submit", {_ ,i-> (context as HomeWork3).deleteData(d)})
                .setNegativeButton("Cancel", {_ ,i-> })
                .show()

        }

        v.edit.setOnClickListener {
            val intent = Intent(context,HomeWork3_Edit::class.java)
            intent.putExtra("index",p0)
            intent.putExtra("name",d.name)
            intent.putExtra("city",d.city)
            intent.putExtra("gender",d.gender)
            intent.putExtra("school",d.school)
            (context as HomeWork3).startActivity(intent)
        }

        return v
    }
}

class Person(
    var name : String,
    var city : String,
    var gender : String,
    var school : String
){

}