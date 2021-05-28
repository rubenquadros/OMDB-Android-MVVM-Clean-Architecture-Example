package com.ruben.data.mapper

import com.ruben.domain.model.base.Record
import com.ruben.domain.model.base.StatusRecord
import com.ruben.domain.model.search.SearchRecord
import com.ruben.domain.model.search.SearchResultRecord
import com.ruben.remote.model.search.SearchResponse
import com.ruben.remote.util.ApiConstants

/**
 * Created by ruben.quadros on 26/05/21.
 **/
class SearchMapper {

    fun mapSearchResult(searchResponse: SearchResponse): Record<SearchResultRecord> {
        val searchResults = arrayListOf<SearchRecord>()
        return if (searchResponse.response == ApiConstants.API_FAIL) {
            Record(StatusRecord.FAIL, SearchResultRecord())
        } else {
            for (i in searchResponse.search!!.indices) {
                searchResults.add(
                    SearchRecord(
                        searchResponse.search?.get(i)?.title ?: "",
                        searchResponse.search?.get(i)?.id ?: "",
                        searchResponse.search?.get(i)?.poster ?: "",
                        searchResponse.search?.get(i)?.year ?: ""
                    )
                )
            }
            Record(StatusRecord.SUCCESS, SearchResultRecord(searchResults))
        }
    }
}