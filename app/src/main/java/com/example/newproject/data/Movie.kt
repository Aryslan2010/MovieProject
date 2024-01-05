package com.example.newproject.data

import com.example.newproject.db.MovieEntity

import com.google.gson.annotations.SerializedName

data class Movie(
    @SerializedName("id") val id: Long,
    @SerializedName("title") val title: String,
    @SerializedName("release_date") val releaseData: String,
    @SerializedName("budget") val budget: Int,
    @SerializedName("poster_path") val poster: String?,
    ){
    fun toMovieEntity(): MovieEntity{
        return MovieEntity(id,title,releaseData,budget,poster)
    }


}


