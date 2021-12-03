package com.example.tmdbapp.views

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.GridLayoutManager
import com.example.tmdbapp.adapters.RecycleViewAdapter
import com.example.tmdbapp.databinding.PopularMoviesFragmentBinding
import com.example.tmdbapp.viewModels.PopularMoviesEvents
import com.example.tmdbapp.viewModels.PopularMoviesFragmentViewModel
import dagger.hilt.android.AndroidEntryPoint

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
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initRecyclerView()
        subscribeObserver()
        if (savedInstanceState == null)
            loadFirstPage()
    }

    override fun onDestroyView() {
        super.onDestroyView()
        viewModel.movies.removeObservers(this)
        _binding = null
    }

    private fun subscribeObserver()
    {
            viewModel.movies.observe(viewLifecycleOwner, {
                it?.let {
                    adapter.submitData(viewLifecycleOwner.lifecycle, it)
                }
            })
    }

    private fun initRecyclerView()
    {
        binding.mRecyclerView.layoutManager = GridLayoutManager(context, 3)

        binding.mRecyclerView.adapter = adapter
        binding.mRecyclerView.setHasFixedSize(false)
    }

    private fun loadFirstPage()
    {
        viewModel.setStateEvent(PopularMoviesEvents.GetPopularMovies)
    }
}