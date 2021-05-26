package com.ruben.data.repository

import com.ruben.data.DataSource
import com.ruben.data.mapper.SearchMapper
import com.ruben.domain.model.SearchResultRecord
import com.ruben.domain.repository.OmdbRepository
import com.ruben.remote.model.search.SearchRequest

/**
 * Created by ruben.quadros on 26/05/21.
 **/
class OmdbRepositoryImpl(private val dataSource: DataSource): OmdbRepository {

    private val mapper = SearchMapper()

    override suspend fun getSearchQuery(searchTerm: String, pageNo: Int): SearchResultRecord {
        return dataSource.api().restApi().getSearchResults(SearchRequest(searchTerm, 1)).run {
            mapper.mapSearchResult(this)
        }
    }

}