package com.example.a1117

import android.content.DialogInterface
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.TransactionTooLargeException
import android.view.Gravity
import android.view.View
import android.widget.Button
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import kotlinx.android.synthetic.main.activity_main.*
import org.json.JSONArray
import kotlin.concurrent.thread

class MainActivity : AppCompatActivity() {

    var data = ArrayList<user>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        getdata()
        b()
    }

    private fun getdata() {

        try {
            data = JSONArray(intent.extras?.getString("data")).let{
                val list = ArrayList<user>()
                for (i in 0..it.length()-1){
                    list.add(user(it.getJSONObject(i).getString("account"),it.getJSONObject(i).getString("password")))
                }
                list
            }
        }
        catch (e : Exception){

        }

    }

    private fun b() {
        num0.setOnClickListener { password_click(it as Button) }
        num1.setOnClickListener { password_click(it as Button) }
        num2.setOnClickListener { password_click(it as Button) }
        num3.setOnClickListener { password_click(it as Button) }
        num4.setOnClickListener { password_click(it as Button) }
        num5.setOnClickListener { password_click(it as Button) }
        num6.setOnClickListener { password_click(it as Button) }
        num7.setOnClickListener { password_click(it as Button) }
        num8.setOnClickListener { password_click(it as Button) }
        num9.setOnClickListener { password_click(it as Button) }

        clear.setOnClickListener {
            password.text.clear()
        }

        submit.setOnClickListener {
            if (data.any{ x -> x.account == account.text.toString() && x.password == password.text.toString()}){
                Toast.makeText(this@MainActivity, "登入成功", Toast.LENGTH_SHORT).run {
                    this.setGravity(Gravity.CENTER,0,0)
                    this.show()
                }
            }
            else{
                Toast.makeText(this@MainActivity, "資料錯誤", Toast.LENGTH_SHORT).run {
                    this.setGravity(Gravity.CENTER,0,0)
                    this.show()
                }
            }
        }

        back.setOnClickListener {
            try {
                password.setText(password.text.toString().substring(0,password.text.toString().length-1))
            } catch (e: Exception) {
            }
        }

        end.setOnClickListener {
            AlertDialog.Builder(this@MainActivity)
                .setTitle("是否退出程式")
                .setPositiveButton("是", { d, i -> finish() })
                .setNegativeButton("否", { d, i ->})
                .show()
        }
    }

    private fun password_click(v : Button) {
        password.setText(password.text.toString() + v.text.toString())
    }
}