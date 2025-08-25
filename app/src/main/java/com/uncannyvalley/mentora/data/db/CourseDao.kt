package com.uncannyvalley.mentora.data.db

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import kotlinx.coroutines.flow.Flow


@Dao
interface CourseDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(course: CourseEntity)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertAll(courses: List<CourseEntity>)

    // Search courses by title
    @Query("SELECT * FROM course_table WHERE title LIKE '%' || :query || '%'")
    fun searchCoursesByTitle(query: String): Flow<List<CourseEntity>>

    // Get all courses ordered by publishDate descending
    @Query("SELECT * FROM course_table ORDER BY publishDate DESC")
    fun getCoursesByPublishDateDesc(): Flow<List<CourseEntity>>
}