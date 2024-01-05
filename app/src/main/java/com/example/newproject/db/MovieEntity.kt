package com.example.newproject.db

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.Ignore
import androidx.room.PrimaryKey
import com.example.newproject.data.Movie

@Entity
data class MovieEntity(
    @PrimaryKey(autoGenerate = false)val id: Long,
    @ColumnInfo(name = "title")val title:String,
    @ColumnInfo(name = "release_data")val releaseData: String,
    @ColumnInfo(name = "budget")val budget: Int,
    @ColumnInfo(name = "poster") val poster: String?
){
    @Ignore
    fun toMovie(): Movie {
        return Movie(id,title,releaseData,budget,poster)
    }


}
//data class  MovieSpisok(
//    @PrimaryKey(autoGenerate = false)val id: Long,
//    @ColumnInfo(name = "title")val title:String,
//    ){
//    @Ignore
//    fun ToSpisok(): MovieSpisok{
//        return MovieSpisok(id,title)
//    }
//}
