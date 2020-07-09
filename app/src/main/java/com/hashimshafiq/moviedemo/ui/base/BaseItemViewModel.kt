package com.hashimshafiq.moviedemo.ui.base

import androidx.lifecycle.MutableLiveData
import com.hashimshafiq.moviedemo.utils.network.NetworkHelper


abstract class BaseItemViewModel<T : Any>(
    networkHelper: NetworkHelper
) : BaseViewModel(networkHelper) {

    val data: MutableLiveData<T> = MutableLiveData()

    fun updateData(data: T) {
        this.data.postValue(data)
    }
}