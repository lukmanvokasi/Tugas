package com.vokasi.mpt301

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import kotlinx.android.synthetic.main.activity_main.*
import kotlin.math.log

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        jumlahkan.setOnClickListener(){
            var bilangan1: String = bil1.text.toString()
            var bilangan2: String = bil2.text.toString()

            var a: Int = bilangan1.toInt()
            var b: Int = bilangan2.toInt()

            var c: Int = a+b

            hasil.setText(c.toString())
        }

        kurangkan.setOnClickListener(){
            kurangi()
        }

//        var nilai= 70
//        if nilai(>=90 && nilai<=100)
//        {
//            Log.i("Hasil", "Nilai anda A");
//            tampil.text"Nilai anda A"
//
//        else nilai(<=45 && nilai <=50)
//        {
//            Log.i("Hasil", "Nilai anda E")
//            tampil.text"Nilai anda B"
//        }

//            for (x :  in 0..10)
//                Log.i("Hasil", "$x")

    } // penutup onCreate
//    fun main(args: Array<String>) {
//        var i = 1
//        while (i <= 5) {
//            println("Line $1")
//            ++i
//        }
//    }

    fun kurangi() {
        var bilangan1: String = bil1.text.toString()
        var bilangan2: String = bil2.text.toString()

        var a: Int = bilangan1.toInt()
        var b: Int = bilangan2.toInt()

        var c: Int = -(a-b)

        hasil.setText(c.toString())
    }
} // penutup class
