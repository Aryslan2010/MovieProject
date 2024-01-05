package com.example.newproject

import android.os.Bundle
import android.widget.TextView
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.newproject.data.MovieData01
import com.example.newproject.data.MovieStorage
import com.example.newproject.repository.numberOfMovie
import com.example.newproject.vm.MovieViewModel
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch


class MovieListActivity : AppCompatActivity() {
    private lateinit var rvMovieList: RecyclerView
    private lateinit var movieAdapter: MovieAdapter
    val viewModel3: MovieViewModel by viewModels()


    override fun onStart() {
        super.onStart()
        val movieList = MovieStorage.moviesList1

        movieAdapter = MovieAdapter(movieList)  // Pass initialized movieList to the adapter
        rvMovieList = findViewById(R.id.rvMovieList)
        rvMovieList.layoutManager = LinearLayoutManager(this)
        rvMovieList.adapter = movieAdapter
    }


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_movie_list)

    }

}

