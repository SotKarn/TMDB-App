package com.example.tmdbapp.adapters


import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.navigation.findNavController
import androidx.paging.PagingDataAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.drawable.DrawableTransitionOptions
import com.example.tmdbapp.R
import com.example.tmdbapp.databinding.ListItemBinding
import com.example.tmdbapp.model.MovieEntity


private const val IMAGE_BASE_URL: String = "https://image.tmdb.org/t/p/w500"


class RecycleViewAdapter: PagingDataAdapter<MovieEntity, RecycleViewAdapter.ViewHolder>(PHOTO_COMPARATOR) {

    inner class ViewHolder(private val binding: ListItemBinding) : RecyclerView.ViewHolder(binding.root) {

        fun bind(movieEntity: MovieEntity)
        {
            movieEntity.apply {
                binding.movieTitle.text = title

                Glide.with(binding.moviePoster)
                    .load(IMAGE_BASE_URL + poster_path)
                    .centerCrop()
                    .transition(DrawableTransitionOptions.withCrossFade())
                    .error(android.R.drawable.ic_menu_close_clear_cancel)
                    .into(binding.moviePoster)

                binding.root.setOnClickListener {
                    val movieBundle = Bundle()
                    movieBundle.putParcelable("movie", this)
                    it.findNavController().navigate(R.id.action_popularMoviesFragment_to_movieDetailsFragment, movieBundle)
                }
            }
        }

    }


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = ListItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: RecycleViewAdapter.ViewHolder, position: Int) {

        val currentItem = getItem(position)
        currentItem?.let{
            holder.bind(it)
        }
    }


    companion object {
        private val PHOTO_COMPARATOR = object : DiffUtil.ItemCallback<MovieEntity>() {
            override fun areItemsTheSame(oldItem: MovieEntity, newItem: MovieEntity): Boolean =
                oldItem.id == newItem.id

            override fun areContentsTheSame(oldItem: MovieEntity, newItem: MovieEntity): Boolean =
                oldItem == newItem
        }
    }
}