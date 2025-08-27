package com.uncannyvalley.mentora.di

import com.uncannyvalley.mentora.presentation.viewmodel.CourseViewModel
import org.koin.core.module.dsl.viewModel
import org.koin.dsl.module

val viewModelModule = module {
    viewModel { CourseViewModel(get(), get()) }
}