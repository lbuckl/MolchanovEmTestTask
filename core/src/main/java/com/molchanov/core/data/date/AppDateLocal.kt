package com.molchanov.core.data.date

import java.text.SimpleDateFormat
import java.util.*
import javax.inject.Inject

class AppDateLocal @Inject constructor(){

    private val dateFormat = SimpleDateFormat("d MMM yyyy")
    
    fun getDate(): String {
        val date = dateFormat.format(Date())
        return date.toString()
    }
}