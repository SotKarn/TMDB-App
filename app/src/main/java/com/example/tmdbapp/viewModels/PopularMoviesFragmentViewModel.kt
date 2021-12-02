package com.example.tmdbapp.viewModels

import androidx.lifecycle.*
import com.example.tmdbapp.model.cache.CachedMovie
import com.example.tmdbapp.model.local.Movie
import com.example.tmdbapp.repository.MyRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class PopularMoviesFragmentViewModel @Inject constructor(private val repo: MyRepository ) : ViewModel() {


    private val _movieList = MutableLiveData<List<CachedMovie>>()
    val movies: LiveData<List<CachedMovie>>
        get() = _movieList

    fun setStateEvent(popularMoviesEvents: PopularMoviesEvents)
    {
        viewModelScope.launch {
            when(popularMoviesEvents)
            {
                PopularMoviesEvents.GetPopularMovies -> {
                    val movieList: List<CachedMovie>? = repo.getPopularMovies()
                    movieList?.let {
                        _movieList.value = it
                    }

                }
                PopularMoviesEvents.GetMovieInfo -> TODO()
            }
        }
    }

}

sealed class PopularMoviesEvents{
    object GetPopularMovies: PopularMoviesEvents()
    object GetMovieInfo: PopularMoviesEvents()
}