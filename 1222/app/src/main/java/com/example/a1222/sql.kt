package com.example.a1222

import android.content.Context
import android.content.Context.MODE_PRIVATE
import android.database.sqlite.SQLiteDatabase

class sql {

    companion object{
        lateinit var db : SQLiteDatabase

        fun default(context : Context){
            db = context.openOrCreateDatabase("homework1222.db", MODE_PRIVATE, null)
        }

        fun createTable(){
            var sqlCommand = "create table if not exists Items(Id integer primary key, name text not null, price integer not null)"
            db.execSQL(sqlCommand)
        }

        fun getData(sqlCommand : String) : ArrayList<Item>{
            val result = ArrayList<Item>()
            var c = db.rawQuery(sqlCommand,null)
            c.moveToFirst()
            for (i in 0 until c.count){
                result.add(
                    Item(
                        c.getInt(0),
                        c.getString(1),
                        c.getInt(2)
                    )
                )
                c.moveToNext()
            }
            c.close()
            return result
        }

        fun postData(sqlCommand : String){
            db.execSQL(sqlCommand)
        }
    }
}

class Item(
    var id : Int,
    var name : String,
    var price : Int
){

}