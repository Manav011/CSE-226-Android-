package com.example.learning226.recyclerview

import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.learning226.R

class RecyclerViewExample : AppCompatActivity() {
    lateinit var data: ArrayList<Pojo>
    lateinit var recyclerView: RecyclerView
    var adapter: RecyclerViewAdapter? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
//        enableEdgeToEdge()
        setContentView(R.layout.activity_recycler_view_example)

        data.add(Pojo("Alpha", 1.0F))
        data.add(Pojo("Beta", 1.1F))
        data.add(Pojo("Cupcake", 1.5F))
        data.add(Pojo("Donut", 1.6F))
        data.add(Pojo("Eclair", 2.0F))
        data.add(Pojo("Eclair", 2.1F))
        data.add(Pojo("Froyo", 2.2F))
        data.add(Pojo("Gingerbread 2.3", 2.3F))
        data.add(Pojo("Gingerbread 2.3.3", 2.3F))
        data.add(Pojo("Honeycomb", 3.0F))
        data.add(Pojo("Ice Cream Sandwich", 4.0F))
        data.add(Pojo("Jelly Bean", 4.1F))
        data.add(Pojo("KitKat", 4.4F))
        data.add(Pojo("Lollipop", 5.0F))
        data.add(Pojo("Marshmallow", 6.0F))
        data.add(Pojo("Nougat", 7.0F))
        data.add(Pojo("Oreo", 8.0F))
        data.add(Pojo("Pie", 9.0F))
        data.add(Pojo("Android 10", 10.0F))
        data.add(Pojo("Android 11", 11.0F))
        data.add(Pojo("Android 12", 12.0F))
        data.add(Pojo("Android 13", 13.0F))
        data.add(Pojo("Android 14", 14.0F))


        recyclerView = findViewById(R.id.recyclerView)
        recyclerView.setHasFixedSize(true)
        recyclerView.layoutManager = LinearLayoutManager(this@RecyclerViewExample, RecyclerView.VERTICAL, false)
        adapter = RecyclerViewAdapter(this@RecyclerViewExample, data)
        recyclerView.adapter = adapter
    }
}