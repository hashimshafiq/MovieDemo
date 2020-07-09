package com.hashimshafiq.moviedemo.data.repository

import com.hashimshafiq.moviedemo.data.local.db.DatabaseService
import com.hashimshafiq.moviedemo.data.remote.NetworkService
import com.hashimshafiq.moviedemo.data.remote.responses.MovieListResponse
import javax.inject.Inject

class HomeRepository @Inject constructor(
    private val networkService: NetworkService,
    private val databaseService: DatabaseService
){

    suspend fun fetchMovies(page : Int) : MovieListResponse {
        return networkService.getPopularMovies(page = page)
    }

}