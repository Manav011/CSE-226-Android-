package com.example.learning226.landingfragments

import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.ListView
import android.widget.Toast
import com.example.learning226.R
import com.example.learning226.learningadapter.customgridview.CustomGridViewExample
import com.example.learning226.learningadapter.customlistviewarrayadapter.CustomListViewArrayAdapterExample
import com.example.learning226.learningadapter.customlistviewbaseadapter.CustomListViewBaseAdapterExample
import com.example.learning226.learningadapter.dynamicallyaddingitemusingadapter.DynamicallyAddingGridElement
import com.example.learning226.learningadapter.dynamicallyaddingitemusingadapter.DynamicallyAddingUsingAdapter
import com.example.learning226.learningadapter.recyclerview.DynamicRecyclerView
import com.example.learning226.learningadapter.recyclerview.RecyclerViewExample

/**
 * A simple [Fragment] subclass.
 * Use the [ArrayAdapterFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class ArrayAdapterFragment : Fragment() {

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_array_adapter, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val listView: ListView = view.findViewById(R.id.arrayadapterlistview)

        val items = arrayOf(
            "ListView Array Adapter",
            "ListView Base Adapter",
            "Dynamic ListView",
            "GridView Base Adapter",
            "Dynamic GridView",
            "ListView Recycler View",
            "Dynamic Recycler View")
        val adapter = ArrayAdapter(requireContext(), android.R.layout.simple_list_item_1, items)
        listView.adapter = adapter

        listView.setOnItemClickListener { parent, view1, position, id ->
            when (position) {
                0 -> startActivity(Intent(requireContext(), CustomListViewArrayAdapterExample::class.java))
                1 -> startActivity(Intent(requireContext(), CustomListViewBaseAdapterExample::class.java))
                2 -> startActivity(Intent(requireContext(), DynamicallyAddingUsingAdapter::class.java))
                3 -> startActivity(Intent(requireContext(), CustomGridViewExample::class.java))
                4 -> startActivity(Intent(requireContext(), DynamicallyAddingGridElement::class.java))
                5 -> startActivity(Intent(requireContext(), RecyclerViewExample::class.java))
                6 -> startActivity(Intent(requireContext(), DynamicRecyclerView::class.java))
                else -> Toast.makeText(requireContext(), "Invalid Item", Toast.LENGTH_SHORT).show()
            }
        }
    }

}