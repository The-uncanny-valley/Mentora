package com.uncannyvalley.mentora.data.db

import androidx.room.Database
import androidx.room.TypeConverters

@Database(
    entities = [CourseEntity::class],
    version = 1
)
@TypeConverters(Converters::class)
abstract class CourseDatabase {
    abstract fun CourseDao(): CourseDao
}