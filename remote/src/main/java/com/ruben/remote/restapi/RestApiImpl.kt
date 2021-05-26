package com.ruben.remote.restapi

import com.ruben.remote.model.search.SearchRequest
import com.ruben.remote.model.search.SearchResponse
import com.ruben.remote.retrofit.RetrofitApi

/**
 * Created by ruben.quadros on 26/05/21.
 **/
class RestApiImpl(private val retrofitApi: RetrofitApi): RestApi {
    override suspend fun getSearchResults(searchRequest: SearchRequest): SearchResponse {
       return retrofitApi.searchQuery(searchRequest.searchTerm, searchRequest.pageNo)
    }
}