package com.example.learning226.landingfragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.learning226.R
import android.annotation.SuppressLint
import android.view.MotionEvent
import com.google.android.material.floatingactionbutton.FloatingActionButton

class DelightfulUXFragment : Fragment() {
    private var xDelta = 0f
    private var yDelta = 0f


    @SuppressLint("ClickableViewAccessibility")
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_settings, container, false)
        val fab = view.findViewById<FloatingActionButton>(R.id.fab)

        fab.setOnTouchListener { view1, event ->
            when (event.action) {
                MotionEvent.ACTION_DOWN -> {
                    xDelta = view1.x - event.rawX
                    yDelta = view1.y - event.rawY
                }

                MotionEvent.ACTION_MOVE -> {
                    view1.animate()
                        .x(event.rawX + xDelta)
                        .y(event.rawY + yDelta)
                        .setDuration(0)
                        .start()
                }

                MotionEvent.ACTION_UP -> {
                    // Optional: Handle any action after the movement is done
                }
            }
            true
        }

        return view
    }

}