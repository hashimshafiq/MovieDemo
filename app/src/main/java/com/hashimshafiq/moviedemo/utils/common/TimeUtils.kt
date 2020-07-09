package com.hashimshafiq.moviedemo.utils.common

import java.lang.Exception
import java.text.SimpleDateFormat
import java.util.*

object TimeUtils {

    fun parseDateToFormat(date: String) : String?{

        val format = SimpleDateFormat("yyyy-MM-dd", Locale.getDefault())
        val outputFormat = SimpleDateFormat("dd MMM yyyy", Locale.getDefault())
        return try {
            val outputDate = format.parse(date)
            outputDate?.run {
                outputFormat.format(this)
            }
        }catch (ex : Exception){
            null
        }

    }
}