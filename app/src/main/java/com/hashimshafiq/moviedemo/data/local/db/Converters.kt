package com.hashimshafiq.moviedemo.data.local.db

import androidx.room.TypeConverter
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken

object Converters {
    @TypeConverter
    fun fromString(value: String): List<Int> {
        val type = object : TypeToken<IntArray>() {}.type
        return Gson().fromJson(value, type)
    }

    @TypeConverter
    fun fromArrayList(list: List<Int?>): String {
        val gson = Gson()
        return gson.toJson(list)
    }


}