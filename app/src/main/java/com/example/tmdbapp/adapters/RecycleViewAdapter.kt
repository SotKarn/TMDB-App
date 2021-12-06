package com.example.tmdbapp.adapters


import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.paging.PagingDataAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.drawable.DrawableTransitionOptions
import com.example.tmdbapp.R
import com.example.tmdbapp.model.MovieEntity


private const val IMAGE_BASE_URL: String = "https://image.tmdb.org/t/p/w500"


class RecycleViewAdapter: PagingDataAdapter<MovieEntity, RecycleViewAdapter.ViewHolder>(PHOTO_COMPARATOR) {

    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val movieTitle: TextView = itemView.findViewById(R.id.movie_title)
        val moviePoster: ImageView = itemView.findViewById(R.id.movie_poster)
    }

    companion object {
        private val PHOTO_COMPARATOR = object : DiffUtil.ItemCallback<MovieEntity>() {
            override fun areItemsTheSame(oldItem: MovieEntity, newItem: MovieEntity): Boolean =
                oldItem.id == newItem.id

            override fun areContentsTheSame(oldItem: MovieEntity, newItem: MovieEntity): Boolean =
                oldItem == newItem
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.list_item, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: RecycleViewAdapter.ViewHolder, position: Int) {

        val currentItem = getItem(position)

        currentItem?.apply {
            holder.movieTitle.text = title

            Glide.with(holder.moviePoster)
                .load(IMAGE_BASE_URL + poster_path)
                .centerCrop()
                .transition(DrawableTransitionOptions.withCrossFade())
                .error(android.R.drawable.ic_menu_close_clear_cancel)
                .into(holder.moviePoster)
        }
    }
}