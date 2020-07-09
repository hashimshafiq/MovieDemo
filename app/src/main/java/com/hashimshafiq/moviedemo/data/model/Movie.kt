package com.hashimshafiq.moviedemo.data.model

import android.os.Parcelable
import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName
import kotlinx.android.parcel.Parcelize
import java.util.*

@Parcelize
data class Movie(
        @Expose
        @SerializedName("id")
        val id: String,

        @Expose
        @SerializedName("vote_average")
        val vote_average: Double,

        @Expose
        @SerializedName("poster_path")
        val poster_path: String?,

        @Expose
        @SerializedName("original_language")
        val originalLanguage: String,

        @Expose
        @SerializedName("original_title")
        val originalTitle: String,

        @Expose
        @SerializedName("title")
        val title: String,

        @Expose
        @SerializedName("overview")
        val overview: String,

        @Expose
        @SerializedName("release_date")
        val releaseDate: String
) : Parcelable {}



