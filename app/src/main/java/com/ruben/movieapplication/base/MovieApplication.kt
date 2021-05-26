package com.ruben.movieapplication.base

import android.app.Application
import com.ruben.movieapplication.injection.appModule
import com.ruben.movieapplication.injection.repositoryModule
import com.ruben.movieapplication.injection.useCaseModule
import com.ruben.movieapplication.injection.viewModelModule
import org.koin.core.context.startKoin

/**
 * Created by ruben.quadros on 26/05/21.
 **/
class MovieApplication: Application() {

    override fun onCreate() {
        super.onCreate()
        startKoin {
            modules(appModule, viewModelModule, useCaseModule, repositoryModule)
        }
    }
}