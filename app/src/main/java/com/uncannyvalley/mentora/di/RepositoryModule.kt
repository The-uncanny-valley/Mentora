package com.uncannyvalley.mentora.di

import com.uncannyvalley.mentora.data.repository.CourseRepositoryImpl
import com.uncannyvalley.mentora.domain.repository.CourseRepository
import org.koin.dsl.module

val repositoryModule = module {
    single<CourseRepository> { CourseRepositoryImpl(get(), get()) } // api, dao
}