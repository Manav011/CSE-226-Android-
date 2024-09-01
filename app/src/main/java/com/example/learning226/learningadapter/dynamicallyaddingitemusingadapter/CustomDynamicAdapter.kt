package com.example.learning226.learningadapter.dynamicallyaddingitemusingadapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.ImageView
import android.widget.TextView
import com.example.learning226.R

class CustomDynamicAdapter(private var mCtx:Context, var resources:Int, private var items:List<DModel>, private var isList: Boolean):
    ArrayAdapter<DModel>(mCtx, resources,items) {
    override fun getView(position: Int, convertView: View?, parent: ViewGroup): View {
//        val layoutInflater: LayoutInflater = LayoutInflater.from(mCtx)
        val view: View = LayoutInflater.from(mCtx).inflate(resources,null)

        if(isList){
            val name = view.findViewById<TextView>(R.id.studentName1)
            val desc = view.findViewById<TextView>(R.id.dynamicdesc1)
            val image = view.findViewById<ImageView>(R.id.dynamicimage1)

            val mItem: DModel = items[position]
            image.setImageDrawable(mCtx.resources.getDrawable(mItem.image))
            name.text = mItem.name
            desc.text = mItem.desc
        }else{
            val name = view.findViewById<TextView>(R.id.gridTextView)
            val image = view.findViewById<ImageView>(R.id.gridImageView)

            val mItem: DModel = items[position]
            name.text = mItem.name
            image.setImageDrawable(mCtx.resources.getDrawable(mItem.image))
        }

        return view
    }

}
