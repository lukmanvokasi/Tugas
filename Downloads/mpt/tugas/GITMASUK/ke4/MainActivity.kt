package com.example.p2

import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import com.androidnetworking.AndroidNetworking
import com.androidnetworking.common.Priority
import com.androidnetworking.error.ANError
import com.androidnetworking.interfaces.JSONArrayRequestListener
import com.androidnetworking.interfaces.JSONObjectRequestListener
import kotlinx.android.synthetic.main.activity_main.*
import okhttp3.Response
import org.json.JSONArray
import org.json.JSONObject


class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val context=this

        btn1m.setOnClickListener {
            val intent = Intent(context,sholat::class.java)
            startActivity(intent)
            finish()
        }
        btn2m.setOnClickListener {
            val intent = Intent(context,identitas::class.java)
            startActivity(intent)
            finish()
        }
        btn3m.setOnClickListener {
            val intent = Intent(context,pengumuman::class.java)
            startActivity(intent)
            finish()
        }
        btn4m.setOnClickListener {
            val intent = Intent(context,tagline::class.java)
            startActivity(intent)
            finish()
        }
        btn5m.setOnClickListener {
            val intent = Intent(context,marquee::class.java)
            startActivity(intent)
            finish()
        }
        btn6m.setOnClickListener {
            val intent = Intent(context,slideshow::class.java)
            startActivity(intent)
            finish()
        }

    }
}
