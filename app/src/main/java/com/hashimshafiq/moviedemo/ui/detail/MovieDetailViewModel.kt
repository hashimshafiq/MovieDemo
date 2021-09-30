package com.hashimshafiq.moviedemo.ui.detail

import com.hashimshafiq.moviedemo.ui.base.BaseViewModel
import com.hashimshafiq.moviedemo.utils.network.NetworkHelper
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class MovieDetailViewModel @Inject constructor(networkHelper: NetworkHelper) : BaseViewModel(networkHelper)  {


    override fun onCreate() {

    }
}