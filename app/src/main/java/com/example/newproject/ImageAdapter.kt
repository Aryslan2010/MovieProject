package com.example.newproject


import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.newproject.data.Movie
import com.example.newproject.data.MovieData01

class MovieAdapter(private val movieList: List<MovieData01>) : RecyclerView.Adapter<MovieAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.list_item_movie, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val movie = movieList[position]
        holder.tvMovieId.text = movie.id.toString()
        holder.tvMovieTitle.text = movie.title
    }

    override fun getItemCount(): Int {
        return movieList.size
    }

    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val tvMovieId: TextView = itemView.findViewById(R.id.tvMovieId)
        val tvMovieTitle: TextView = itemView.findViewById(R.id.tvMovieTitle)
    }
}