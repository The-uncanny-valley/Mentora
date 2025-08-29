package com.uncannyvalley.mentora.data.repository

import com.uncannyvalley.mentora.data.api.CourseApiService
import com.uncannyvalley.mentora.data.db.CourseDao
import com.uncannyvalley.mentora.data.mapper.toDomain
import com.uncannyvalley.mentora.data.mapper.toEntity
import com.uncannyvalley.mentora.domain.model.Course
import com.uncannyvalley.mentora.domain.repository.CourseRepository
import kotlinx.coroutines.flow.first
import java.lang.Exception

class CourseRepositoryImpl(
    private val api: CourseApiService,
    private val dao: CourseDao
) : CourseRepository {

    // Keep track of liked course IDs
    private val likedCourseIds = mutableSetOf<Int>()

    override suspend fun getCourses(): List<Course> {
        return try {
            // Try remote
            val remote = api.getCourses().courses
            val entities = remote.map { it.toEntity() } // cache
            dao.insertAll(entities)

            dao.getCoursesByPublishDateDesc().first().map { it.toDomain() }
                .map { course ->
                    if (likedCourseIds.contains(course.id)) course.copy(hasLike = true) else course
                }
        } catch (e: Exception) {
            // If offline
            dao.getCoursesByPublishDateDesc().first().map { it.toDomain() }
                .map { course ->
                    if (likedCourseIds.contains(course.id)) course.copy(hasLike = true) else course
                }
        }
    }

    override fun toggleLike(courseId: Int) {
        if (likedCourseIds.contains(courseId)) likedCourseIds.remove(courseId)
        else likedCourseIds.add(courseId)
    }
}