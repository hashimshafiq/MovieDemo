package com.hashimshafiq.moviedemo.data.local.db.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.hashimshafiq.moviedemo.data.local.db.entity.Movie

@Dao
interface MoviesDao {
    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insertMoves(movies: List<Movie>)

    @Query("SELECT * FROM movies ORDER BY vote_average DESC")
    suspend fun getMovies(): List<Movie>
}