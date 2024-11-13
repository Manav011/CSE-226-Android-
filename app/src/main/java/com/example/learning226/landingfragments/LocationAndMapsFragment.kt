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
import com.example.learning226.backgroundprocesses.boundservices.BoundServiceMain
import com.example.learning226.backgroundprocesses.broadcastreceiver.ModeReceiverMain
import com.example.learning226.backgroundprocesses.learningcoroutines.CoroutinesExamples
import com.example.learning226.backgroundprocesses.learningforegroundservices.ForegroundServicesMain
import com.example.learning226.learningadapter.touchlandingpage.TouchLandingPage
import com.example.learning226.locationandmaps.currentlocation.CurrentLocationMain
import com.example.learning226.locationandmaps.locationapi.LocationAPIMain
import com.example.learning226.staractivity.StarActivity

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 * Use the [LocationAndMapsFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class LocationAndMapsFragment : Fragment() {
    // TODO: Rename and change types of parameters
    private var param1: String? = null
    private var param2: String? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            param1 = it.getString(ARG_PARAM1)
            param2 = it.getString(ARG_PARAM2)
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_location, container, false)
        val list = view.findViewById<ListView>(R.id.locationlistview)

        val items = arrayOf("Current Location",
                            "Location API")
        val adapter = ArrayAdapter(requireContext(), android.R.layout.simple_list_item_1, items)

        list.adapter = adapter
        list.setOnItemClickListener{parent, view, position, id, ->
            when(position){
                0 -> startActivity(Intent(requireContext(), CurrentLocationMain::class.java))
                1 -> startActivity(Intent(requireContext(), LocationAPIMain::class.java))
                else -> throw IllegalArgumentException("Unexpected position home list viewL $position")
            }
        }

        return view
    }

    companion object {
        /**
         * Use this factory method to create a new instance of
         * this fragment using the provided parameters.
         *
         * @param param1 Parameter 1.
         * @param param2 Parameter 2.
         * @return A new instance of fragment LocationFragment.
         */
        // TODO: Rename and change types and number of parameters
        @JvmStatic
        fun newInstance(param1: String, param2: String) =
            LocationAndMapsFragment().apply {
                arguments = Bundle().apply {
                    putString(ARG_PARAM1, param1)
                    putString(ARG_PARAM2, param2)
                }
            }
    }
}