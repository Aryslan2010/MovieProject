package com.example.newproject

import android.content.Context
import android.content.Intent
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.EditText
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.activity.viewModels
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import com.bumptech.glide.Glide
import com.example.newproject.data.Movie
import com.example.newproject.data.MovieData01
import com.example.newproject.data.MovieResponseImages
import com.example.newproject.data.MovieStorage
import com.example.newproject.repository.MovieRepositoryImpl
import com.example.newproject.repository.MovieRepositoty
import com.example.newproject.repository.numberOfMovie
import com.example.newproject.vm.MovieViewModel
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch



val listImage: MutableList<String> = mutableListOf()
val listData2: MutableList<MovieData01> = mutableListOf()
class MainActivity() : AppCompatActivity() {
    private val movieIdEdit: EditText by lazy { findViewById(R.id.movie_id_edit)}
    private val loadButton: Button by lazy { findViewById(R.id.load_button)}
    private val DataButton: Button by lazy { findViewById(R.id.new_button)}

    private val movieTitle: TextView by lazy { findViewById(R.id.movie_title_value)}
    private val movieRelease: TextView by lazy { findViewById(R.id.movie_release_value)}
    private val movieBudget: TextView by lazy { findViewById(R.id.movie_budget_value)}

    private val moviePoster: ImageView by lazy {findViewById(R.id.movie_poster) }

    private val DownloadButton: Button by lazy { findViewById(R.id.download_button)}
    var IntegerButton: Int = 0

    private fun SetMovieImage(movie: MovieResponseImages?) {
        if (movie != null) {
            for (i in movie.backdrops.indices){
                listImage.add(movie.backdrops[i].file_path)
            }
            }

    }

    private fun SetMovie (movie: Movie?){
        if (movie == null){return}
        Log.i(TAG,"Movie arrived -> $movie")
        movieTitle.text  = movie.title
        movieRelease.text  = movie.releaseData
        movieBudget.text  = movie.budget.toString()

        Glide.with(this)
            .load(Uri.parse("https://image.tmdb.org/t/p/original/${movie.poster}"))
            .fitCenter()
            .into(moviePoster)

    }

    val moviesList = listOf(
        MovieData01(1, "The Shawshank Redemption"),
        MovieData01(2, "The Godfather"),
        MovieData01(3, "The Dark Knight")
    )




    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        Log.d(TAG,"Activity -> OnCreate()")
        val viewModel: MovieViewModel by viewModels()
        val viewModel2: MovieViewModel by viewModels()
        val viewModel3: MovieViewModel by viewModels()
        val preferences = getSharedPreferences("default", Context.MODE_PRIVATE)

        val moviesList = listOf(
            MovieData01(1, "The Shawshank Redemption"),
            MovieData01(2, "The Godfather"),
            MovieData01(3, "The Dark Knight")
        )


    lifecycleScope.launch {
        repeatOnLifecycle(Lifecycle.State.STARTED){
            viewModel.currentMovie.collect{movie ->
                SetMovie(movie)

            }

        }
    }
        lifecycleScope.launch {
            repeatOnLifecycle(Lifecycle.State.STARTED) {
                viewModel.currentMovie1.collect { movie ->
                    SetMovieImage(movie)
                }
            }
        }

        lifecycleScope.launch {
            repeatOnLifecycle(Lifecycle.State.STARTED) {
                viewModel3.currentMovie2.collect { movie ->
                    if (movie != null) {
                        listData2.add(movie)
                        Log.i(TAG,"$movie")
                    }
                }
            }
        }

        loadButton.setOnClickListener{
            val movieId = movieIdEdit.text.toString().toLongOrNull()
            IntegerButton++
            preferences.edit().putInt("movieDownload",IntegerButton).apply()
            if(movieId != null){
                viewModel.loadMovie(movieId)
                viewModel2.loadMovieImage(movieId)
            }
            Toast.makeText(applicationContext,"Кнопка была нажата $IntegerButton раз", Toast.LENGTH_SHORT).show()
        }
        DataButton.setOnClickListener{
            val number = numberOfMovie.size
            for(i in numberOfMovie){

                viewModel3.loadMovieData(i)
                Log.i(TAG,"Вызов фора")
                Log.i(TAG,"$number")
            }

            val intent = Intent(this,MovieListActivity::class.java)
            lifecycleScope.launch {
                delay(10000L)
                MovieStorage.moviesList1 = listData2
            startActivity(intent)}
        }


        val SaveButton = preferences.getInt("movieDownload", Int.MIN_VALUE)
        if(SaveButton != Int.MIN_VALUE){
            IntegerButton = SaveButton
        }

        DownloadButton.setOnClickListener{

            val movieId = movieIdEdit.text.toString().toLongOrNull()
            if(movieId != null){
                val intent = Intent(this,ImagesActivity::class.java)
                val stingimage = listImage.toString()
                val elementsList = stingimage
                    .trim('[', ']')
                    .split(", ")
                intent.putStringArrayListExtra("listDownload",ArrayList(elementsList))
                startActivity(intent)
            }
            else Toast.makeText(applicationContext,"Вы не ввели moveId",Toast.LENGTH_SHORT).show()
        }

    }

    override fun onStart() {
        super.onStart()
        Log.d(TAG,"Activity -> onStart()")


        }
    companion object {
        const val TAG = "MainActivity1"
    }
}





