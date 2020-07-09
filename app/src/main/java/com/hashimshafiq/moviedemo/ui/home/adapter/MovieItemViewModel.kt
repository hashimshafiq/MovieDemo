package com.hashimshafiq.moviedemo.ui.home.adapter

import androidx.lifecycle.LiveData
import androidx.lifecycle.Transformations
import com.hashimshafiq.moviedemo.data.model.Movie
import com.hashimshafiq.moviedemo.ui.base.BaseItemViewModel
import com.hashimshafiq.moviedemo.utils.common.Constants
import com.hashimshafiq.moviedemo.utils.common.TimeUtils
import com.hashimshafiq.moviedemo.utils.network.NetworkHelper
import java.lang.NumberFormatException
import javax.inject.Inject

class MovieItemViewModel @Inject constructor(networkHelper: NetworkHelper) : BaseItemViewModel<Movie>(networkHelper) {

    override fun onCreate() {}

    val imageDetail : LiveData<String> = Transformations.map(data){
        Constants.IMAGE_BASE_UR+it.poster_path
    }

    val movieName : LiveData<String> = Transformations.map(data){ it.title }

    val movieAverageVote : LiveData<Int> = Transformations.map(data){
        try {
            (it.vote_average*10).toInt()
        }catch (ex : NumberFormatException){
            0
        }
    }

    val movieOverview : LiveData<String> = Transformations.map(data){it.overview}

    val releaseDate : LiveData<String> = Transformations.map(data){TimeUtils.parseDateToFormat(it.releaseDate)}






}