package com.example.learning226.landingfragments

import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.ListView
import com.example.learning226.R
import com.example.learning226.learningcoroutines.CoroutinesExamples
import com.example.learning226.learningadapter.touchlandingpage.TouchLandingPage
import com.example.learning226.learningforegroundservices.ForegroundServicesMain

/**
 * A simple [Fragment] subclass.
 * Use the [HomeFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class HomeFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_home, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val list = view.findViewById<ListView>(R.id.homelistview)

        val items = arrayOf("Touch Landing Page",
                            "Coroutine U-2",
                            "Foreground Service")
        val adapter = ArrayAdapter(requireContext(), android.R.layout.simple_list_item_1, items)

        list.adapter = adapter
        list.setOnItemClickListener{parent, view, position, id, ->
            when(position){
                0 -> startActivity(Intent(requireContext(), TouchLandingPage::class.java))
                1 -> startActivity(Intent(requireContext(), CoroutinesExamples::class.java))
                2 -> startActivity(Intent(requireContext(), ForegroundServicesMain::class.java))
                else -> throw IllegalArgumentException("Unexpected position home list viewL $position")
            }
        }

    }

}