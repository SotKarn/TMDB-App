package com.example.tmdbapp.viewModels

import androidx.lifecycle.*
import androidx.paging.ExperimentalPagingApi
import androidx.paging.PagingData
import androidx.paging.cachedIn
import com.example.tmdbapp.model.web.MovieEntity
import com.example.tmdbapp.repository.MyRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.launch

import javax.inject.Inject

@HiltViewModel
class PopularMoviesFragmentViewModel @Inject constructor(
    private val repo: MyRepository
): ViewModel() {

    private val _movieList = MutableLiveData<PagingData<MovieEntity>>()
    val movies: LiveData<PagingData<MovieEntity>>
        get() = _movieList

    @ExperimentalPagingApi
    fun setStateEvent(popularMoviesEvents: PopularMoviesEvents)
    {
        when(popularMoviesEvents){
            PopularMoviesEvents.GetPopularMovies -> {
                viewModelScope.launch {
                    repo.getPopularMovies().cachedIn(viewModelScope).onEach {
                        _movieList.value = it
                    }.launchIn(viewModelScope)
                }
            }
            PopularMoviesEvents.GetMovieInfo -> {}
        }
    }
}

sealed class PopularMoviesEvents{
    object GetPopularMovies: PopularMoviesEvents()
    object GetMovieInfo: PopularMoviesEvents()
}