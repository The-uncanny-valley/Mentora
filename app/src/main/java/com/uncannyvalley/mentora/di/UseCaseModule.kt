package com.uncannyvalley.mentora.di

import com.uncannyvalley.mentora.domain.usecase.GetCoursesUseCase
import org.koin.dsl.module

val useCaseModule = module {
    factory { GetCoursesUseCase(get()) }
}