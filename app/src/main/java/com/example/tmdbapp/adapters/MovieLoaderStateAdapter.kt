package com.example.tmdbapp.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.core.view.isVisible
import androidx.paging.LoadState
import androidx.paging.LoadStateAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.tmdbapp.databinding.MovieLoadStateFooterBinding

class MovieLoaderStateAdapter(private val retry: () -> Unit): LoadStateAdapter<MovieLoaderStateAdapter.LoadStateViewHolder>()
{
    override fun onCreateViewHolder(parent: ViewGroup, loadState: LoadState): LoadStateViewHolder {
       val binding = MovieLoadStateFooterBinding.inflate(LayoutInflater.from(parent.context), parent, false)
       return LoadStateViewHolder(binding)
    }


    override fun onBindViewHolder(holder: LoadStateViewHolder, loadState: LoadState) {
        holder.bind(loadState)
    }
    inner class LoadStateViewHolder(private val binding: MovieLoadStateFooterBinding): RecyclerView.ViewHolder(binding.root)
    {
        init {
            binding.retryButton.setOnClickListener{
                retry.invoke()
            }
        }

        fun bind(loadState: LoadState)
        {
            binding.apply {
                mProgressBar.isVisible = loadState is LoadState.Loading
                textViewError.isVisible = loadState is LoadState.Loading
                retryButton.isVisible = loadState is LoadState.Loading
            }
        }

    }
}