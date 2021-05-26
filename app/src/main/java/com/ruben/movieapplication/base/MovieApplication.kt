package com.ruben.movieapplication.base

import android.app.Application
import com.ruben.movieapplication.injection.*
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin

/**
 * Created by ruben.quadros on 26/05/21.
 **/
class MovieApplication: Application() {

    override fun onCreate() {
        super.onCreate()
        startKoin {
            androidContext(this@MovieApplication)
            modules(listOf(appModule, viewModelModule, useCaseModule, repositoryModule, adapterModule))
        }
    }
}