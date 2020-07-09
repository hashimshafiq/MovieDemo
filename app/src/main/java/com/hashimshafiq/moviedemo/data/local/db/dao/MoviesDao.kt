package com.hashimshafiq.moviedemo.data.local.db.dao

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.hashimshafiq.moviedemo.data.local.db.entity.MovieEntity
import com.hashimshafiq.moviedemo.data.model.Movie

@Dao
interface MoviesDao {
    @Insert(onConflict = OnConflictStrategy.IGNORE)
    fun insertMoves(vararg movies: MovieEntity)

    @Query("SELECT * FROM movies ORDER BY vote_average DESC LIMIT (:pageNumber*30)")
    fun getMovies(pageNumber: Int): LiveData<List<Movie>>
}