package com.hashimshafiq.moviedemo.data.remote.responses

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName
import com.hashimshafiq.moviedemo.data.model.Movie

data class MovieListResponse (
        @SerializedName("page")
        @Expose
        val page:Int,

        @SerializedName("total_results")
        @Expose
        val totalResults: Int,

        @SerializedName("total_pages")
        @Expose
        val totalPages: Int,

        @SerializedName("results")
        @Expose
        val movies: List<Movie>?
)

