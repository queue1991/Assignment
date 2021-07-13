package com.hsj.assignment.util

import com.google.firebase.Timestamp
import java.text.SimpleDateFormat
import java.util.*

object DateUtil {
    fun convertTimestampToDate(seconds: Long) : String{
        return SimpleDateFormat("yyyy-MM-dd hh:mm").format(Date(seconds*1000))
    }
}