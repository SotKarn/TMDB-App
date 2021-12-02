package com.example.tmdbapp.adapters


import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.tmdbapp.R
import com.example.tmdbapp.model.cache.CachedMovie


private const val IMAGE_BASE_URL: String = "https://image.tmdb.org/t/p/w500"
private const val VIEW_TYPE_ITEM: Int = 1
private const val VIEW_TYPE_LOADING: Int = 0

class RecycleViewAdapter: RecyclerView.Adapter<RecyclerView.ViewHolder>()
{
    private var movieList: MutableList<CachedMovie?> = mutableListOf()

    inner class ViewHolder(itemView: View): RecyclerView.ViewHolder(itemView){
        val movieTitle: TextView = itemView.findViewById(R.id.movie_title)
        val moviePoster: ImageView = itemView.findViewById(R.id.movie_poster)
    }

    inner class LoadingViewHolder(itemView: View): RecyclerView.ViewHolder(itemView)


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {

        val view:View
        return if(viewType == VIEW_TYPE_ITEM) {
            view = LayoutInflater.from(parent.context).inflate(R.layout.list_item, parent, false)
            ViewHolder(view)
        } else {
            view = LayoutInflater.from(parent.context).inflate(R.layout.loading_item, parent, false)
            LoadingViewHolder(view)
        }
    }



    override fun getItemViewType(position: Int): Int {
        return if (movieList[position] == null) VIEW_TYPE_LOADING else VIEW_TYPE_ITEM
    }

    override fun getItemCount(): Int {
        return movieList.size
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        if(holder is ViewHolder)
        {
            holder.movieTitle.text = movieList[position]?.title

            Glide.with(holder.moviePoster)
                .asBitmap()
                .load(IMAGE_BASE_URL + movieList.get(position)?.poster_path)
                .into(holder.moviePoster)
        }
    }


    /////   Helper Methods ////////////////////


    fun updateMovies(newList: List<CachedMovie>?)
    {
        removeLoadingItem()
        newList?.let {

            it.forEach { movie->
                movieList.add(movie)
                notifyItemChanged(movieList.size - 1)
            }
        }


    }

    fun addLoadingItem()
    {
        movieList.add(null)
        notifyItemInserted(movieList.size - 1)
    }

    private fun removeLoadingItem()
    {
        movieList.removeAt(movieList.size - 1)
        notifyItemRemoved(movieList.size - 1)
    }

}