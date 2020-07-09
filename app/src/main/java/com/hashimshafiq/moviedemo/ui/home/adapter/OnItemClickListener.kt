package com.hashimshafiq.moviedemo.ui.home.adapter

import android.widget.ImageView
import com.hashimshafiq.moviedemo.data.model.Movie

interface OnItemClickListener {
    fun onItemClicked(movie: Movie, imageView: ImageView)
}