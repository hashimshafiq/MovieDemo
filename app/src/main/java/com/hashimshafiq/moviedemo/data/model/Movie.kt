package com.hashimshafiq.moviedemo.data.model

import android.os.Parcelable
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName
import kotlinx.android.parcel.Parcelize
import java.util.*

@Parcelize
@Entity(tableName = "movies")
data class Movie(
        @Expose
        @SerializedName("id")
        @PrimaryKey
        val id: String,

        @Expose
        @SerializedName("vote_average")
        val vote_average: Double,

        @Expose
        @SerializedName("poster_path")
        val poster_path: String?,

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



