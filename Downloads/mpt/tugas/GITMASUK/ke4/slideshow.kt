package com.example.p2

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
import kotlinx.android.synthetic.main.activity_identitas.*
import kotlinx.android.synthetic.main.activity_marquee.*
import kotlinx.android.synthetic.main.activity_slideshow.*
import org.json.JSONArray
import org.json.JSONObject


class slideshow : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_slideshow)

        val context=this
        home6.setOnClickListener {
            intent = Intent(context, MainActivity::class.java)
            startActivity(intent)
            finish()
        }

        val recyclerView = findViewById(R.id.recyclerView) as RecyclerView
        recyclerView.layoutManager= LinearLayoutManager(this, LinearLayout.VERTICAL, false)

        val users=ArrayList<User>()

        AndroidNetworking.get("http://192.168.1.13/tugas/mptTugas/mptAPI1/slideshow_json.php")
            .setPriority(Priority.MEDIUM)
            .build()
            .getAsJSONObject(object : JSONObjectRequestListener {
                override fun onResponse(response: JSONObject){
                    Log.e("_kotlinResponse", response.toString())

                    val jsonArray = response.getJSONArray("result")
                    for (i in 0 until jsonArray.length()){
                        val jsonObject = jsonArray.getJSONObject(i)
                        Log.e("_kotlinTitle", jsonObject.optString("id"))

                        var isi1=jsonObject.optString("id").toString()
                        var isi2=jsonObject.optString("judul").toString()
                        var isi3=jsonObject.optString("uri").toString()

                        users.add(User("$isi1", "$isi2","$isi3"))
                    }
                    val adapter=CustomAdapter(users)
                    recyclerView.adapter=adapter
                }

                override fun onError(anError: ANError) {
                    Log.i("_err", anError.toString())
                }
            })


    }


}
