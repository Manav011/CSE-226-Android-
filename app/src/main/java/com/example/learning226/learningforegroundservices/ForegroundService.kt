package com.example.learning226.learningforegroundservices

import android.app.NotificationChannel
import android.app.NotificationManager
import android.app.PendingIntent
import android.app.Service
import android.content.Intent
import android.content.pm.ServiceInfo.FOREGROUND_SERVICE_TYPE_LOCATION
import android.os.Build
import android.os.IBinder
import androidx.annotation.RequiresApi
import androidx.core.app.NotificationCompat
import com.example.learning226.R

class ForegroundService : Service(){
    private val channelID = "ForegroundService Kotlin"
    override fun onBind(intent: Intent?): IBinder? {
        TODO("Not yet implemented")
    }

    @RequiresApi(Build.VERSION_CODES.Q)
    override fun onStartCommand(intent: Intent?, flags: Int, startId: Int): Int {
        super.onStartCommand(intent, flags, startId)
        val input = intent?.getStringExtra("inputExtra")
//        createNotificationChannel()

        val serviceChannel = if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.O){
            NotificationChannel(channelID, "Foreground Service Channel", NotificationManager.IMPORTANCE_DEFAULT)
        }else{
            TODO("Version.SDK_INT < 0")
        }
        val manager = getSystemService(NotificationManager::class.java)
        manager!!.createNotificationChannel(serviceChannel)

        val notificationIntent = Intent(this@ForegroundService, ForegroundServicesMain::class.java)

        val pendingIntent = PendingIntent.getActivity(this@ForegroundService, 0, notificationIntent, PendingIntent.FLAG_IMMUTABLE)
        val notification = NotificationCompat.Builder(this@ForegroundService, channelID)
            .setContentTitle("Foreground Service Kotlin Example")
            .setContentText(input)
            .setSmallIcon(R.drawable.skype)
            .setContentIntent(pendingIntent)
            .build()

        startForeground(1, notification, FOREGROUND_SERVICE_TYPE_LOCATION)

        return START_NOT_STICKY
    }

//    private fun createNotificationChannel(){
//        val serviceChannel = if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.O){
//            NotificationChannel(channelID, "Foreground Service Channel", NotificationManager.IMPORTANCE_DEFAULT)
//        }else{
//            TODO("Version.SDK_INT < 0")
//        }
//        val manager = getSystemService(NotificationManager::class.java)
//        manager!!.createNotificationChannel(serviceChannel)
//    }
}
