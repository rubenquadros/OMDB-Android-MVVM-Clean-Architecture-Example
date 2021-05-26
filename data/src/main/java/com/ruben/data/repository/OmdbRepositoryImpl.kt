package com.ruben.data.repository

import com.ruben.data.DataSource
import com.ruben.data.mapper.DetailsMapper
import com.ruben.data.mapper.SearchMapper
import com.ruben.domain.model.details.DetailsRecord
import com.ruben.domain.model.search.SearchResultRecord
import com.ruben.domain.repository.OmdbRepository
import com.ruben.remote.model.detail.DetailsRequest
import com.ruben.remote.model.search.SearchRequest

/**
 * Created by ruben.quadros on 26/05/21.
 **/
class OmdbRepositoryImpl(private val dataSource: DataSource): OmdbRepository {

    private val searchMapper = SearchMapper()
    private val detailsMapper = DetailsMapper()

    override suspend fun getSearchQuery(searchTerm: String, pageNo: Int): SearchResultRecord {
        return dataSource.api().restApi().getSearchResults(SearchRequest(searchTerm, 1)).run {
            searchMapper.mapSearchResult(this)
        }
    }

    override suspend fun getDetails(id: String): DetailsRecord {
        return dataSource.api().restApi().getDetails(DetailsRequest(id)).run {
            detailsMapper.mapDetailsResponse(this)
        }
    }

}