package com.example.learning226.learningadapter.customlistviewbaseadapter

import android.os.Bundle
import android.widget.ListView
import androidx.appcompat.app.AppCompatActivity
import com.example.learning226.R

class CustomListViewBaseAdapterExample : AppCompatActivity() {// we can do more customisation in base adapter like we can do something different with each element
    private lateinit var listView: ListView
    private var arrayList: ArrayList<MyData> = ArrayList()
//    var adapter: MyBaseAdapter? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
//        enableEdgeToEdge()
        setContentView(R.layout.activity_custom_list_view_base_adapter_example)
        title = "ListView BaseAdapter"
        listView = findViewById(R.id.baseadapterlistView)

        arrayList.add(MyData(1, "Manav", "98789465416"))
        arrayList.add(MyData(2, "Mana", "68471489455"))
        arrayList.add(MyData(3, "Man", "964718949445"))

        listView.adapter = MyBaseAdapter(this@CustomListViewBaseAdapterExample, arrayList)
//        listView.adapter = adapter
    }
}