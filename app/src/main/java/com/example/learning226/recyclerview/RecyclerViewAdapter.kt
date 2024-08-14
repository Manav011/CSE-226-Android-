package com.example.learning226.recyclerview

import android.content.Context
import android.text.Layout
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.learning226.R

class RecyclerViewAdapter(val context: Context, val data: ArrayList<Pojo>):
    RecyclerView.Adapter<RecyclerViewAdapter.MyHolder>(){

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyHolder {
        val view = LayoutInflater.from(context).inflate(R.layout.recyclecardview, parent, false)
        return MyHolder(view)
    }

    override fun getItemCount(): Int {
        return data.size
    }

    override fun onBindViewHolder(holder: MyHolder, position: Int) {
       val item = data[position]
        holder.versionname.text = item.versionName
        holder.version.text = item.version.toString()
    }

    class MyHolder(val view: View): RecyclerView.ViewHolder(view){
        val versionname = view.findViewById<TextView>(R.id.recyclerviewversionname)
        val version = view.findViewById<TextView>(R.id.recyclerviewversion)
    }
}
