package com.example.learning226.staractivity

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.AdapterView
import android.widget.ListView
import androidx.appcompat.app.AppCompatActivity
import com.example.learning226.R
import com.example.learning226.backgroundprocesses.broadcastreceiver.ModeReceiverMain
import com.example.learning226.learningadapter.customlistviewarrayadapter.Model

class StarActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_star)
        val listView = findViewById<ListView>(R.id.starlistview)
        val list = mutableListOf<Model>()

        list.add(Model("Mobile Data", "", R.drawable.profile_white_foreground))
        list.add(Model("AirplaneMode", "", R.drawable.profile_white_foreground))
        list.add(Model("Ringer", "", R.drawable.profile_white_foreground))
        list.add(Model("Image", "", R.drawable.profile_white_foreground))

        listView.adapter = MyAdapter(this@StarActivity, R.layout.listviewrow, list)
        listView.setOnItemClickListener{ parent: AdapterView<*>, view: View, position: Int, id: Long ->
            if (position == 0) {
                startActivity(Intent(this@StarActivity, ModeReceiverMain::class.java))
            }else if(position == 1){
                startActivity(Intent(this@StarActivity, ModeReceiverMain::class.java))
            }else if(position == 2){
                startActivity(Intent(this@StarActivity, ModeReceiverMain::class.java))
            } else if(position == 3){
                startActivity(Intent(this@StarActivity, ImageCoroutine::class.java))
            }
        }

    }
}