package com.example.tmdbapp.viewModels

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.tmdbapp.model.MovieInfo
import com.example.tmdbapp.repository.MyRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MovieDetailsViewModel @Inject constructor(
    private val repo: MyRepository
): ViewModel() {

    private val _movieInfo = MutableLiveData<MovieInfo>()
    val movieInfo: LiveData<MovieInfo>
        get() = _movieInfo

    fun getMovieInfo(id: Int)
    {
        viewModelScope.launch {
             repo.getMovieInfo(id).onEach {
                 _movieInfo.value = it
             }.launchIn(viewModelScope)
        }
    }
}