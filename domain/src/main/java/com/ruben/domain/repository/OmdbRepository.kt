package com.ruben.domain.repository

import com.ruben.domain.model.SearchResultRecord

/**
 * Created by ruben.quadros on 26/05/21.
 **/
interface OmdbRepository {
    suspend fun getSearchQuery(searchTerm: String, pageNo: Int): SearchResultRecord
}