package com.uncannyvalley.mentora.domain.repository

import com.uncannyvalley.mentora.domain.model.Course

interface CourseRepository {
    suspend fun getCourses(): List<Course>
}