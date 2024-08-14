package com.example.learning226.recyclerview

import android.os.Bundle
import android.widget.EditText
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.learning226.R
import com.google.android.material.button.MaterialButton
import kotlin.random.Random

class DynamicRecyclerView : AppCompatActivity() {
    lateinit var et1: EditText
    lateinit var et2: EditText
    lateinit var btn: MaterialButton
    lateinit var data: MutableList<Pojo>
    lateinit var adapter: RecyclerViewDynamicAdapter


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
//        enableEdgeToEdge()
        setContentView(R.layout.activity_dynamic_recycler_view)
        var recyclerview = findViewById<RecyclerView>(R.id.dynamicrecgridview)

        data = mutableListOf()
        et1 = findViewById(R.id.dynamicrecet1)
        et2 = findViewById(R.id.dynamicrecet2)
        btn = findViewById(R.id.dynamicrecbtn1)
        btn.elevation = 100.0F

        recyclerview.setHasFixedSize(true)
        recyclerview.layoutManager = GridLayoutManager(this@DynamicRecyclerView, 2)
        adapter = RecyclerViewDynamicAdapter(this@DynamicRecyclerView,  data)
        recyclerview.adapter = adapter

        var ImageList = ArrayList<Int>()
        ImageList.add(R.drawable.twitter)
        ImageList.add(R.drawable.facebook)
        ImageList.add(R.drawable.skype)
        ImageList.add(R.drawable.youtube)
        ImageList.add(R.drawable.spotify)
        ImageList.add(R.drawable.telegram)
        ImageList.add(R.drawable.whatsapp)

        data.add(Pojo("Alpha", "1.0", ImageList[Random.nextInt(ImageList.size)]))
        data.add(Pojo("Beta", "1.1", ImageList[Random.nextInt(ImageList.size)]))
        data.add(Pojo("Cupcake", "1.5", ImageList[Random.nextInt(ImageList.size)]))
        data.add(Pojo("Donut", "1.6", ImageList[Random.nextInt(ImageList.size)]))
        data.add(Pojo("Eclair", "2.0", ImageList[Random.nextInt(ImageList.size)]))
        data.add(Pojo("Eclair", "2.1", ImageList[Random.nextInt(ImageList.size)]))
        data.add(Pojo("Froyo", "2.2", ImageList[Random.nextInt(ImageList.size)]))
        data.add(Pojo("Gingerbread 2.3.1", "2.3", ImageList[Random.nextInt(ImageList.size)]))
        data.add(Pojo("Gingerbread 2.3.3", "2.3.3", ImageList[Random.nextInt(ImageList.size)]))
        data.add(Pojo("Honeycomb", "3.0", ImageList[Random.nextInt(ImageList.size)]))
        data.add(Pojo("Ice Cream Sandwich", "4.0", ImageList[Random.nextInt(ImageList.size)]))
        data.add(Pojo("Jelly Bean", "4.1", ImageList[Random.nextInt(ImageList.size)]))
        data.add(Pojo("KitKat", "4.4", ImageList[Random.nextInt(ImageList.size)]))
        data.add(Pojo("Lollipop", "5.0", ImageList[Random.nextInt(ImageList.size)]))
        data.add(Pojo("Marshmallow", "6.0", ImageList[Random.nextInt(ImageList.size)]))
        data.add(Pojo("Nougat", "7.0", ImageList[Random.nextInt(ImageList.size)]))
        data.add(Pojo("Oreo", "8.0", ImageList[Random.nextInt(ImageList.size)]))
        data.add(Pojo("Pie", "9.0", ImageList[Random.nextInt(ImageList.size)]))
        data.add(Pojo("Android 10", "10.0", ImageList[Random.nextInt(ImageList.size)]))
        data.add(Pojo("Android 11", "11.0", ImageList[Random.nextInt(ImageList.size)]))
        data.add(Pojo("Android 12", "12.0", ImageList[Random.nextInt(ImageList.size)]))
        data.add(Pojo("Android 13", "13.0", ImageList[Random.nextInt(ImageList.size)]))
        data.add(Pojo("Android 14", "14.0", ImageList[Random.nextInt(ImageList.size)]))


        btn.setOnClickListener{
            data.add(Pojo(et1.text.toString(), et2.text.toString(), ImageList[Random.nextInt(ImageList.size)]))
            adapter.notifyItemInserted(data.size-1)
            adapter.notifyItemRangeChanged(data.size-1, data.size)
        }

    }

    fun deleteItem(position: Int) {
        if (position >= 0 && position < data.size) {
            data.removeAt(position)
            adapter.notifyItemRemoved(position)
            adapter.notifyItemRangeChanged(position, data.size)
        }
    }
}