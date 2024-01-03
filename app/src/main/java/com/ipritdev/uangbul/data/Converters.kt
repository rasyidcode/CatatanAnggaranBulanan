package com.ipritdev.uangbul.data

import androidx.room.TypeConverter
import java.util.Calendar

class Converters {
    @TypeConverter
    fun calendarToTimeMillis(calendar: Calendar): Long = calendar.timeInMillis

    @TypeConverter
    fun timeMillisToCalendar(time: Long): Calendar =
        Calendar.getInstance().apply { timeInMillis = time }

}