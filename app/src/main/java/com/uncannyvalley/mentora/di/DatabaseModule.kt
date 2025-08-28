package com.uncannyvalley.mentora.di

import androidx.room.Room
import com.uncannyvalley.mentora.data.db.CourseDatabase
import org.koin.android.ext.koin.androidContext
import org.koin.dsl.module

val databaseModule = module {
    single {
        Room.databaseBuilder(
            androidContext(),
            CourseDatabase::class.java,
            "course_db"
        ).build()
    }
    single { get<CourseDatabase>().courseDao() }
}