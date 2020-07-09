package com.hashimshafiq.moviedemo.ui.home

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.hashimshafiq.moviedemo.R
import com.hashimshafiq.moviedemo.data.remote.responses.MovieListResponse
import com.hashimshafiq.moviedemo.data.repository.HomeRepository
import com.hashimshafiq.moviedemo.ui.base.BaseViewModel
import com.hashimshafiq.moviedemo.utils.common.Resource
import com.hashimshafiq.moviedemo.utils.network.NetworkHelper
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch


class HomeViewModel(networkHelper: NetworkHelper, private val homeRepository: HomeRepository) : BaseViewModel(networkHelper) {

    private val moviesList = MutableLiveData<Resource<MovieListResponse>>()
    private var pageNumber = 1;

    override fun onCreate() {
        doFetchMovies()
    }

    private fun doFetchMovies(){
        if (!networkHelper.isNetworkConnected()){
            messageStringId.postValue(Resource.error(R.string.network_connection_error))
            return
        }
        viewModelScope.launch {
            moviesList.postValue(Resource.loading())
            try {
                val res = homeRepository.fetchMovies(pageNumber)
                moviesList.postValue(Resource.success(res))

            }catch (e : Exception){
                messageStringId.postValue(Resource.error(R.string.network_default_error))
            }
        }
    }

    fun getMoviesList() : LiveData<Resource<MovieListResponse>> {
        return moviesList
    }

    fun loadMore() {
        pageNumber += 1
        doFetchMovies()
    }
}