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
import com.example.learning226.storageoptions.sqllitedatabase.SQLiteMain

/**
 * A simple [Fragment] subclass.
 * Use the [BlankFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class BlankFragment : Fragment() {
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view =  inflater.inflate(R.layout.fragment_blank, container, false)

        val listView: ListView = view.findViewById(R.id.blankfraglistview)

        val items = arrayOf(
            "SQL Lite Database")
        val adapter = ArrayAdapter(requireContext(), android.R.layout.simple_list_item_1, items)
        listView.adapter = adapter

        listView.setOnItemClickListener { parent, view1, position, id ->
            when (position) {
                0 -> startActivity(Intent(requireContext(), SQLiteMain::class.java))
                else -> Toast.makeText(requireContext(), "Invalid Item", Toast.LENGTH_SHORT).show()
            }
        }

        return view
    }
}