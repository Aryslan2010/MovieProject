package com.example.newproject.vm



import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.viewModelScope
import com.example.newproject.data.Movie
import com.example.newproject.data.MovieData01
import com.example.newproject.data.MovieResponseImages
import com.example.newproject.repository.MovieRepositoryImpl
import com.example.newproject.repository.MovieRepositoty
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class MovieViewModel(application: Application):AndroidViewModel(application) {
    private val _currentMovie = MutableStateFlow<Movie?>(null)
    private val repositoty: MovieRepositoty = MovieRepositoryImpl(application)
    val currentMovie: StateFlow<Movie?> = _currentMovie
    fun loadMovie(movieId: Long){
        viewModelScope.launch(Dispatchers.IO){
            val movie = repositoty.getMovieDetails(movieId)
            _currentMovie.value = movie
        }

    }
    private val _currentMovie2 = MutableStateFlow<MovieData01?>(null)
    val currentMovie2: StateFlow<MovieData01?> = _currentMovie2
    fun loadMovieData(movieId: Long) {
        viewModelScope.launch(Dispatchers.IO){
            val movie = repositoty.getSDASD(movieId)
            if (movie != null) {
                _currentMovie2.value = MovieData01(movie.id,movie.title)
            }

        }

    }



    private val _currentMovie_ = MutableStateFlow<MovieResponseImages?>(null)
    val currentMovie1: StateFlow<MovieResponseImages?> = _currentMovie_
    fun loadMovieImage(movieId: Long){
        viewModelScope.launch(Dispatchers.IO){
            val movie = repositoty.getMovieImage(movieId)
            _currentMovie_.value = movie
        }

    }

}