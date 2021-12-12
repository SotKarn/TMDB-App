package com.example.tmdbapp.views


import android.graphics.Color
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.TextView
import androidx.cardview.widget.CardView
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.paging.ExperimentalPagingApi
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.drawable.DrawableTransitionOptions
import com.example.tmdbapp.databinding.MovieDetailsFragmentBinding
import com.example.tmdbapp.model.MovieEntity
import com.example.tmdbapp.model.MovieInfo
import com.example.tmdbapp.retrofit.IWebService.Companion.IMAGE_BASE_URL
import com.example.tmdbapp.viewModels.MovieDetailsViewModel
import dagger.hilt.android.AndroidEntryPoint

@ExperimentalPagingApi
@AndroidEntryPoint
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

        subscribeObserver()
        return binding.root
    }

    private fun subscribeObserver() {
        lifecycleScope.launchWhenCreated {
            viewModel.movieInfo.observe(viewLifecycleOwner, { movieInfo->

                Glide.with(binding.detailsImage)
                     .load(IMAGE_BASE_URL + movieInfo.poster_path)
                     .transition(DrawableTransitionOptions.withCrossFade())
                     .error(android.R.drawable.ic_menu_close_clear_cancel)
                     .into(binding.detailsImage)

                binding.detailsTitle.text = movieInfo.title
                binding.detailsReleaseDate.text = movieInfo.release_date
                binding.detailsVote.text = movieInfo.vote_average.toString()
                binding.detailsOverview.text = movieInfo.overview

                // Add Genres of Movie
                val genresString = buildGenresString(movieInfo.genres)
                genresString?.let {
                    binding.detailsGenres.text = it
                }

                //Add Cast CardViews
                addCastCardViews(movieInfo.credits.cast, binding.castLinearLayout)

            })
        }

        viewModel.similarMovies.observe(viewLifecycleOwner, {
            addCastCardViews(it, binding.similarMoviesLinearLayout)
        })
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        movieEntity?.apply {
            viewModel.getMovieInfo(id)
        }
    }

    private fun buildGenresString(list: List<MovieInfo.Genre>): String?{
        var genreString: String? = null
        list.forEach { genre->

            genreString = if (list[list.size - 1] == genre)
            {
                if(genreString == null) genre.name else  genreString + genre.name
            }
            else
            {
                if(genreString == null) genre.name + ", " else genreString + genre.name + ", "
            }
        }
        return genreString
    }


    private fun <T> addCastCardViews(list: List<T>, container: LinearLayout)
    {
        val linearLayoutParams by lazy {
            LinearLayout.LayoutParams(
                LinearLayout.LayoutParams.WRAP_CONTENT,
                LinearLayout.LayoutParams.WRAP_CONTENT
             ).also {
                 it.setMargins(20,20,20, 20)
            }
        }

        val cardNameLayoutParams by lazy {
            LinearLayout.LayoutParams(
                LinearLayout.LayoutParams.WRAP_CONTENT,
                LinearLayout.LayoutParams.WRAP_CONTENT
            ).also {
                it.setMargins(20,0, 0 ,0)
            }
        }

        val imageViewLayoutParams by lazy {
            LinearLayout.LayoutParams(
                LinearLayout.LayoutParams.WRAP_CONTENT,
                LinearLayout.LayoutParams.WRAP_CONTENT
            )
        }

        when(container)
        {
            binding.castLinearLayout -> {

                val cardViewLayoutParams = LinearLayout.LayoutParams(150, 200)
                list.filterIsInstance<MovieInfo.Credits.Cast>().forEach {

                    val linearLayout = LinearLayout(this.requireContext())
                    linearLayout.orientation = LinearLayout.HORIZONTAL
                    linearLayout.layoutParams = linearLayoutParams

                    val cardView = CardView(this.requireContext())
                    cardView.radius = 20F
                    cardView.elevation = 8F
                    cardView.layoutParams = cardViewLayoutParams

                    val imageView = ImageView(this.requireContext())
                    imageView.scaleType = ImageView.ScaleType.FIT_XY
                    imageView.layoutParams = imageViewLayoutParams
                    Glide.with(imageView)
                        .load(IMAGE_BASE_URL + it.profile_path)
                        .transition(DrawableTransitionOptions.withCrossFade())
                        .error(android.R.drawable.ic_menu_close_clear_cancel)
                        .into(imageView)

                    val castName = TextView(this.requireContext())
                    castName.layoutParams = cardNameLayoutParams
                    castName.width = 200
                    castName.setTextColor(Color.WHITE)
                    castName.autoSizeTextType
                    castName.text = String.format("%s as %s", it.name, it.character)

                    cardView.addView(imageView)

                    linearLayout.addView(cardView)
                    linearLayout.addView(castName)

                   container.addView(linearLayout)
                }
            }
            binding.similarMoviesLinearLayout -> {

                val cardViewLayoutParams = LinearLayout.LayoutParams(200, 300)
                    .also {
                        it.setMargins(5,5, 5, 5 )
                    }

                list.filterIsInstance<MovieEntity>().forEach {

                        val cardView = CardView(this.requireContext()).apply {
                            radius = 20F
                            elevation = 8F
                            layoutParams = cardViewLayoutParams
                        }



                        val imageView = ImageView(this.requireContext())
                        imageView.scaleType = ImageView.ScaleType.FIT_XY
                        imageView.layoutParams = imageViewLayoutParams
                        Glide.with(imageView)
                            .load(IMAGE_BASE_URL + it.poster_path)
                            .transition(DrawableTransitionOptions.withCrossFade())
                            .error(android.R.drawable.ic_menu_close_clear_cancel)
                            .into(imageView)

                        cardView.addView(imageView)
                        container.addView(cardView)
                    }
                }
        }
    }
 }





