package com.example.learning226.dynamicallyaddingitemusingadapter

import android.os.Bundle
import android.widget.EditText
import android.widget.ListView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.learning226.R
import com.google.android.material.button.MaterialButton
import kotlin.random.Random

class DynamicallyAddingUsingAdapter : AppCompatActivity() {
    lateinit var et1: EditText
    lateinit var et2: EditText
    lateinit var btn: MaterialButton

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_dynamically_adding_using_adapter)
        var listview = findViewById<ListView>(R.id.dynamiclistview)
        var list = mutableListOf<DModel>()
        et1 = findViewById(R.id.dynamicet1)
        et2 = findViewById(R.id.dynamicet2)
        btn = findViewById(R.id.dynamicbtn)
        btn.elevation = 100.0F

        var adapter = CustomDynamicAdapter(this@DynamicallyAddingUsingAdapter, R.layout.dynamicaddinglistitem, list, true)
        listview.adapter = adapter

        var ImageList = ArrayList<Int>()
        ImageList.add(R.drawable.twitter)
        ImageList.add(R.drawable.facebook)
        ImageList.add(R.drawable.skype)
        ImageList.add(R.drawable.youtube)
        ImageList.add(R.drawable.spotify)
        ImageList.add(R.drawable.telegram)
        ImageList.add(R.drawable.whatsapp)

        btn.setOnClickListener{
            list.add(DModel(et1.text.toString(), et2.text.toString(), ImageList[Random.nextInt(ImageList.size)]))
            adapter.notifyDataSetChanged()
        }

        listview.setOnItemClickListener{parent, view, position, l ->
            Toast.makeText(this@DynamicallyAddingUsingAdapter, "Removing...", Toast.LENGTH_SHORT).show()
            list.removeAt(position)
            adapter.notifyDataSetChanged()
        }

    }
}