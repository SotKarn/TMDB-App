package com.example.tmdbapp.views

import android.content.res.Configuration
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.widget.SearchView
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.paging.*
import androidx.recyclerview.widget.GridLayoutManager
import com.example.tmdbapp.adapters.MovieLoaderStateAdapter
import com.example.tmdbapp.adapters.RecycleViewAdapter
import com.example.tmdbapp.databinding.PopularMoviesFragmentBinding
import com.example.tmdbapp.viewModels.MoviesEvents
import com.example.tmdbapp.viewModels.PopularMoviesFragmentViewModel
import dagger.hilt.android.AndroidEntryPoint


@ExperimentalPagingApi
@AndroidEntryPoint
class PopularMoviesFragment : Fragment() {

    private val viewModel  by viewModels<PopularMoviesFragmentViewModel>()
    private var _binding: PopularMoviesFragmentBinding? = null
    private val adapter: RecycleViewAdapter = RecycleViewAdapter()
    private val binding get() = _binding!!


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = PopularMoviesFragmentBinding.inflate(inflater, container, false)
        subscribeObserver()
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initRecyclerView()

        if (viewModel.movies.value == null)
        {
           viewModel.setStateEvent(MoviesEvents.GetPopularMovies, null)
        }
    }

    override fun onResume() {
        super.onResume()
        binding.searchView.setOnQueryTextListener(object: SearchView.OnQueryTextListener{
            override fun onQueryTextSubmit(query: String?): Boolean {
                return true
            }

            override fun onQueryTextChange(newText: String?): Boolean
            {
                newText?.let {
                    if (it.isNotBlank())
                    {
                        adapter.submitData(viewLifecycleOwner.lifecycle, PagingData.empty())
                        viewModel.setStateEvent(MoviesEvents.SearchMovies, it)
                    }
                    else viewModel.setStateEvent(MoviesEvents.GetPopularMovies, null)
                }
                return false
            }
        })
    }
    private fun initRecyclerView()
    {
        binding.mRecyclerView.layoutManager = if (resources.configuration.orientation == Configuration.ORIENTATION_LANDSCAPE) GridLayoutManager(context, 5)
        else GridLayoutManager(context, 3)
        binding.mRecyclerView.adapter = adapter.withLoadStateHeaderAndFooter(
            header = MovieLoaderStateAdapter{adapter.retry()},
            footer = MovieLoaderStateAdapter{ adapter.retry()}
        )
        binding.mRecyclerView.setHasFixedSize(false)
    }

    private fun subscribeObserver()
    {
        viewModel.movies.observe(viewLifecycleOwner, {
            adapter.submitData(viewLifecycleOwner.lifecycle, it)
        })
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}