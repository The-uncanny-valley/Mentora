package com.uncannyvalley.mentora.data.repository

import com.uncannyvalley.mentora.data.api.CourseApiService
import com.uncannyvalley.mentora.data.mapper.toDomain
import com.uncannyvalley.mentora.domain.model.Course
import com.uncannyvalley.mentora.domain.repository.CourseRepository

class CourseRepositoryImpl(
    private val api: CourseApiService
) : CourseRepository {

    // Keep track of liked course IDs
    private val likedCourseIds = mutableSetOf<Int>()

    override suspend fun getCourses(): List<Course> {
        val response = api.getCourses()
        return response.courses.map { dto ->
            val course = dto.toDomain()
            // Override hasLike if user has toggled it
            if (likedCourseIds.contains(course.id)) course.copy(hasLike = true)
            else course
        }
    }

    override fun toggleLike(courseId: Int) {
        if (likedCourseIds.contains(courseId)) likedCourseIds.remove(courseId)
        else likedCourseIds.add(courseId)
    }
}