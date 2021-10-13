package com.akin.figmatask.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.akin.figmatask.R

class Genres(private val list: List<String>):RecyclerView.Adapter<Genres.ViewHolder>() {

    class ViewHolder(itemView: View):RecyclerView.ViewHolder(itemView) {
        val genresText = itemView.findViewById<TextView>(R.id.genreText)

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.genres_item, parent, false)


        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
       holder.genresText.text = list[position]
    }

    override fun getItemCount(): Int {
        return  list.size
    }
}
