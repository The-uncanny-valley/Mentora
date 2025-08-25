package com.uncannyvalley.mentora.data.db

import androidx.room.Entity
import androidx.room.PrimaryKey
import java.util.Date

@Entity(tableName = "course_table")
data class CourseEntity(
    @PrimaryKey val id: Int,
    val title: String,
    val text: String,
    val price: Double,
    val rate: Double,
    val startDate: Date,
    val hasLike: Boolean,
    val publishDate: Date
)