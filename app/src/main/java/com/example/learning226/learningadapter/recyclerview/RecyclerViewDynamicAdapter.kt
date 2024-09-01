package com.example.learning226.learningadapter.recyclerview

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.learning226.R

class RecyclerViewDynamicAdapter(val context: Context, val data: MutableList<Pojo>):
RecyclerView.Adapter<RecyclerViewDynamicAdapter.MyHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyHolder {
        val view = LayoutInflater.from(context).inflate(R.layout.recyclecardviewgrid, parent, false)
        return MyHolder(view, context)
    }

    override fun getItemCount(): Int {
        return data.size
    }

    override fun onBindViewHolder(holder: MyHolder, position: Int) {
        val item = data[position]
        holder.versionname.text = item.versionName
        holder.version.text = item.version
        holder.image.setImageDrawable(context.resources.getDrawable(item.Image))
    }

    class MyHolder(val view: View, val context: Context): RecyclerView.ViewHolder(view){
        val versionname = view.findViewById<TextView>(R.id.recyclerviewversionname1)
        val version = view.findViewById<TextView>(R.id.recyclerviewversion1)
        val image = view.findViewById<ImageView>(R.id.recyclerviewimage)

        init {
            itemView.setOnClickListener {
                val position = adapterPosition
                if (position != RecyclerView.NO_POSITION) {
                    (context as? DynamicRecyclerView)?.deleteItem(position)
                }
            }
        }
    }
}