package com.ruben.movieapplication.injection

import com.ruben.data.repository.OmdbRepositoryImpl
import com.ruben.domain.repository.OmdbRepository
import org.koin.dsl.module

/**
 * Created by ruben.quadros on 26/05/21.
 **/
val repositoryModule = module {
    single<OmdbRepository> {
        return@single OmdbRepositoryImpl(get())
    }
}