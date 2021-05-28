package com.ruben.movieapplication.presentation.search

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.ruben.domain.interactor.SearchUseCase
import com.ruben.domain.model.base.Record
import com.ruben.domain.model.base.StatusRecord
import com.ruben.domain.model.search.SearchResultRecord
import com.ruben.movieapplication.util.AppConstants
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import java.util.regex.Pattern

/**
 * Created by ruben.quadros on 26/05/21.
 **/
class SearchViewModel(private val searchUseCase: SearchUseCase): ViewModel() {

    private var searchResult: MutableStateFlow<Record<SearchResultRecord>> = MutableStateFlow(
        Record(StatusRecord.LOADING, SearchResultRecord())
    )

    fun isValidQuery(input: String): Boolean {
        if (input.length > 2 && Pattern.matches(AppConstants.TEXT_PATTERN, input)) {
            return true
        }
        return false
    }


    fun getQueryResults(searchTerm: String, pageNo: Int) {
        searchUseCase.invoke(
            viewModelScope,
            SearchUseCase.RequestValue(searchTerm, pageNo)
        ) { handleSearchResponse(it) }
    }

    private fun handleSearchResponse(searchResultRecord: Record<SearchResultRecord>) {
        searchResult.value = searchResultRecord
    }

    fun getSearchResult(): StateFlow<Record<SearchResultRecord>> {
        return searchResult
    }
}