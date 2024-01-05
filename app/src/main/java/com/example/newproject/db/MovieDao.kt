package com.example.newproject.db

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query

@Dao
interface MovieDao{
    @Insert
    fun insert(movieEntity: MovieEntity)
    @Query("SELECT * FROM movieentity WHERE id = :movieId LIMIT 1")
    fun get(movieId:Long): MovieEntity?

   @Query("SELECT * FROM movieentity WHERE id = :movieId")
   fun getAll(movieId:Long): MovieEntity?


}