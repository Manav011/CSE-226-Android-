package com.example.learning226.dynamicallyaddingitemusingadapter

import android.os.Bundle
import android.widget.EditText
import android.widget.ListView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.learning226.R
import com.google.android.material.button.MaterialButton

class DynamicallyAddingUsingAdapter : AppCompatActivity() {
    lateinit var et1: EditText
    lateinit var et2: EditText
    lateinit var btn: MaterialButton

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_dynamically_adding_using_adapter)
        var listview = findViewById<ListView>(R.id.dynamiclistview)
        var list = mutableListOf<ModelDemo>()
        et1 = findViewById(R.id.dynamicet1)
        et2 = findViewById(R.id.dynamicet2)
        btn = findViewById(R.id.dynamicbtn)
        btn.elevation = 100.0F

        var adapter = CustomDynamicAdapter(this@DynamicallyAddingUsingAdapter, R.layout.dynamicaddingitem, list)
        listview.adapter = adapter
        btn.setOnClickListener{
            list.add(ModelDemo(et1.text.toString(), et2.text.toString(), R.drawable.twitter))
            adapter.notifyDataSetChanged()
        }

        listview.setOnItemClickListener{parent, view, position, l ->
            if(position == 0) Toast.makeText(this@DynamicallyAddingUsingAdapter, "Removing...", Toast.LENGTH_SHORT).show()
            list.removeAt(position)
            adapter.notifyDataSetChanged()
        }

    }
}