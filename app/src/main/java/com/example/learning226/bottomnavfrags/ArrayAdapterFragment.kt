package com.example.learning226.bottomnavfrags

import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.ListView
import com.example.learning226.R
import com.example.learning226.customgridview.CustomGridViewExample
import com.example.learning226.customlistviewarrayadapter.CustomListViewArrayAdapterExample
import com.example.learning226.customlistviewbaseadapter.CustomListViewBaseAdapterExample
import com.example.learning226.dynamicallyaddingitemusingadapter.DynamicallyAddingGridElement
import com.example.learning226.dynamicallyaddingitemusingadapter.DynamicallyAddingUsingAdapter

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 * Use the [ArrayAdapterFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class ArrayAdapterFragment : Fragment() {
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
        return inflater.inflate(R.layout.fragment_array_adapter, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val listView: ListView = view.findViewById(R.id.arrayadapterlistview)

        val items = arrayOf("Array Adapter", "Base Adapter", "Dynamically Adding and Removing using Adapter", "Custom Grid View using Base Adapter", "Dynamically Adding and Removing using Adapter in Grid View")
        val adapter = ArrayAdapter(requireContext(), android.R.layout.simple_list_item_1, items)
        listView.adapter = adapter

        listView.setOnItemClickListener { parent, view, position, id ->
            when (position) {
                0 -> startActivity(Intent(requireContext(), CustomListViewArrayAdapterExample::class.java))
                1 -> startActivity(Intent(requireContext(), CustomListViewBaseAdapterExample::class.java))
                2 -> startActivity(Intent(requireContext(), DynamicallyAddingUsingAdapter::class.java))
                3 -> startActivity(Intent(requireContext(), CustomGridViewExample::class.java))
                4 -> startActivity(Intent(requireContext(), DynamicallyAddingGridElement::class.java))
                else -> throw IllegalArgumentException("Unexpected position: $position")
            }
        }
    }

    companion object {
        /**
         * Use this factory method to create a new instance of
         * this fragment using the provided parameters.
         *
         * @param param1 Parameter 1.
         * @param param2 Parameter 2.
         * @return A new instance of fragment ArrayAdapterFragment.
         */
        // TODO: Rename and change types and number of parameters
        @JvmStatic
        fun newInstance(param1: String, param2: String) =
            ArrayAdapterFragment().apply {
                arguments = Bundle().apply {
                    putString(ARG_PARAM1, param1)
                    putString(ARG_PARAM2, param2)
                }
            }
    }
}