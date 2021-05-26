package com.ruben.remote.model.search

import com.google.gson.annotations.SerializedName

/**
 * Created by ruben.quadros on 26/05/21.
 **/
data class SearchResponse(
    @SerializedName("Search")
    val search: List<SearchResult>?,
    @SerializedName("totalResults")
    val totalResults: String?,
    @SerializedName("Response")
    val response: String
)