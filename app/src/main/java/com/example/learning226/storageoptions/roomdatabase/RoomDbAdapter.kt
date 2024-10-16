package com.example.learning226.storageoptions.roomdatabase

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.TextView
import com.example.learning226.R

class RoomDbAdapter(private var mCtx: Context, var resources:Int, private var items:List<Contact>):
    ArrayAdapter<Contact>(mCtx, resources, items) {
    override fun getView(position: Int, convertView: View?, parent: ViewGroup): View {
        val layoutInflater: LayoutInflater = LayoutInflater.from(mCtx)
        val view: View = layoutInflater.inflate(resources, null)

        val name: TextView = view.findViewById(R.id.roomdbnametv)
        val phone: TextView = view.findViewById(R.id.roomdbmobtv)
        val id: TextView = view.findViewById(R.id.roomdbidtv)
        val mItem:Contact = items[position]

        name.text = mItem.name
        phone.text = mItem.phone.toString()
        id.text = mItem.id.toString()

        return view
    }
}
