package com.akin.figmatask.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.akin.figmatask.HomeScreen
import com.akin.figmatask.R

class CustomViewPager(private val images: List<Int>, private val names: List<String>,private val itemClickListener: (Int) -> (Unit)) :
    RecyclerView.Adapter<CustomViewPager.PostHolder>() {
   inner class PostHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val movieTextView = itemView.findViewById<TextView>(R.id.movieName)
        val starRatingText = itemView.findViewById<TextView>(R.id.ratingBar)
        val imageView = itemView.findViewById<ImageView>(R.id.cardImage)
       init {
           itemView.setOnClickListener {
               itemClickListener(adapterPosition)

           }
       }

   }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PostHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.view_pager_item, parent, false)


        return PostHolder(view)
    }

    override fun onBindViewHolder(holder: PostHolder, position: Int) {
        holder.imageView.setImageResource(images[position])
         holder.movieTextView.text = names[position]
         holder.starRatingText.text = "9.1"

    }

    override fun getItemCount(): Int {
        return images.size
    }


}