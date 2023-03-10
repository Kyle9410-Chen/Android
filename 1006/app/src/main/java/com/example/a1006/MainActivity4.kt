package com.example.a1006

import android.Manifest
import android.content.Context
import android.content.DialogInterface
import android.content.Intent
import android.content.SharedPreferences
import android.content.pm.PackageManager
import android.location.Geocoder
import android.location.Location
import android.location.LocationListener
import android.location.LocationManager
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.*
import androidx.appcompat.app.AlertDialog
import androidx.core.app.ActivityCompat
import com.google.android.material.navigation.NavigationBarView
import kotlinx.android.synthetic.main.activity_main4.*
import org.json.JSONArray
import org.json.JSONObject
import java.text.DecimalFormat
import kotlin.math.roundToLong

class MainActivity4 : AppCompatActivity() {

    lateinit var LM : LocationManager
    lateinit var geo : Geocoder
    lateinit var list : ArrayList<data>
    lateinit var name_list : ArrayList<String>
    lateinit var json : JSONArray
    lateinit var sp : SharedPreferences

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main4)

        getdata()
        getaddress()
        b()
    }

    private fun getdata() {
        list = ArrayList()
        name_list = ArrayList()
        sp = getSharedPreferences("data", MODE_PRIVATE)
        val data = sp.getString("data","[]")
        json = JSONArray(data)
        for (i in 0 until json.length()){
            list.add(data(
                json.getJSONObject(i).getString("name"),
                json.getJSONObject(i).getString("address"),
                json.getJSONObject(i).getString("x"),
                json.getJSONObject(i).getString("y"),
            ))
            name_list.add(json.getJSONObject(i).getString("name"))
        }

        chooser.adapter = ArrayAdapter(this, androidx.appcompat.R.layout.support_simple_spinner_dropdown_item,name_list)
    }

    private fun b() {

        save_data.setOnClickListener {
            val jsondata = JSONObject()
            var flag = false
            val ab = AlertDialog.Builder(this).setTitle("請輸入資料名稱")
            val ed = EditText(this)
            ed.setHint("名稱")
            ab.setView(ed)
                .setPositiveButton("OK", DialogInterface.OnClickListener { dialogInterface, i ->
                    jsondata.put("name",ed.text)
                    jsondata.put("address",address.text)
                    jsondata.put("x",x.text)
                    jsondata.put("y",y.text)
                    json.put(jsondata)
                    Log.e("e",json.toString())
                    sp.edit().putString("data",json.toString()).commit()
                    getdata()
                })
                .setNegativeButton("Cancle", DialogInterface.OnClickListener { dialogInterface, i ->  }).show()
        }

        chooser.onItemSelectedListener = object : AdapterView.OnItemSelectedListener{
            override fun onItemSelected(p0: AdapterView<*>?, p1: View?, p2: Int, p3: Long) {
                show_name.text = list[p2].name
                show_address.text = list[p2].address
                show_x.text = list[p2].x
                show_y.text = list[p2].y
            }

            override fun onNothingSelected(p0: AdapterView<*>?) {
                TODO("Not yet implemented")
            }
        }

        map_data.setOnClickListener {
            val uri = Uri.parse("geo:${show_y.text},${show_x.text}")
            val it = Intent(Intent.ACTION_VIEW,uri)
            startActivity(it)
        }

        clear.setOnClickListener {
            AlertDialog.Builder(this)
                .setTitle("確定清空")
                .setMessage("你的資料將不再存在")
                .setPositiveButton("Ok", DialogInterface.OnClickListener { dialogInterface, i ->
                    sp.edit().clear().commit()
                    getdata()
                    show_name.text = ""
                    show_address.text = ""
                    show_x.text = ""
                    show_y.text = ""
                })
                .setNegativeButton("Cancle", DialogInterface.OnClickListener { dialogInterface, i ->  }).show()
        }
    }

    private fun getaddress() {
        geo = Geocoder(this)
        LM = getSystemService(Context.LOCATION_SERVICE) as LocationManager
        val l = if (ActivityCompat.checkSelfPermission(this,
                Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(
                this,
                Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED
        ) {

            return
        } else LM.getLastKnownLocation(LocationManager.GPS_PROVIDER)

        val format = DecimalFormat("#.####")
        val longitude = l!!.longitude
        val latitude = l!!.latitude
        x.text = format.format(longitude)
        y.text = format.format(latitude)

        val data_address = geo.getFromLocation(latitude,longitude,1)

        address.text = data_address.get(0).getAddressLine(0)

        LM.requestLocationUpdates(LocationManager.GPS_PROVIDER,2000,1f,object :LocationListener{
            override fun onLocationChanged(p0: Location) {
                val l = if (ActivityCompat.checkSelfPermission(this@MainActivity4,
                        Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(
                        this@MainActivity4,
                        Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED
                ) {

                    return
                } else LM.getLastKnownLocation(LocationManager.GPS_PROVIDER)

                val format = DecimalFormat("#.####")
                val longitude = l!!.longitude
                val latitude = l!!.latitude
                x.text = format.format(longitude)
                y.text = format.format(latitude)

                val data_address = geo.getFromLocation(latitude,longitude,1)

                address.text = data_address.get(0).getAddressLine(0)
            }

        })
    }

    class data(name:String,address:String,x:String,y:String){
        val name = name
        val address = address
        val x = x
        val y = y
    }
}