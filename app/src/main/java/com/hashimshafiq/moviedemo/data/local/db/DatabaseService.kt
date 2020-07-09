package com.hashimshafiq.moviedemo.data.local.db

import androidx.room.Database
import androidx.room.RoomDatabase
import com.hashimshafiq.moviedemo.data.local.db.dao.MoviesDao
import com.hashimshafiq.moviedemo.data.local.db.entity.Movie
import javax.inject.Singleton

@Singleton
@Database(entities = [Movie::class],
        version = 1,
        exportSchema = false)
abstract class DatabaseService : RoomDatabase() {
    abstract fun moviesDao() : MoviesDao

}