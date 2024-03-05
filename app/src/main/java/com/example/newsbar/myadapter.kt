package com.example.newsbar

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Adapter
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide

class myadapter(context: Context):RecyclerView.Adapter<myadapter.myViewHolder>() {

    var list=ArrayList<DataView>()




    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): myViewHolder {
       val itemView = LayoutInflater.from(parent.context).inflate(R.layout.itemview,parent,false)
        return myViewHolder(itemView)
    }


    override fun getItemCount(): Int {
        return list.size

    }

    override fun onBindViewHolder(holder: myViewHolder, position: Int) {
        val currentItem = list[position]
        Glide.with(holder.itemView.context).load(currentItem.image).into(holder.image)
        holder.mytitle.text= currentItem.title
        holder.mydescription.text= currentItem.description

    }
    class myViewHolder(  itemView: View): RecyclerView.ViewHolder(itemView){
       val image = itemView.findViewById<ImageView>(R.id.tvimage)
        val mytitle = itemView.findViewById<TextView>(R.id.tvTitle)
        val mydescription = itemView.findViewById<TextView>(R.id.tvdescription)

    }

        fun updateNews(updatedNews: ArrayList<DataView>) {
                list.clear()
                list.addAll(updatedNews)

                notifyDataSetChanged()

        }


}