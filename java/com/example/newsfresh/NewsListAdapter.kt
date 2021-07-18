package com.example.newsfresh

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide

class NewsListAdapter( private val listener : NewsItemClicked): RecyclerView.Adapter<NewsViewHolder>() {

    private val items: ArrayList<News> = ArrayList()
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): NewsViewHolder {
        val view=LayoutInflater.from(parent.context).inflate(R.layout.item_news,parent,false)
        val viewHolder= NewsViewHolder(view)
        view.setOnClickListener{
             listener.onItemClicked(items[viewHolder.adapterPosition])
        }
        return viewHolder
    }

    override fun onBindViewHolder(holder: NewsViewHolder, position: Int) {
        val currentItem= items[position]
        holder.name.text=currentItem.name
        holder.symbolname.text=currentItem.symbol
        holder.pricechange1hr.text=currentItem.priceChange1h
        holder.price.text=currentItem.price
        Glide.with(holder.itemView.context).load(currentItem.imageUrl).into(holder.imageView)
    }

    override fun getItemCount(): Int {
        return items.size
    }

    fun updateNews(updatedNews: ArrayList<News>){
        items.clear()
        items.addAll(updatedNews)

        notifyDataSetChanged()
    }
}

class NewsViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

   val name: TextView = itemView.findViewById(R.id.textView1)
    val symbolname: TextView = itemView.findViewById(R.id.textView)
    val pricechange1hr: TextView = itemView.findViewById(R.id.textView7)
    val price: TextView = itemView.findViewById(R.id.textView6)
    val imageView: ImageView = itemView.findViewById(R.id.imageView)
}

interface NewsItemClicked{
    fun onItemClicked(item: News)
}