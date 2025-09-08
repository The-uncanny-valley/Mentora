package com.uncannyvalley.mentora.di

import com.uncannyvalley.mentora.data.Constants
import com.uncannyvalley.mentora.data.api.CourseApiService
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import org.koin.dsl.module
import kotlin.jvm.java

val networkModule = module {
    single {
        Retrofit.Builder()
            .baseUrl(Constants.BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(CourseApiService::class.java)
    }
}