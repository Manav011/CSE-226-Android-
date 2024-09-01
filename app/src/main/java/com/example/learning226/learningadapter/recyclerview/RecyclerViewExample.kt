package com.example.learning226.learningadapter.recyclerview

import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.ItemTouchHelper
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.learning226.R
import com.google.android.material.snackbar.Snackbar

class RecyclerViewExample : AppCompatActivity() {
    lateinit var data: ArrayList<Pojo>
    lateinit var recyclerView: RecyclerView
    lateinit var adapter: RecyclerViewAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
//        enableEdgeToEdge()
        setContentView(R.layout.activity_recycler_view_example)

        data = ArrayList();
        data.add(Pojo("Alpha", "1.0"))
        data.add(Pojo("Beta", "1.1"))
        data.add(Pojo("Cupcake", "1.5"))
        data.add(Pojo("Donut", "1.6"))
        data.add(Pojo("Eclair", "2.0"))
        data.add(Pojo("Eclair", "2.1"))
        data.add(Pojo("Froyo", "2.2"))
        data.add(Pojo("Gingerbread 2.3.1" ,"2.3"))
        data.add(Pojo("Gingerbread 2.3.3", "2.3.3"))
        data.add(Pojo("Honeycomb", "3.0"))
        data.add(Pojo("Ice Cream Sandwich", "4.0"))
        data.add(Pojo("Jelly Bean", "4.1"))
        data.add(Pojo("KitKat", "4.4"))
        data.add(Pojo("Lollipop", "5.0"))
        data.add(Pojo("Marshmallow", "6.0"))
        data.add(Pojo("Nougat", "7.0"))
        data.add(Pojo("Oreo", "8.0"))
        data.add(Pojo("Pie", "9.0"))
        data.add(Pojo("Android 10", "10.0"))
        data.add(Pojo("Android 11", "11.0"))
        data.add(Pojo("Android 12", "12.0"))
        data.add(Pojo("Android 13", "13.0"))
        data.add(Pojo("Android 14", "14.0"))

        recyclerView = findViewById(R.id.recyclerView)
        recyclerView.setHasFixedSize(true)
        recyclerView.layoutManager = LinearLayoutManager(this@RecyclerViewExample, RecyclerView.VERTICAL, false)
        adapter = RecyclerViewAdapter(this@RecyclerViewExample, data)
        recyclerView.adapter = adapter

        val itemTouchHelper = ItemTouchHelper(object : ItemTouchHelper.SimpleCallback(
            ItemTouchHelper.UP or ItemTouchHelper.DOWN,
            ItemTouchHelper.RIGHT or ItemTouchHelper.LEFT // 0 disables swipe
        ) {
            override fun onMove(
                recyclerView: RecyclerView,
                viewHolder: RecyclerView.ViewHolder,
                target: RecyclerView.ViewHolder
            ): Boolean {
                val fromPosition = viewHolder.adapterPosition
                val toPosition = target.adapterPosition
                adapter.moveItem(fromPosition, toPosition)
                return true
            }
            override fun onSwiped(viewHolder: RecyclerView.ViewHolder, direction: Int) {

                val position = viewHolder.adapterPosition
                val data1 = data.get(position)
                if(direction == ItemTouchHelper.LEFT) {
                    data.removeAt(position)
                    adapter.notifyItemRemoved(position)
                    Snackbar.make(
                        recyclerView," Deleting " + data1.versionName,
                        Snackbar.LENGTH_SHORT
                    ).setAction("Undo", View.OnClickListener {
                        data.add(position, data1)
                        adapter.notifyItemInserted(position)
                    }).show()
                }else{
                    adapter.notifyItemChanged(position)
                    Toast.makeText(this@RecyclerViewExample, "Swiped Right", Toast.LENGTH_LONG).show()
                }
            }
        })
        itemTouchHelper.attachToRecyclerView(recyclerView)
    }
}