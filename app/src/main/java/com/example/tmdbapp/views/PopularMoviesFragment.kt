package com.example.tmdbapp.views

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.GridLayoutManager
import com.example.tmdbapp.adapters.SwipeRefreshLayoutAdapter
import com.example.tmdbapp.databinding.PopularMoviesFragmentBinding
import com.example.tmdbapp.viewModels.PopularMoviesEvents
import com.example.tmdbapp.viewModels.PopularMoviesFragmentViewModel
import dagger.hilt.android.AndroidEntryPoint


@AndroidEntryPoint
class PopularMoviesFragment : Fragment() {

    private lateinit var viewModel: PopularMoviesFragmentViewModel
    private var _binding: PopularMoviesFragmentBinding? = null
    private val adapter: SwipeRefreshLayoutAdapter = SwipeRefreshLayoutAdapter()
    private val binding get() = _binding!!

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewModel = ViewModelProvider(this).get(PopularMoviesFragmentViewModel::class.java).apply {
            setIndex(arguments?.getInt(ARG_SECTION_NUMBER) ?: 1)
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = PopularMoviesFragmentBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        initRecyclerView()
        subscribeObserver()
        viewModel.setStateEvent(PopularMoviesEvents.GetPopularMovies)
    }

    private fun subscribeObserver() {
        viewModel.movies.observe(this, {

            adapter.updatePosts(it)
            binding.swipeRefreshLayout.isRefreshing = false

        })
    }

    private fun initRecyclerView()
    {
        binding.mRecyclerView.layoutManager = GridLayoutManager(context, 3)
        binding.mRecyclerView.adapter = adapter
        binding.mRecyclerView.setHasFixedSize(false)
    }

    companion object {
        /**
         * The fragment argument representing the section number for this
         * fragment.
         */
        private const val ARG_SECTION_NUMBER = "section_number"

        /**
         * Returns a new instance of this fragment for the given section
         * number.
         */
        @JvmStatic
        fun newInstance(sectionNumber: Int): PopularMoviesFragment {
            return PopularMoviesFragment().apply {
                arguments = Bundle().apply {
                    putInt(ARG_SECTION_NUMBER, sectionNumber)
                }
            }
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}