package com.mvvm.testproject.ui.home

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.mvvm.testproject.R

class Homepageadapter(var itemList: List<String>) : RecyclerView.Adapter<Homepageadapter.MyViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        return MyViewHolder(LayoutInflater.from(parent?.context).inflate(R.layout.row_item_home, parent, false))
    }

    override fun getItemCount(): Int {
        return itemList.size
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        holder.itemTitle?.text = itemList.get(position)
        }

    class MyViewHolder(itemview: View) : RecyclerView.ViewHolder(itemview) {
        var itemTitle: TextView
        var imageview: ImageView
        init {
            itemTitle = itemView.findViewById(R.id.textView2)
            imageview=itemView.findViewById(R.id.imageView2)
        }
    }

 }