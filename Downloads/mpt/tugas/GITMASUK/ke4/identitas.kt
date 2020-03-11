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
import org.json.JSONArray
import org.json.JSONObject

class identitas : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_identitas)

        val context=this

        getdariserver2()

        home2.setOnClickListener {
            val intent2 = Intent(context,MainActivity::class.java)
            startActivity(intent2)
            finish()
        }
        update2.setOnClickListener {
            var data1 = datak2a.text.toString()
            var data2 = datak2b.text.toString()
            postkeserver2(data1,data2)
        }

    }

    fun getdariserver2() {
        AndroidNetworking.get("http://192.168.1.13/tugas/mptTugas/mptAPI1/nama_masjid_json.php")
            .setPriority(Priority.MEDIUM)
            .build()
            .getAsJSONObject(object : JSONObjectRequestListener {
                override fun onResponse(response: JSONObject){
                    Log.e("_kotlinResponse", response.toString())

                    val jsonArray = response.getJSONArray("result")
                    for (i in 0 until jsonArray.length()){
                        val jsonObject = jsonArray.getJSONObject(i)
                        Log.e("_kotlinTitle", jsonObject.optString("Nama"))
                        txtk2a.setText(jsonObject.optString("Nama"))
                        txtk2b.setText(jsonObject.optString("Alamat"))

                        datak2a.setText(jsonObject.optString("Nama"))
                        datak2b.setText(jsonObject.optString("Alamat"))
                    }
                }

                override fun onError(anError: ANError) {
                    Log.i("_err", anError.toString())
                }
            })
    }
    fun postkeserver2(data1: String, data2: String){
        AndroidNetworking.post("http://192.168.1.13/tugas/mptTugas/mptAPI1/proses-identitas.php")
            .addBodyParameter("nama_masjid",data1)
            .addBodyParameter("alamat_masjid",data2)
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
