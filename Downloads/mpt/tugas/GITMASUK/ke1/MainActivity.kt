package com.vokasi.tugas1

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        var nilai= 70
        if (nilai>=60 && nilai<=100)
        {
            Log.i("Hasil", "Nilai anda A")
            tampil.setText("Nilai anda A")
        }
        else (nilai>=40 && nilai <=60)
        {
            Log.i("Hasil", "Nilai anda B")
            tampil.setText("Nilai anda B")
        }

    }
}
