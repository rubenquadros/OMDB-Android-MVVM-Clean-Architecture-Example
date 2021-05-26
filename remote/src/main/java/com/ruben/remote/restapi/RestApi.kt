package com.ruben.remote.restapi

import com.ruben.remote.model.search.SearchRequest
import com.ruben.remote.model.search.SearchResponse

/**
 * Created by ruben.quadros on 26/05/21.
 **/
interface RestApi {
    suspend fun getSearchResults(searchRequest: SearchRequest): SearchResponse
}