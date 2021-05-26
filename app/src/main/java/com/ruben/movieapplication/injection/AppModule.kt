package com.ruben.movieapplication.injection

import com.ruben.data.DataSource
import com.ruben.data.DataSourceImpl
import org.koin.dsl.module

/**
 * Created by ruben.quadros on 26/05/21.
 **/
val appModule = module {
    single<DataSource> {
        return@single DataSourceImpl()
    }
}