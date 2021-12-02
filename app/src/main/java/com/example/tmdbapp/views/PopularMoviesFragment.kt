package com.example.tmdbapp.views

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.tmdbapp.adapters.RecycleViewAdapter
import com.example.tmdbapp.databinding.PopularMoviesFragmentBinding
import com.example.tmdbapp.model.cache.CachedMovie
import com.example.tmdbapp.viewModels.PopularMoviesEvents
import com.example.tmdbapp.viewModels.PopularMoviesFragmentViewModel
import dagger.hilt.android.AndroidEntryPoint


@AndroidEntryPoint
class PopularMoviesFragment : Fragment() {

    private var movieList: MutableList<CachedMovie> = mutableListOf()
    private lateinit var viewModel: PopularMoviesFragmentViewModel
    private var _binding: PopularMoviesFragmentBinding? = null
    private val adapter: RecycleViewAdapter = RecycleViewAdapter()
    private val binding get() = _binding!!
    private var isLoading: Boolean = false

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewModel = ViewModelProvider(this)[PopularMoviesFragmentViewModel::class.java]
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = PopularMoviesFragmentBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        initRecyclerView()
        subscribeObserver()
        loadFirstPage()
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    private fun subscribeObserver() {
        viewModel.movies.observe(this, {
            movieList.addAll(it)
            adapter.updateMovies(it)
            isLoading = false
        })
    }

    private fun initRecyclerView()
    {
        binding.mRecyclerView.layoutManager = GridLayoutManager(context, 3)
        binding.mRecyclerView.adapter = adapter
        binding.mRecyclerView.setHasFixedSize(false)

        binding.mRecyclerView.addOnScrollListener(object : RecyclerView.OnScrollListener(){

            override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {
                if (!isLoading && (binding.mRecyclerView.layoutManager as LinearLayoutManager).findLastCompletelyVisibleItemPosition() == movieList.size - 1 )
                {
                    isLoading = true
                    adapter.addLoadingItem()
                    viewModel.setStateEvent(PopularMoviesEvents.GetPopularMovies)
                }
            }
        })
    }

    private fun loadFirstPage()
    {
        isLoading = true
        adapter.addLoadingItem()
        viewModel.setStateEvent(PopularMoviesEvents.GetPopularMovies)
    }
}