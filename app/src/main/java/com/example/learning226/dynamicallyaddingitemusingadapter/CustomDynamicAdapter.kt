package com.example.learning226.dynamicallyaddingitemusingadapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.EditText
import android.widget.ImageView
import android.widget.TextView
import com.example.learning226.R
import com.google.android.material.button.MaterialButton

class CustomDynamicAdapter(var mCtx:Context, var resources:Int,var items:List<ModelDemo>):
    ArrayAdapter<ModelDemo>(mCtx, resources,items) {
    override fun getView(position: Int, convertView: View?, parent: ViewGroup): View {
        val layoutInflater: LayoutInflater = LayoutInflater.from(mCtx)
        val view: View = layoutInflater.inflate(resources,null)

        val name = view.findViewById<TextView>(R.id.studentName1)
        val desc = view.findViewById<TextView>(R.id.dynamicdesc1)
        val image = view.findViewById<ImageView>(R.id.dynamicimage1)

        val mItem:ModelDemo = items[position]
        image.setImageDrawable(mCtx.resources.getDrawable(mItem.image))
        name.text = mItem.name
        desc.text = mItem.desc

        return view
    }

}
