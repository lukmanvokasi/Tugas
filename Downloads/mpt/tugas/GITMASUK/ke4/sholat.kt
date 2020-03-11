package com.example.p2

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import com.androidnetworking.AndroidNetworking
import com.androidnetworking.common.Priority
import com.androidnetworking.error.ANError
import com.androidnetworking.interfaces.JSONArrayRequestListener
import com.androidnetworking.interfaces.JSONObjectRequestListener
import kotlinx.android.synthetic.main.activity_identitas.*
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.activity_sholat.*
import org.json.JSONArray
import org.json.JSONObject

class sholat : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_sholat)

        val context=this
        getdariserver()

        home1.setOnClickListener {
            val intent = Intent(context,MainActivity::class.java)
            startActivity(intent)
            finish()
        }
        update1.setOnClickListener {
            var data1 = datak1a.text.toString()
            var data2 = datak1b.text.toString()
            var data3 = datak1c.text.toString()
            var data4 = datak1d.text.toString()
            var data5 = datak1e.text.toString()
            var data6 = datak1f.text.toString()
            postkeserver(data1,data2,data3,data4,data5,data6)
        }
    }
    fun getdariserver(){
        AndroidNetworking.get("http://192.168.1.13/tugas/mptTugas/mptAPI1/contoh_jadwal_json.php")
            .setPriority(Priority.MEDIUM)
            .build()
            .getAsJSONObject(object : JSONObjectRequestListener {
                override fun onResponse(response: JSONObject){
                    Log.e("_kotlinResponse", response.toString())

                    val jsonArray = response.getJSONArray("result")
                    for (i in 0 until jsonArray.length()){
                        val jsonObject = jsonArray.getJSONObject(i)
                        Log.e("_kotlinTitle", jsonObject.optString("shubuh"))
                        txtk1a.setText(jsonObject.optString("shubuh"))
                        txtk1b.setText(jsonObject.optString("dhuhur"))
                        txtk1c.setText(jsonObject.optString("ashar"))
                        txtk1d.setText(jsonObject.optString("maghrib"))
                        txtk1e.setText(jsonObject.optString("isha"))
                        txtk1f.setText(jsonObject.optString("dhuha"))

                        datak1a.setText(jsonObject.optString("shubuh"))
                        datak1b.setText(jsonObject.optString("dhuhur"))
                        datak1c.setText(jsonObject.optString("ashar"))
                        datak1d.setText(jsonObject.optString("maghrib"))
                        datak1e.setText(jsonObject.optString("isha"))
                        datak1f.setText(jsonObject.optString("dhuha"))
                    }
                }

                override fun onError(anError: ANError) {
                    Log.i("_err", anError.toString())
                }
            })
    }
    fun postkeserver(data1: String, data2: String, data3: String, data4: String, data5: String, data6: String){
        AndroidNetworking.post("http://192.168.1.13/tugas/mptTugas/mptAPI1/proses-jadwal.php")
            .addBodyParameter("shubuh",data1)
            .addBodyParameter("dhuhur",data2)
            .addBodyParameter("ashar",data3)
            .addBodyParameter("maghrib",data4)
            .addBodyParameter("isha",data5)
            .addBodyParameter("dhuha",data6)
            .setPriority(Priority.MEDIUM)
            .build()
            .getAsJSONArray(object : JSONArrayRequestListener {
                override fun onResponse(response: JSONArray){

                }

                override fun onError(error: ANError) {
                }
            })
    }


}
