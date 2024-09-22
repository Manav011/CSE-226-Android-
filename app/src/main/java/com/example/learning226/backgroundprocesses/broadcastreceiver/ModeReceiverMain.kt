package com.example.learning226.backgroundprocesses.broadcastreceiver

import android.content.Intent
import android.content.IntentFilter
import android.media.AudioManager
import android.net.ConnectivityManager
import android.os.BatteryManager
import android.os.Bundle
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity
import com.example.learning226.R

class ModeReceiverMain : AppCompatActivity() {
    private lateinit var receiver: ModeChangeReceiver
    private lateinit var airplanemode: Button
    private lateinit var ringermode: Button
    private lateinit var internet: Button
    private lateinit var battery: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_mode_receiver_main)
        airplanemode = findViewById(R.id.AirplaneMode)
        ringermode = findViewById(R.id.RingerMode)
        internet = findViewById(R.id.Internet)
        battery = findViewById(R.id.Battery)

        receiver = ModeChangeReceiver()
        airplanemode.setOnClickListener{
            IntentFilter(Intent.ACTION_AIRPLANE_MODE_CHANGED).also {
                registerReceiver(receiver, it)
            }
        }

        ringermode.setOnClickListener{
            IntentFilter(AudioManager.RINGER_MODE_CHANGED_ACTION).also{
                registerReceiver(receiver, it)
            }
        }

        internet.setOnClickListener{
            IntentFilter(ConnectivityManager.CONNECTIVITY_ACTION).also{
                registerReceiver(receiver, it)
            }
        }

        battery.setOnClickListener{
            IntentFilter(Intent.ACTION_BATTERY_CHANGED).also {
                registerReceiver(receiver, it)
            }
        }
    }

    override fun onStop() {
        super.onStop()
        unregisterReceiver(receiver)
    }
}