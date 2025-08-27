package com.uncannyvalley.mentora.domain.usecase

import com.uncannyvalley.mentora.domain.model.Course
import com.uncannyvalley.mentora.domain.repository.CourseRepository

class GetCoursesUseCase(private val repository: CourseRepository) {
    suspend operator fun invoke(): List<Course> {
        return repository.getCourses()
    }
}