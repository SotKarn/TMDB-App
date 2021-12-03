package com.example.tmdbapp.viewModels

import androidx.lifecycle.*
import androidx.paging.PagingData
import androidx.paging.cachedIn
import com.example.tmdbapp.model.web.WebMovieEntity
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

    private val _movieList = MutableLiveData<PagingData<WebMovieEntity>>()
    val movies: LiveData<PagingData<WebMovieEntity>>
        get() = _movieList

    fun setStateEvent(popularMoviesEvents: PopularMoviesEvents)
    {
        viewModelScope.launch {
            repo.getPopularMovies().cachedIn(viewModelScope).onEach {
                _movieList.value = it
            }.launchIn(viewModelScope)
        }
    }
}

sealed class PopularMoviesEvents{
    object GetPopularMovies: PopularMoviesEvents()
    object GetMovieInfo: PopularMoviesEvents()
}