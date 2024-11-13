package com.example.learning226.backgroundprocesses.learningforegroundservices

import android.content.Intent
import android.content.pm.PackageManager
import android.os.Build
import android.os.Bundle
import android.widget.Button
import androidx.annotation.RequiresApi
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import com.example.learning226.R

class ForegroundServicesMain : AppCompatActivity() {
    private lateinit var startbtn: Button
    private lateinit var stopbtn: Button
    private val LOCATION_PERMISSION_REQUEST_CODE = 1001

    @RequiresApi(Build.VERSION_CODES.UPSIDE_DOWN_CAKE)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
//        enableEdgeToEdge()
        setContentView(R.layout.activity_foreground_services_main)

        startbtn = findViewById(R.id.foregroundstartbtn)
        stopbtn = findViewById(R.id.foregroundstopbtn)

        startbtn.setOnClickListener{
            if(hasPermissions1()){
                startForegroundService()
            }else{
                requestPermissions()
            }
        }

        stopbtn.setOnClickListener{
            stopForegroundService()
        }
    }

    @RequiresApi(Build.VERSION_CODES.UPSIDE_DOWN_CAKE)
    private fun hasPermissions1(): Boolean{
        return ContextCompat.checkSelfPermission(this@ForegroundServicesMain,  android.Manifest.permission.FOREGROUND_SERVICE_LOCATION) == PackageManager.PERMISSION_GRANTED &&
                ContextCompat.checkSelfPermission(this@ForegroundServicesMain, android.Manifest.permission.ACCESS_COARSE_LOCATION) == PackageManager.PERMISSION_GRANTED &&
                ContextCompat.checkSelfPermission(this@ForegroundServicesMain, android.Manifest.permission.ACCESS_FINE_LOCATION) == PackageManager.PERMISSION_GRANTED
    }

    @RequiresApi(Build.VERSION_CODES.UPSIDE_DOWN_CAKE)
    private fun requestPermissions(){
        ActivityCompat.requestPermissions(this@ForegroundServicesMain,
            arrayOf(android.Manifest.permission.FOREGROUND_SERVICE_LOCATION,
                    android.Manifest.permission.ACCESS_COARSE_LOCATION,
                    android.Manifest.permission.ACCESS_FINE_LOCATION
            ),
            LOCATION_PERMISSION_REQUEST_CODE
        )
    }

    private fun startForegroundService(){
        val startIntent = Intent(this@ForegroundServicesMain, ForegroundService::class.java)
        startIntent.putExtra("inputExtra", "Foreground services are running....")
        ContextCompat.startForegroundService(this@ForegroundServicesMain, startIntent)
    }

    private fun stopForegroundService(){
        val stopIntent = Intent(this@ForegroundServicesMain, ForegroundService::class.java)
        stopService(stopIntent)
    }

    override fun onRequestPermissionsResult(
        requestCode: Int,
        permissions: Array<out String>,
        grantResults: IntArray
    ) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)
        if(requestCode == LOCATION_PERMISSION_REQUEST_CODE){
            if(grantResults.isNotEmpty() && grantResults.all{it == PackageManager.PERMISSION_GRANTED}){
                startForegroundService()
            }else{
                // todo
            }
        }
    }
}