package com.ruben.movieapplication.injection

import com.ruben.movieapplication.presentation.SearchViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

/**
 * Created by ruben.quadros on 26/05/21.
 **/
val viewModelModule = module {
    viewModel {
        SearchViewModel(get())
    }
}