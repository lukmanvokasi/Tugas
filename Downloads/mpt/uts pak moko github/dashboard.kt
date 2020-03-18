package com.vokasi.shared

import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.LinearLayout
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.androidnetworking.AndroidNetworking
import com.androidnetworking.common.Priority
import com.androidnetworking.error.ANError
import com.androidnetworking.interfaces.JSONArrayRequestListener
import com.androidnetworking.interfaces.JSONObjectRequestListener
import kotlinx.android.synthetic.main.activity_dashboard.*
import kotlinx.android.synthetic.main.activity_main.*
import org.json.JSONArray
import org.json.JSONObject

class dashboard : AppCompatActivity() {
    val context=this
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_dashboard)

        val recyclerView = findViewById(R.id.recyclerView) as RecyclerView
        recyclerView.layoutManager= LinearLayoutManager(this, LinearLayout.VERTICAL, false)

        button0.setOnClickListener{
            val sharedPreferences=getSharedPreferences("CEKLOGIN", Context.MODE_PRIVATE)
            val editor=sharedPreferences.edit()

            editor.putString("STATUS","0")
            editor.apply()

            startActivity(Intent(this@dashboard,MainActivity::class.java))
            finish()
        }

        getdariserver()
        button.setOnClickListener {
            var data1 = editText.text.toString()
            var data2 = editText2.text.toString()
            var data3 = editText3.text.toString()
            var data4 = editText4.text.toString()
            postkeserver(data1,data2,data3,data4)
            //getdariserver()
        }

    }

    fun getdariserver() {
        val users=ArrayList<User>()

        AndroidNetworking.get("http://192.168.43.11/tugas/mptTugas/mptAPI3/penduduk.php")
            .setPriority(Priority.MEDIUM)
            .build()
            .getAsJSONObject(object : JSONObjectRequestListener {
                override fun onResponse(response: JSONObject){
                    Log.e("_kotlinResponse", response.toString())

                    val jsonArray = response.getJSONArray("result")
                    for (i in 0 until jsonArray.length()){
                        val jsonObject = jsonArray.getJSONObject(i)
                        Log.e("_kotlinTitle", jsonObject.optString("Nama"))

                        var isi1=jsonObject.optInt("id_penduduk").toString()
                        var isi2=jsonObject.optString("nama_penduduk").toString()
                        var isi3=jsonObject.optString("ttl_penduduk").toString()
                        var isi4=jsonObject.optString("hp_penduduk").toString()
                        var isi5=jsonObject.optString("alamat_penduduk").toString()

                        users.add(User("$isi1", "$isi2","$isi3", "$isi4", "$isi5"))
                    }
                    val adapter=CustomAdapter(users)
                    recyclerView.adapter=adapter
                }

                override fun onError(anError: ANError) {
                    Log.i("_err", anError.toString())
                }
            })
    }
    fun postkeserver(data1: String, data2: String, data3: String, data4: String){
        AndroidNetworking.post("http://192.168.43.11/tugas/mptTugas/mptAPI3/p-penduduk.php")
            .addBodyParameter("nama_penduduk",data1)
            .addBodyParameter("ttl_penduduk",data2)
            .addBodyParameter("hp_penduduk",data3)
            .addBodyParameter("alamat_penduduk",data4)
            .setPriority(Priority.MEDIUM)
            .build()
            .getAsJSONArray(object : JSONArrayRequestListener {
                override fun onResponse(response: JSONArray){

                }

                override fun onError(error: ANError) {
                }
            })
        getdariserver()
        val intent0 = Intent(context,MainActivity::class.java)
        startActivity(intent0)
    }
}
