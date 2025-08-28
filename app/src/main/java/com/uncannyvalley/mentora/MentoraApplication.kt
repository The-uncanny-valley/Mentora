package com.uncannyvalley.mentora

import android.app.Application
import com.uncannyvalley.mentora.di.databaseModule
import com.uncannyvalley.mentora.di.networkModule
import com.uncannyvalley.mentora.di.repositoryModule
import com.uncannyvalley.mentora.di.useCaseModule
import com.uncannyvalley.mentora.di.viewModelModule
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.GlobalContext.startKoin

class MentoraApplication : Application() {
    override fun onCreate() {
        super.onCreate()

        // Start Koin
        startKoin {
            androidContext(this@MentoraApplication)
            modules(listOf(
                networkModule,
                databaseModule,
                repositoryModule,
                useCaseModule,
                viewModelModule)
            )
        }
    }
}