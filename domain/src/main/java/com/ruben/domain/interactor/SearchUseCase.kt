package com.ruben.domain.interactor

import com.ruben.domain.BaseUseCase
import com.ruben.domain.model.base.Record
import com.ruben.domain.model.search.SearchResultRecord
import com.ruben.domain.repository.OmdbRepository

/**
 * Created by ruben.quadros on 26/05/21.
 **/
class SearchUseCase(private val repository: OmdbRepository): BaseUseCase<SearchUseCase.RequestValue, Record<SearchResultRecord>>() {

    data class RequestValue(
        val searchTerm: String,
        val pageNo: Int
    )

    override suspend fun run(request: RequestValue): Record<SearchResultRecord> {
        return repository.getSearchQuery(request.searchTerm, request.pageNo)
    }

}