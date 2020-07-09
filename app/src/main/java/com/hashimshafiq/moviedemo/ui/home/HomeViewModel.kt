package com.hashimshafiq.moviedemo.ui.home

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.hashimshafiq.moviedemo.data.remote.responses.MovieListResponse
import com.hashimshafiq.moviedemo.data.repository.HomeRepository
import com.hashimshafiq.moviedemo.ui.base.BaseViewModel
import com.hashimshafiq.moviedemo.utils.common.Resource
import com.hashimshafiq.moviedemo.utils.network.NetworkHelper
import kotlinx.coroutines.launch


class HomeViewModel(networkHelper: NetworkHelper, private val homeRepository: HomeRepository) : BaseViewModel(networkHelper) {

    private val moviesList = MutableLiveData<Resource<MovieListResponse>>()
    private var movieListResponse : MovieListResponse? = null

    override fun onCreate() {
        doFetchMovies()
    }

    private fun doFetchMovies(pageNumber : Int = 1){
        if (!networkHelper.isNetworkConnected()){
            moviesList.postValue(Resource.error())
            return
        }
        viewModelScope.launch {
            moviesList.postValue(Resource.loading())
            try {
                val res = homeRepository.fetchMovies(pageNumber)
                movieListResponse = res
                moviesList.postValue(Resource.success(res))

            }catch (e : Exception){
                moviesList.postValue(Resource.unknown())
            }
        }
    }

    fun getMoviesList() : LiveData<Resource<MovieListResponse>> {
        return moviesList
    }

    fun loadMore() {
        movieListResponse?.let {
            if (it.page <= it.totalPages){
                doFetchMovies(pageNumber = it.page+1)
            }
        } ?: run {
            doFetchMovies()
        }

    }
}