package com.ruben.data.mapper

import com.ruben.domain.model.SearchRecord
import com.ruben.domain.model.SearchResultRecord
import com.ruben.remote.model.search.SearchResponse

/**
 * Created by ruben.quadros on 26/05/21.
 **/
class SearchMapper {

    fun mapSearchResult(searchResponse: SearchResponse): SearchResultRecord {
        val searchResults = arrayListOf<SearchRecord>()
        if (searchResponse.search.isNotEmpty()) {
            for (i in searchResponse.search.indices) {
                searchResults.add(
                    SearchRecord(
                        searchResponse.search[i].title,
                        searchResponse.search[i].id,
                        searchResponse.search[i].poster
                    )
                )
            }
        }
        return SearchResultRecord(searchResults)
    }
}