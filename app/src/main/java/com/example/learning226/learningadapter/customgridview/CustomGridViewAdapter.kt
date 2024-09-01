package com.example.learning226.learningadapter.customgridview

import android.app.Activity
import android.content.Context
import android.view.View
import android.view.ViewGroup
import android.widget.ActionMenuView
import android.widget.BaseAdapter
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView.ViewHolder
import com.example.learning226.R

class CustomGridViewAdapter(context: Context, Images: ArrayList<Int>, names: Array<String>) : BaseAdapter(){

    var context = context;
    var ImageList = Images;
    var names = names;


    override fun getCount(): Int {
        return ImageList.size
    }

    override fun getItem(position: Int): Any {
        return ImageList.get(position)
    }

    override fun getItemId(position: Int): Long {
        return 0
    }

    override fun getView(position: Int, convertView: View?, parent: ViewGroup?): View {
        var myView = convertView
        var holder: ViewHolder

        if(myView == null){
            val mInflater = (context as Activity).layoutInflater
            myView = mInflater.inflate(R.layout.grid_item, parent, false)
            holder = ViewHolder()

            holder.mImageView = myView!!.findViewById<ImageView>(R.id.gridImageView) as ImageView
            holder.mTextView = myView!!.findViewById<TextView>(R.id.gridTextView) as TextView
            myView.tag = holder
        }else{
            holder = myView.tag as ViewHolder
        }

        holder.mImageView!!.setImageResource(ImageList.get(position))
        holder.mTextView!!.setText(names.get(position))

        return myView
    }

    class ViewHolder(){
        var mImageView: ImageView? = null
        var mTextView: TextView? = null
    }

}