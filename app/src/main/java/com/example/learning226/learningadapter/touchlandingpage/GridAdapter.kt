package com.example.learning226.learningadapter.touchlandingpage

import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.learning226.databinding.GriditemtouchBinding

class GridAdapter(private val gridData: List<GridItem>) :
    RecyclerView.Adapter<GridAdapter.GridViewHolder>() {

    class GridViewHolder(private val binding: GriditemtouchBinding) : RecyclerView.ViewHolder(binding.root) {
        val titleTextView: TextView = binding.titleTextView
        val countTextView: TextView = binding.countTextView
        val iconImageView: ImageView = binding.iconImageView
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): GridViewHolder {
        val binding = GriditemtouchBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return GridViewHolder(binding)
    }

    override fun onBindViewHolder(holder: GridViewHolder, position: Int) {
        val gridItem = gridData[position]
        holder.titleTextView.text = gridItem.title
        holder.countTextView.text = gridItem.count.toString()
        holder.iconImageView.setImageResource(gridItem.icon)
    }

    override fun getItemCount(): Int {
        return gridData.size
    }
}