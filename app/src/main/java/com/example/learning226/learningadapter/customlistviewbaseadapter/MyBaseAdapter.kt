package com.example.learning226.learningadapter.customlistviewbaseadapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import com.example.learning226.R

class MyBaseAdapter(private val context: Context,
                    private val arrayList: java.util.ArrayList<MyData>): BaseAdapter() {

    private lateinit var serialNum: TextView
    private lateinit var name: TextView
    private lateinit var contactNum: TextView
    private lateinit var image1: ImageView

    override fun getCount(): Int {
        return arrayList.size
    }
    override fun getItem(position: Int): Any {
        return position
    }
    override fun getItemId(position: Int): Long{
        return position.toLong()
    }
    override fun getView(position: Int, Counter: View?, parent: ViewGroup): View? {
        var convertView = LayoutInflater.from(context).inflate(R.layout.rowbase, parent, false)
        serialNum= convertView.findViewById(R.id.serialNumber)
        name = convertView.findViewById(R.id.studentName)
        contactNum = convertView.findViewById(R.id.mobno)
        image1 = convertView.findViewById(R.id.baseimageview)

        serialNum.text = " "+ arrayList[position].id
        name.text = arrayList[position].Name
        contactNum.text = arrayList[position].Mobnum
        image1.setOnClickListener {
            Toast.makeText(context, "Image clicked!", Toast.LENGTH_SHORT).show()
        }

            return convertView
    }
}
