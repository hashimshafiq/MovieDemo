package com.hashimshafiq.moviedemo.ui.home

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.hashimshafiq.moviedemo.data.remote.responses.MovieListResponse
import com.hashimshafiq.moviedemo.data.repository.HomeRepository
import com.hashimshafiq.moviedemo.ui.base.BaseViewModel
import com.hashimshafiq.moviedemo.utils.common.Resource
import com.hashimshafiq.moviedemo.utils.network.NetworkHelper
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(networkHelper: NetworkHelper, private val homeRepository: HomeRepository) : BaseViewModel(networkHelper) {

    private val moviesList = MutableLiveData<Resource<MovieListResponse>>()
    private var movieListResponse : MovieListResponse? = null

    override fun onCreate() {
        doFetchMovies()
    }

    private fun doFetchMovies(pageNumber : Int = 1){
        viewModelScope.launch(Dispatchers.IO) {
            moviesList.postValue(Resource.loading())
            try {
                val res = homeRepository.fetchMovies(pageNumber)
                res?.let {
                    if (it.movies!!.isNotEmpty()){
                        movieListResponse = res
                        moviesList.postValue(Resource.success(res))
                    }else{
                        moviesList.postValue(Resource.error())
                    }
                }
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
            if (it.page < it.totalPages){
                doFetchMovies(pageNumber = it.page+1)
            }
        }
    }
}