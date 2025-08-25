package com.uncannyvalley.mentora.data.db

import androidx.room.TypeConverter
import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale

class Converters {

    @TypeConverter
    fun fromString(value: String?): Date? {
        return value?.let {
            try {
                SimpleDateFormat("yyyy-MM-dd", Locale.ROOT).parse(it)
            } catch (e: Exception) {
                null
            }
        }
    }

    @TypeConverter
    fun dateToString(date: Date?): String? {
        return date?.let {
            SimpleDateFormat("yyyy-MM-dd", Locale.ROOT).format(it)
        }
    }
}