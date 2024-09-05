package com.example.learning226.learningadapter.touchlandingpage

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.GridLayoutManager
import com.example.learning226.R
import com.example.learning226.databinding.ActivityTouchLandingPageBinding
import androidx.recyclerview.widget.LinearLayoutManager
import kotlin.random.Random

class TouchLandingPage : AppCompatActivity() {
    private lateinit var binding: ActivityTouchLandingPageBinding
    private lateinit var timetableAdapter: TimetableAdapter
    private lateinit var gridAdapter: GridAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityTouchLandingPageBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val timetableData = mutableListOf(
            TimetableItem("CSE226", "38-916", "01-02 PM", R.drawable.books),
            TimetableItem("INT221", "38-907", "09-10 AM", R.drawable.books),
            TimetableItem("CSE226", "38-916", "01-02 PM", R.drawable.books),
            TimetableItem("INT221", "38-907", "09-10 AM", R.drawable.books),
            TimetableItem("CSE226", "38-916", "01-02 PM", R.drawable.books),
            TimetableItem("INT221", "38-907", "09-10 AM", R.drawable.books)
        )

        val gridData = mutableListOf(
            GridItem("Announce", 26, R.drawable.books),
            GridItem("Fee Statement", 0, R.drawable.facebook),
            GridItem("Attendance", 72, R.drawable.skype),
            GridItem("Assignment", 0, R.drawable.twitter),
            GridItem("Results", 9.06, R.drawable.whatsapp),
            GridItem("Exams", 0, R.drawable.whatsapp)
        )

        timetableAdapter = TimetableAdapter(timetableData)
        binding.timetableRecyclerView.layoutManager = LinearLayoutManager(this@TouchLandingPage, LinearLayoutManager.HORIZONTAL, false)
        binding.timetableRecyclerView.adapter = timetableAdapter

        gridAdapter = GridAdapter(gridData)
        binding.gridRecyclerView.layoutManager = GridLayoutManager(this@TouchLandingPage, 3)
        binding.gridRecyclerView.adapter = gridAdapter


        binding.addbutton.setOnClickListener{
            gridData.add(gridData[Random.nextInt(gridData.size)])
            gridAdapter.notifyItemInserted(gridData.size-1)
            gridAdapter.notifyItemRangeChanged(gridData.size-1, gridData.size)
        }
    }
}