package com.example.learning226

import android.os.Bundle
import android.view.View
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity

class EspressoTestingActivity : AppCompatActivity() {
    var preferred_language: TextView? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_espresso_testing)
        preferred_language = findViewById(R.id.preferred_language)
    }

    // Same onClick function
    fun onClick(view: View) {
        when (view.id) {
            R.id.english -> preferred_language?.text = "English"
            R.id.french -> preferred_language?.text = "French"
            R.id.german -> preferred_language?.text = "German"
            R.id.hindi -> preferred_language?.text = "Hindi"
            R.id.urdu -> preferred_language?.text = "Urdu"
        }
    }
}