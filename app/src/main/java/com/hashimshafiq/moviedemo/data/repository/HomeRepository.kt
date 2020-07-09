package com.hashimshafiq.moviedemo.data.repository

import com.hashimshafiq.moviedemo.data.local.db.DatabaseService
import com.hashimshafiq.moviedemo.data.local.db.entity.Movie
import com.hashimshafiq.moviedemo.data.remote.NetworkService
import com.hashimshafiq.moviedemo.data.remote.responses.MovieListResponse
import com.hashimshafiq.moviedemo.utils.network.NetworkBoundResource
import com.hashimshafiq.moviedemo.utils.network.NetworkHelper
import javax.inject.Inject

class HomeRepository @Inject constructor(
    private val networkService: NetworkService,
    private val databaseService: DatabaseService,
    private val networkHelper: NetworkHelper
){

    suspend fun fetchMovies(page : Int) : MovieListResponse? {

        return object : NetworkBoundResource(){

            override fun saveCallResult(request: MovieListResponse?) {
                databaseService.moviesDao().insertMoves(request?.movies!!)
            }

            override suspend fun loadFromDb(): List<Movie> {
                return databaseService.moviesDao().getMovies()
            }

            override suspend fun loadFromNetwork(): MovieListResponse {
                return networkService.getPopularMovies(page = page)
            }

            override fun shouldFetch(): Boolean {
                return networkHelper.isNetworkConnected()
            }


        }.execute()


    }

}