package com.example.learning226.learningadapter.touchlandingpage

import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.learning226.databinding.TimetableItemBinding

class TimetableAdapter(private val timetableData: List<TimetableItem>) :
    RecyclerView.Adapter<TimetableAdapter.TimetableViewHolder>() {

    class TimetableViewHolder(private val binding: TimetableItemBinding) :
        RecyclerView.ViewHolder(binding.root) {
        val subjectTextView: TextView = binding.subjectTextView
        val codeTextView: TextView = binding.codeTextView
        val timeTextView: TextView = binding.timeTextView
        val iconImageView: ImageView = binding.iconImageView
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TimetableViewHolder {
        val binding = TimetableItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return TimetableViewHolder(binding)
    }

    override fun onBindViewHolder(holder: TimetableViewHolder, position: Int) {
        val timetableItem = timetableData[position]
        holder.subjectTextView.text = timetableItem.subject
        holder.codeTextView.text = timetableItem.code
        holder.timeTextView.text = timetableItem.time
        holder.iconImageView.setImageResource(timetableItem.icon)
    }

    override fun getItemCount(): Int {
        return timetableData.size
    }
}
