package com.example.tmdbapp.views


import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.drawable.DrawableTransitionOptions
import com.example.tmdbapp.databinding.MovieDetailsFragmentBinding
import com.example.tmdbapp.model.MovieEntity
import com.example.tmdbapp.repository.MyRepository.Companion.IMAGE_BASE_URL
import com.example.tmdbapp.viewModels.MovieDetailsViewModel


class MovieDetailsFragment : Fragment() {


    private val viewModel: MovieDetailsViewModel by viewModels()
    private var _binding: MovieDetailsFragmentBinding? = null
    private val binding get() = _binding!!
    private var movieEntity: MovieEntity? = null

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = MovieDetailsFragmentBinding.inflate(inflater, container, false)

        arguments?.let{
            movieEntity = it.getParcelable("movie")
        }

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        movieEntity?.apply {
            Glide.with(binding.detailsImage)
                .load(IMAGE_BASE_URL + poster_path)
                .centerCrop()
                .transition(DrawableTransitionOptions.withCrossFade())
                .error(android.R.drawable.ic_menu_close_clear_cancel)
                .into(binding.detailsImage)

            binding.detailsTitle.text = title
            binding.detailsOverview.text = overview
        }
    }

}