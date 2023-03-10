package com.example.a0922

import android.content.DialogInterface
import android.graphics.drawable.Drawable
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.view.ViewGroup
import android.widget.LinearLayout
import androidx.appcompat.app.AlertDialog
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.activity_main.view.*
import java.util.*
import kotlin.collections.ArrayList
import kotlin.concurrent.timerTask

class MainActivity : AppCompatActivity() {

    lateinit var pic : ArrayList<Int>
    var index = 0
    var l = false
    lateinit var timer : Timer

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        pic = ArrayList()
        pic.add(R.drawable.p01)
        pic.add(R.drawable.p02)
        pic.add(R.drawable.p03)
        pic.add(R.drawable.p04)
        pic.add(R.drawable.p05)
        pic.add(R.drawable.p06)
        pic.add(R.drawable.p07)
        pic.add(R.drawable.p08)
        pic.add(R.drawable.p09)
        pic.add(R.drawable.p10)
        pic.add(R.drawable.p11)
        pic.add(R.drawable.p12)
        pic.add(R.drawable.p13)

        number.setText("(1/${pic.size})")
        timer = Timer()

        b()
    }

    private fun b() {

        next.setOnClickListener {
            try{
                if(l and (index == 12)) index = 0
                else index++
                image.setImageDrawable(resources.getDrawable(pic[index]))
                image.setTag(pic[index])

            }
            catch (e : Exception){
                index = 12
                AlertDialog.Builder(this)
                    .setTitle("警告")
                    .setMessage("已為最後一張")
                    .show()
            }
            finally {
                number.setText("(${index+1}/${pic.size})")
                filename.setText(resources.getResourceEntryName(image.tag.toString().toInt()))
            }

        }

        last.setOnClickListener {
            try{
                if(l and (index == 0)) index = 12
                else index--
                image.setImageDrawable(resources.getDrawable(pic[index]))
                image.setTag(pic[index])

            }
            catch (e : Exception){
                index = 0
                AlertDialog.Builder(this)
                    .setTitle("警告")
                    .setMessage("已為第一張")
                    .show()
            }
            finally {
                number.setText("(${index+1}/${pic.size})")
                filename.setText(resources.getResourceEntryName(image.tag.toString().toInt()))
            }
        }

        zoom_in.setOnClickListener {
            val width = image.width
            val height = image.height
            image.layoutParams = LinearLayout.LayoutParams((width*1.5).toInt(),(height*1.5).toInt())
        }

        zoom_out.setOnClickListener {
            val width = image.width
            val height = image.height
            if(width <= 10) return@setOnClickListener
            image.layoutParams = LinearLayout.LayoutParams((width/1.5).toInt(),(height/1.5).toInt())
        }

        show.setOnCheckedChangeListener { compoundButton, b ->
            if(b){
                image.visibility = View.VISIBLE
            }
            else{
                image.visibility = View.INVISIBLE
            }
        }

        loop.setOnCheckedChangeListener { compoundButton, b ->
            l = b
        }

        auto.setOnClickListener {

            timer.schedule(timerTask {
                kotlin.run {
                    runOnUiThread {
                        try{
                            if(l and (index == 12)) index = 0
                            else index++
                            image.setImageDrawable(resources.getDrawable(pic[index]))
                            image.setTag(pic[index])

                        }
                        catch (e : Exception){
                            index = 12
                            AlertDialog.Builder(this@MainActivity)
                                .setTitle("警告")
                                .setMessage("已為最後一張")
                                .show()
                        }
                        finally {
                            number.setText("(${index+1}/${pic.size})")
                            filename.setText(resources.getResourceEntryName(image.tag.toString().toInt()))
                        }
                    }
                }
            },0,1000)

            auto.visibility = View.GONE
            stop.visibility = View.VISIBLE
            order.visibility = View.GONE
            loop.isChecked = true
        }

        stop.setOnClickListener {
            timer.cancel()
            timer = Timer()

            auto.visibility = View.VISIBLE
            stop.visibility = View.GONE
            order.visibility = View.VISIBLE
        }

    }
}
