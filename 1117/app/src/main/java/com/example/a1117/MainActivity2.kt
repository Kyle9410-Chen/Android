package com.example.a1117

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Gravity
import android.widget.Button
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import kotlinx.android.synthetic.main.activity_main.*
import org.json.JSONArray
import org.json.JSONObject

class MainActivity2 : AppCompatActivity() {

    var data = ArrayList<user>()
    lateinit var jsonArray: JSONArray

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main2)

        getdata()
        b()
    }

    private fun getdata() {

        try {
            data = JSONArray(intent.extras?.getString("data")).let{
                jsonArray = it
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
            if(data.any { x -> x.account == account.text.toString() }){
                Toast.makeText(this@MainActivity2, "帳號已存在", Toast.LENGTH_SHORT).run {
                    this.setGravity(Gravity.CENTER,0,0)
                    this.show()
                }
                return@setOnClickListener
            }
            val json = JSONObject()
            json.put("account",account.text.toString())
            json.put("password",password.text.toString())
            jsonArray.put(json)
            val sp = getSharedPreferences("data", MODE_PRIVATE)
            sp.edit().putString("data",jsonArray.toString()).apply()
            Toast.makeText(this@MainActivity2, "註冊成功", Toast.LENGTH_SHORT).run {
                this.setGravity(Gravity.CENTER,0,0)
                this.show()
            }
            finish()

        }

        back.setOnClickListener {
            try {
                password.setText(password.text.toString().substring(0,password.text.toString().length-1))
            } catch (e: Exception) {
            }
        }

        end.setOnClickListener {
            AlertDialog.Builder(this@MainActivity2)
                .setTitle("是否返回")
                .setPositiveButton("是", { d, i -> finish() })
                .setNegativeButton("否", { d, i ->})
                .show()
        }
    }

    private fun password_click(v : Button) {
        password.setText(password.text.toString() + v.text.toString())
    }
}