package com.uncannyvalley.mentora.data.api

import com.uncannyvalley.mentora.data.Constants
import retrofit2.http.GET

interface CourseApiService {

    @GET(Constants.COURSES_ENDPOINT)
    suspend fun getCourses(): CourseResponse
}