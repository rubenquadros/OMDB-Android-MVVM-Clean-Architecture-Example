package com.ruben.domain.model.search

/**
 * Created by ruben.quadros on 26/05/21.
 **/
data class SearchResultRecord(
    val searchResults: List<SearchRecord>
) {
    constructor(): this(arrayListOf())
}