package com.ruben.movieapplication.injection

import com.ruben.movieapplication.presentation.search.ResultAdapter
import com.ruben.movieapplication.presentation.search.SearchActivity
import org.koin.dsl.module

/**
 * Created by ruben.quadros on 26/05/21.
 **/
val adapterModule = module {
    scope<SearchActivity> {
        scoped {
            ResultAdapter()
        }
    }
}