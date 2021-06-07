package com.example.gridview

import android.app.Activity
import android.graphics.Color
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import android.widget.TextView

data class Adapter(val list: List<Int>, val activity: Activity, val index:Int):BaseAdapter() {
    private val TAG = "Adapter"
    override fun getCount(): Int {
        return list.size
    }

    override fun getItem(position: Int): Any {
        return list.get(position)
    }

    override fun getItemId(position: Int): Long {
        return position.toLong()
    }

    override fun getView(position: Int, convertView: View?, parent: ViewGroup?): View? {
//        var convertView = convertView
//        if(convertView == null){
//            convertView = View.inflate(activity,R.layout.layout_adapter,null)
//        }
        val convertView = View.inflate(activity,R.layout.layout_adapter,null)
        var id = convertView?.findViewById<TextView>(R.id.tv_id)
        id?.text = list[position].toString()
        if(position+1 == index){
            id?.setBackgroundColor(Color.GRAY)
//            Log.d(TAG, "getView: ${position+1} and ${index}")
        }
//        parent?.getChildAt(index)?.setBackgroundColor(Color.GRAY)
//        Log.d(TAG, "getView: ${position}")
//        Log.d(TAG, "getView: ${parent?.childCount}")
        return convertView
    }
}