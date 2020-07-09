package com.hashimshafiq.moviedemo.data.local.db

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.hashimshafiq.moviedemo.data.local.db.dao.MoviesDao
import com.hashimshafiq.moviedemo.data.model.Movie
import javax.inject.Singleton

@Singleton
@Database(entities = [Movie::class],
        version = 1,
        exportSchema = false)
@TypeConverters(Converters::class)
abstract class DatabaseService : RoomDatabase() {
    abstract fun moviesDao() : MoviesDao

}