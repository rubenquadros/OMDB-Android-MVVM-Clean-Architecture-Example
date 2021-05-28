package com.ruben.domain.repository

import com.ruben.domain.model.base.Record
import com.ruben.domain.model.details.DetailsRecord
import com.ruben.domain.model.search.SearchResultRecord

/**
 * Created by ruben.quadros on 26/05/21.
 **/
interface OmdbRepository {
    suspend fun getSearchQuery(searchTerm: String, pageNo: Int): Record<SearchResultRecord>
    suspend fun getDetails(id: String): Record<DetailsRecord>
}