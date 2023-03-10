package com.example.a1006

import android.content.DialogInterface
import android.content.Intent
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.graphics.Color
import android.graphics.PorterDuff
import android.graphics.drawable.BitmapDrawable
import android.graphics.drawable.Drawable
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Environment
import android.provider.CalendarContract
import android.provider.MediaStore
import android.util.Log
import android.view.ViewGroup
import android.widget.EditText
import android.widget.LinearLayout
import android.widget.SeekBar
import android.widget.Toast
import androidx.activity.result.contract.ActivityResultContracts
import androidx.appcompat.app.AlertDialog
import androidx.core.graphics.BitmapCompat
import androidx.core.graphics.drawable.toBitmap
import kotlinx.android.synthetic.main.activity_main3.*
import kotlinx.android.synthetic.main.activity_main3.clear
import kotlinx.android.synthetic.main.activity_main4.*
import java.io.ByteArrayOutputStream
import java.io.File
import java.io.FileOutputStream
import java.net.URI
import java.util.*
import kotlin.Exception

class MainActivity3 : AppCompatActivity() {

    lateinit var canvas : CanvasView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main3)


        b()
    }

    private fun b() {

        red.setOnClickListener {
            try {
                canvas.paint.color = Color.RED
                canvas.text_paint.color = Color.RED
            } catch (e: Exception) {
            }
        }

        green.setOnClickListener {
            try {
                canvas.paint.color = Color.GREEN
                canvas.text_paint.color = Color.GREEN
            } catch (e: Exception) {
            }
        }

        blue.setOnClickListener {
            try {
                canvas.paint.color = Color.BLUE
                canvas.text_paint.color = Color.BLUE
            } catch (e: Exception) {
            }
        }

        yellow.setOnClickListener {
            try {
                canvas.paint.color = Color.YELLOW
                canvas.text_paint.color = Color.YELLOW
            } catch (e: Exception) {
            }
        }

        black.setOnClickListener {
            try {
                canvas.paint.color = Color.BLACK
                canvas.text_paint.color = Color.BLACK
            } catch (e: Exception) {
            }
        }

        clear.setOnClickListener {
            try {
                val bitmap = canvas.defaultBitmap.copy(canvas.defaultBitmap.config,true)
                canvas.canvas.drawColor(Color.TRANSPARENT,PorterDuff.Mode.CLEAR)
                canvas.canvas.drawBitmap(bitmap,0f,0f,null)
            }
            catch (e : Exception){

            }
        }

        strokeWidth.setOnSeekBarChangeListener(object : SeekBar.OnSeekBarChangeListener{
            override fun onProgressChanged(p0: SeekBar?, p1: Int, p2: Boolean) {
                try {
                    canvas.paint.strokeWidth = p1.toFloat()
                    canvas.text_paint.textSize = p1.toFloat() * 4
                } catch (e: Exception) {
                }
            }

            override fun onStartTrackingTouch(p0: SeekBar?) {

            }

            override fun onStopTrackingTouch(p0: SeekBar?) {

            }
        })

        choose.setOnClickListener {
            val it = Intent(Intent.ACTION_PICK,MediaStore.Images.Media.INTERNAL_CONTENT_URI)
            startActivityForResult(it,1)
        }

        camera.setOnClickListener {
            val it = Intent(MediaStore.ACTION_IMAGE_CAPTURE)
            startActivityForResult(it,2)
        }

        save.setOnClickListener {
            try{
                val bytes = ByteArrayOutputStream()
                val c = Calendar.getInstance()
                canvas.bitmap.compress(Bitmap.CompressFormat.JPEG,60,bytes)
                Toast.makeText(this, Environment.getExternalStorageDirectory().toString() + File.separator, Toast.LENGTH_SHORT).show()
                val f = File(Environment.getExternalStorageDirectory().toString() + File.separator + "${c.get(Calendar.YEAR)}${c.get(Calendar.MONTH)}${c.get(Calendar.DAY_OF_MONTH)}${c.get(Calendar.HOUR_OF_DAY)}${c.get(Calendar.MINUTE)}${c.get(Calendar.SECOND)}")
                f.createNewFile()
                val fo = FileOutputStream(f)
                fo.write(bytes.toByteArray())
                fo.close()
            }
            catch (e : Exception){

            }
        }

        paint.setOnClickListener {
            try {
                canvas.mode = 0
            } catch (e: Exception) {
            }
        }

        circle.setOnClickListener {
            try {
                canvas.mode = 1
            } catch (e: Exception) {
            }
        }

        rect.setOnClickListener {
            try {
                canvas.mode = 2
            } catch (e: Exception) {
            }
        }

        line.setOnClickListener {
            try {
                canvas.mode = 3
            } catch (e: Exception) {
            }
        }

        text.setOnClickListener {
            try {
                val ab = AlertDialog.Builder(this).setTitle("請輸入顯示文字")
                val ed = EditText(this)
                ed.setHint("文字")
                ab.setView(ed)
                    .setPositiveButton("OK", DialogInterface.OnClickListener { dialogInterface, i ->
                        canvas.text = ed.text.toString()
                        canvas.mode = 4
                    })
                    .setNegativeButton("Cancle", DialogInterface.OnClickListener { dialogInterface, i ->  }).show()

            } catch (e: Exception) {
            }
        }

    }

    private fun canvas(bitmap : Bitmap) {
        canvas = CanvasView(this,Color.RED,10f,bitmap)
        canvas.layoutParams = ViewGroup.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT,LinearLayout.LayoutParams.MATCH_PARENT)
        main.addView(canvas)
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)

        if(requestCode == 1){
            if(resultCode == RESULT_OK){
                val uri = data?.data
                try{
                    main.removeView(canvas)
                }
                catch (e:Exception){

                }


                val bitmap = MediaStore.Images.Media.getBitmap(contentResolver,uri)
                canvas(bitmap)
            }
        }
        if(requestCode == 2){
            if(resultCode == RESULT_OK){
                try{
                    main.removeView(canvas)
                }
                catch (e:Exception){

                }


                val bitmap = data?.extras?.get("data") as Bitmap
                canvas(bitmap)
            }
        }
    }
}