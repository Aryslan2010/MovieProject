package com.example.newproject.repository
import android.content.Context
import android.util.Log
import androidx.room.Room
import com.example.newproject.data.Movie
import com.example.newproject.data.MovieResponseImages
import com.example.newproject.db.MovieDatabase
import com.example.newproject.db.MovieEntity

val numberOfMovie: MutableList<Long> = mutableListOf()
class MovieRepositoryImpl(
    private val context: Context
): MovieRepositoty {
        var database = Room
        .databaseBuilder(context, MovieDatabase::class.java, "movie_database")
        .build()
    override suspend fun getMovieDetails(movieId: Long): Movie? {
        val savedMovieEntity = database.MovieDao().get(movieId)
        numberOfMovie.add(movieId)
        return if(savedMovieEntity != null){
            Log.i(TAG,"Movie was saved before, reading from database")
            savedMovieEntity.toMovie()

        }else{
        val response = MovieRepositoty.MovieApi.INSTANCE.getMovieDetails(movieId, API_KEY).execute()
            if(response.isSuccessful){
                val movie = response.body()
                if(movie != null){
                    Log.i(TAG,"Movie is new, saving to database")
                    database.MovieDao().insert(movie.toMovieEntity())
                }
                movie
                }else null}
    }
override suspend fun getSDASD(movieId: Long): Movie? {
    return database.MovieDao().getAll(movieId)?.toMovie()
}
    override suspend fun getMovieImage(movieId: Long): MovieResponseImages? {
        val response = MovieRepositoty.MovieApi.INSTANCE.getMovieImages(movieId, API_KEY).execute()
        return if(response.isSuccessful)response.body() else null
    }



    companion object {
        const val API_KEY = "95d291306c05c68fb2617a17182a102b"
        const val TAG = "MovieRepositoryImpl"
    }

}



