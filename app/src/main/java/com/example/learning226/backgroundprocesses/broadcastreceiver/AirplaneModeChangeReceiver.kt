package com.example.learning226.backgroundprocesses.broadcastreceiver

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.media.AudioManager
import android.net.ConnectivityManager
import android.net.NetworkCapabilities
import android.os.Build
import android.widget.Toast

class AirplaneModeChangeReceiver: BroadcastReceiver() {
    override fun onReceive(context: Context?, intent: Intent?) {
        when(intent?.action){
            Intent.ACTION_AIRPLANE_MODE_CHANGED -> {
                val isAirplaneModeEnabled = intent.getBooleanExtra("state", false) ?: return
                if(isAirplaneModeEnabled){
                    Toast.makeText(context, "Airplane Mode Enabled", Toast.LENGTH_SHORT).show()
                }else{
                    Toast.makeText(context, "Airplane Mode Disabled", Toast.LENGTH_SHORT).show()
                }
            }
            AudioManager.RINGER_MODE_CHANGED_ACTION -> {
                val audioManager = context?.getSystemService(Context.AUDIO_SERVICE) as AudioManager
                val message = when(audioManager.ringerMode){
                    AudioManager.RINGER_MODE_SILENT -> "Silent Mode On"
                    AudioManager.RINGER_MODE_VIBRATE -> "Vibrate Mode On"
                    AudioManager.RINGER_MODE_NORMAL -> "Normal Mode On"
                    else -> "Unknown Ringer Mode"
                }
                Toast.makeText(context, message, Toast.LENGTH_SHORT).show()
            }
            ConnectivityManager.CONNECTIVITY_ACTION -> {
                val isConnected  = checkInternetConnection(context)
                val message = if(isConnected){"Internet Connected"}else{"Internet Disconnected"}
                Toast.makeText(context, message, Toast.LENGTH_SHORT).show()
            }
        }
    }

    private fun checkInternetConnection(context: Context?): Boolean {
        val connectivityManager = context?.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            val activeNetwork = connectivityManager.activeNetwork ?: return false
            val networkCapabilities = connectivityManager.getNetworkCapabilities(activeNetwork) ?: return false
            return networkCapabilities.hasCapability(NetworkCapabilities.NET_CAPABILITY_INTERNET)
        } else {
            val activeNetworkInfo = connectivityManager.activeNetworkInfo
            return activeNetworkInfo != null && activeNetworkInfo.isConnected
        }
    }
}
