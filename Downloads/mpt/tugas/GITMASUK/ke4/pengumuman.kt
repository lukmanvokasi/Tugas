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
import kotlinx.android.synthetic.main.activity_pengumuman.*
import org.json.JSONArray
import org.json.JSONObject

class pengumuman : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_pengumuman)

        getdariserver3()

        val context=this

        home3.setOnClickListener {
            intent = Intent(context,MainActivity::class.java)
            startActivity(intent)
            finish()
        }
        update3.setOnClickListener {
            var data1 = datak3a.text.toString()
            var data2 = datak3b.text.toString()
            postkeserver3(data1,data2)
        }
    }

    fun getdariserver3(){
        AndroidNetworking.get("http://192.168.1.13/tugas/mptTugas/mptAPI1/pengumuman_json.php")
            .setPriority(Priority.MEDIUM)
            .build()
            .getAsJSONObject(object : JSONObjectRequestListener {
                override fun onResponse(response: JSONObject){
                    Log.e("_kotlinResponse", response.toString())

                    val jsonArray = response.getJSONArray("result")
                    for (i in 0 until jsonArray.length()){
                        val jsonObject = jsonArray.getJSONObject(i)
                        Log.e("_kotlinTitle", jsonObject.optString("judul_pengumuman"))
                        judul3.setText(jsonObject.optString("judul_pengumuman"))
                        txtk3a.setText(jsonObject.optString("isi_pengumuman"))

                        datak3a.setText(jsonObject.optString("judul_pengumuman"))
                        datak3b.setText(jsonObject.optString("isi_pengumuman"))
                    }
                }

                override fun onError(anError: ANError) {
                    Log.i("_err", anError.toString())
                }
            })
    }
    fun postkeserver3(data1: String, data2: String){
        AndroidNetworking.post("http://192.168.1.13/tugas/mptTugas/mptAPI1/proses-pengumuman.php")
            .addBodyParameter("judul_pengumuman",data1)
            .addBodyParameter("isi_pengumuman",data2)
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
