package com.ruben.movieapplication.presentation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.ruben.domain.interactor.SearchUseCase
import com.ruben.domain.model.SearchResultRecord
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow

/**
 * Created by ruben.quadros on 26/05/21.
 **/
class SearchViewModel(private val searchUseCase: SearchUseCase): ViewModel() {

    private var searchResult: MutableStateFlow<SearchResultRecord> = MutableStateFlow(
        SearchResultRecord()
    )

    fun getQueryResults(searchTerm: String, pageNo: Int) {
        searchUseCase.invoke(
            viewModelScope,
            SearchUseCase.RequestValue(searchTerm, pageNo)
        ) { handleSearchResponse(it) }
    }

    private fun handleSearchResponse(searchResultRecord: SearchResultRecord) {
        searchResult.value = searchResultRecord
    }

    fun getSearchResult(): StateFlow<SearchResultRecord> {
        return searchResult
    }
}