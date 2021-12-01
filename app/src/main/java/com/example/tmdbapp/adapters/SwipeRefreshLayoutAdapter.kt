package com.example.tmdbapp.adapters

import android.net.Uri
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView

import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.tmdbapp.R
import com.example.tmdbapp.model.local.Movie
import java.net.URI
import kotlin.coroutines.coroutineContext

private const val IMAGE_BASE_URL: String = "https://image.tmdb.org/t/p/w500"

class SwipeRefreshLayoutAdapter: RecyclerView.Adapter<SwipeRefreshLayoutAdapter.ViewHolder>()
{
    var movieList: MutableList<Movie>? = null

    class ViewHolder(itemView: View): RecyclerView.ViewHolder(itemView){
        val movieTitle: TextView = itemView.findViewById(R.id.movie_title)
        val moviePoster: ImageView = itemView.findViewById(R.id.movie_poster)
        val movieVoteAverage: TextView = itemView.findViewById(R.id.movie_vote_average)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.list_item, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {

        holder.movieTitle.text = movieList?.get(position)?.title
        holder.movieVoteAverage.text = movieList?.get(position)?.vote_average.toString()

        Glide.with(holder.moviePoster)
            .asBitmap()
            .load(IMAGE_BASE_URL + movieList?.get(position)?.poster_path)
            .into(holder.moviePoster)


    }

    override fun getItemCount(): Int {
        movieList?.let {
            return it.size
        } ?: return 0
    }

    fun updatePosts(newList: List<Movie>?)
    {
        newList?.let {
            if (movieList.isNullOrEmpty())
            {
                movieList = mutableListOf()
            }
            it.forEach { movie-> movieList?.add(movie); notifyDataSetChanged()}
        }

    }


}