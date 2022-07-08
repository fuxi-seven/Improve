package com.hly.improve.ui.notifications

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.hly.improve.R

class MyAdapter(val context: Context, list: ArrayList<String>) :
    RecyclerView.Adapter<MyAdapter.MyViewHolder>() {

    var dataList = ArrayList<String>()

    init {
        dataList.clear()
        dataList.addAll(list)
    }

    inner class MyViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView)

    //创建ViewHolder
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val itemView = LayoutInflater.from(context).inflate(R.layout.item, parent, false)
        return MyViewHolder(itemView)
    }

    //数据绑定
    override fun onBindViewHolder(itemViewHolder: MyViewHolder, position: Int) {
        itemViewHolder.itemView.findViewById<TextView>(R.id.item_text).text = dataList[position]
    }

    //获取item个数
    override fun getItemCount() : Int {
        return dataList.size
    }
}
