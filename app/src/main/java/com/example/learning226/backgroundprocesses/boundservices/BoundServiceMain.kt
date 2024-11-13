package com.example.learning226.backgroundprocesses.boundservices

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.learning226.R

class BoundServiceMain : AppCompatActivity() {
    lateinit var start: Button
    lateinit var stop: Button
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_bound_service_main)

        start = findViewById(R.id.boundservicestartbtn)
        stop = findViewById(R.id.boundservicestopbtn)

        start.setOnClickListener{
            startService(Intent(this, NewService::class.java))
        }

        stop.setOnClickListener {
            stopService(Intent(this, NewService::class.java))
        }

    }
}