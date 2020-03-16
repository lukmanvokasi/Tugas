package com.vokasi.mahasiswa

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
import kotlinx.android.synthetic.main.activity_main.*
import org.json.JSONArray
import org.json.JSONObject

class MainActivity : AppCompatActivity() {
    val context=this
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val recyclerView = findViewById(R.id.recyclerView) as RecyclerView
        recyclerView.layoutManager= LinearLayoutManager(this, LinearLayout.VERTICAL, false)

        getdariserver()
        button.setOnClickListener {
            var data1 = editText.text.toString()
            var data2 = editText2.text.toString()
            var data3 = editText3.text.toString()
            postkeserver(data1,data2,data3)
            //getdariserver()
        }
    }

    fun getdariserver() {
        val users=ArrayList<User>()

        AndroidNetworking.get("http://192.168.43.11/tugas/mptTugas/mptAPI2/mhs.php")
            .setPriority(Priority.MEDIUM)
            .build()
            .getAsJSONObject(object : JSONObjectRequestListener {
                override fun onResponse(response: JSONObject){
                    Log.e("_kotlinResponse", response.toString())

                    val jsonArray = response.getJSONArray("result")
                    for (i in 0 until jsonArray.length()){
                        val jsonObject = jsonArray.getJSONObject(i)
                        Log.e("_kotlinTitle", jsonObject.optString("Nama"))

                        var isi1=jsonObject.optInt("id_data").toString()
                        var isi2=jsonObject.optString("nama_mhs").toString()
                        var isi3=jsonObject.optString("nomer_mahasiswa").toString()
                        var isi4=jsonObject.optString("alamat_mahasiswa").toString()

                        users.add(User("$isi1", "$isi2","$isi3", "$isi4"))
                    }
                    val adapter=CustomAdapter(users)
                    recyclerView.adapter=adapter
                }

                override fun onError(anError: ANError) {
                    Log.i("_err", anError.toString())
                }
            })
    }
    fun postkeserver(data1: String, data2: String, data3: String){
        AndroidNetworking.post("http://192.168.43.11/tugas/mptTugas/mptAPI2/p-mhs.php")
            .addBodyParameter("nama_mhs",data1)
            .addBodyParameter("nomer_mahasiswa",data2)
            .addBodyParameter("alamat_mahasiswa",data3)
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
