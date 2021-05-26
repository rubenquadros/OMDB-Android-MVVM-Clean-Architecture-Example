package com.ruben.movieapplication.injection

import com.ruben.domain.interactor.GetDetailsUseCase
import com.ruben.domain.interactor.SearchUseCase
import org.koin.dsl.module

/**
 * Created by ruben.quadros on 26/05/21.
 **/
val useCaseModule = module {
    single {
        return@single SearchUseCase(get())
    }
    single {
        return@single GetDetailsUseCase(get())
    }
}