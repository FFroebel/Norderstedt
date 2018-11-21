package com.example.frfr01.norderstedt

import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    //TODO implement BLE connection from BleActivity or set this as an start activity
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val start = findViewById<Button>(R.id.button_start)
       button_start.setOnClickListener {
           val intent = Intent(this, MapsActivity::class.java)
           startActivity(intent)
       }
    }
}
