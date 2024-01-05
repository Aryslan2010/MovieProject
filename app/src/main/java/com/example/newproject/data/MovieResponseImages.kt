package com.example.newproject.data

import com.google.gson.annotations.SerializedName
data class MovieResponseImages (
    @SerializedName("backdrops") val backdrops: List<Images>
)

data class Images(
    val aspect_ratio: Float,
    val height: Int,
    val iso_639_1: String?  = null,
    val file_path: String,
    val vote_average: Float,
    val vote_count: Int,
    val width: Float
)