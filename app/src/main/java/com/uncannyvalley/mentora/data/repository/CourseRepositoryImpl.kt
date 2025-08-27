package com.uncannyvalley.mentora.data.repository

import com.uncannyvalley.mentora.data.api.CourseApiService
import com.uncannyvalley.mentora.data.mapper.toDomain
import com.uncannyvalley.mentora.domain.model.Course
import com.uncannyvalley.mentora.domain.repository.CourseRepository

class CourseRepositoryImpl(
    private val api: CourseApiService
) : CourseRepository {

    override suspend fun getCourses(): List<Course> {
        val response = api.getCourses()
        // Map each DTO to domain model
        return response.courses.map { it.toDomain() }
    }
}