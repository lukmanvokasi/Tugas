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
import com.example.p2.tagline
import kotlinx.android.synthetic.main.activity_identitas.*
import kotlinx.android.synthetic.main.activity_tagline.*
import org.json.JSONArray
import org.json.JSONObject

class tagline : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_tagline)

        getdariserver4()

        val context=this

        home4.setOnClickListener {
            intent = Intent(context,MainActivity::class.java)
            startActivity(intent)
            finish()
        }
        update4.setOnClickListener {
            var data1 = datak4a.text.toString()
            postkeserver4(data1)
        }
    }

    fun getdariserver4(){
        AndroidNetworking.get("http://192.168.1.13/tugas/mptTugas/mptAPI1/tagline_json.php")
            .setPriority(Priority.MEDIUM)
            .build()
            .getAsJSONObject(object : JSONObjectRequestListener {
                override fun onResponse(response: JSONObject){
                    Log.e("_kotlinResponse", response.toString())

                    val jsonArray = response.getJSONArray("result")
                    for (i in 0 until jsonArray.length()){
                        val jsonObject = jsonArray.getJSONObject(i)
                        Log.e("_kotlinTitle", jsonObject.optString("isi_tagline"))
                        txtk4a.setText(jsonObject.optString("isi_tagline"))
                        datak4a.setText(jsonObject.optString("isi_tagline"))
                    }
                }

                override fun onError(anError: ANError) {
                    Log.i("_err", anError.toString())
                }
            })
    }
    fun postkeserver4(data1: String){
        AndroidNetworking.post("http://192.168.1.13/tugas/mptTugas/mptAPI1/proses-tagline.php")
            .addBodyParameter("isi_tagline",data1)
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
