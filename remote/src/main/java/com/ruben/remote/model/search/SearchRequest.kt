package com.ruben.remote.model.search

/**
 * Created by ruben.quadros on 26/05/21.
 **/
data class SearchRequest(
    val searchTerm: String,
    val pageNo: Int
)