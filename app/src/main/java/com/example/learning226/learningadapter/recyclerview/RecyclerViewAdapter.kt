package com.example.learning226.learningadapter.recyclerview

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.learning226.R
import java.util.Collections

class RecyclerViewAdapter(val context: Context, private val data: ArrayList<Pojo>):
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
        holder.version.text = item.version
    }

    class MyHolder(val view: View): RecyclerView.ViewHolder(view){
        val versionname = view.findViewById<TextView>(R.id.recyclerviewversionname)
        val version = view.findViewById<TextView>(R.id.recyclerviewversion)
    }

    fun moveItem(fromPosition: Int, toPosition: Int) {
        if (fromPosition < toPosition) {
            for (i in fromPosition until toPosition) {
                Collections.swap(data, i, i + 1)
                Collections.swap(data, i, i + 1)
            }
        } else {
            for (i in fromPosition downTo toPosition + 1) {
                Collections.swap(data, i, i - 1)
                Collections.swap(data, i, i - 1)
            }
        }
        notifyItemMoved(fromPosition, toPosition)
    }

    fun removeItem(position: Int) {
        data.removeAt(position)
        notifyItemRemoved(position)
    }
}
