package com.example.tmdbapp.viewModels

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.ExperimentalPagingApi
import androidx.paging.PagingData
import androidx.paging.cachedIn
import androidx.paging.map
import com.example.tmdbapp.model.MovieEntity
import com.example.tmdbapp.model.MovieInfo
import com.example.tmdbapp.repository.MyRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MovieDetailsViewModel @Inject constructor(
    private val repo: MyRepository
): ViewModel() {

    private val _movieInfo = MutableLiveData<MovieInfo>()
    private val _similarMovies = MutableLiveData<List<MovieEntity>>()
    val movieInfo: LiveData<MovieInfo>
        get() = _movieInfo
    val similarMovies: LiveData<List<MovieEntity>>
        get() = _similarMovies


    @ExperimentalPagingApi
    fun getMovieInfo(id: Int)
    {
        viewModelScope.launch {
             repo.getMovieInfo(id).onEach {
                 _movieInfo.value = it
             }.launchIn(viewModelScope)

            repo.getSimilarMovies(id).onEach {
                _similarMovies.value = it
            }.launchIn(viewModelScope)

        }
    }

}