package com.example.learning226.learningadapter.customgridview

import android.os.Bundle
import android.widget.GridView
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.learning226.R

class CustomGridViewExample : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
//        enableEdgeToEdge()
        setContentView(R.layout.activity_custom_grid_view_example)
        val gridView = findViewById<GridView>(R.id.gridViewexample)

        val Imagelist = ArrayList<Int>()

        Imagelist.add(R.drawable.youtube)
        Imagelist.add(R.drawable.facebook)
        Imagelist.add(R.drawable.skype)
        Imagelist.add(R.drawable.twitter)
        Imagelist.add(R.drawable.whatsapp)
        Imagelist.add(R.drawable.baseline_audiotrack_24)
        Imagelist.add(R.drawable.ic_launcher_foreground)

        val name = arrayOf("youtube", "facebook", "skype", "twitter", "whatsapp", "audio", "android")

        val Gridadapter = CustomGridViewAdapter(this@CustomGridViewExample, Imagelist, name)

        gridView.adapter = Gridadapter

        gridView.setOnItemClickListener{adapterView, parent, position, l ->
            Toast.makeText(this@CustomGridViewExample, name[position], Toast.LENGTH_SHORT).show()
        }
    }
}