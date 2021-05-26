package com.ruben.remote.retrofit

import com.ruben.remote.model.detail.DetailResponse
import com.ruben.remote.model.search.SearchResponse
import retrofit2.http.GET
import retrofit2.http.Query

/**
 * Created by ruben.quadros on 26/05/21.
 **/
interface RetrofitApi {

    @GET("?apikey=17b7d079")
    suspend fun searchQuery(
        @Query("s") searchTerm: String,
        @Query("page") pageNo: Int
    ): SearchResponse

    @GET("?apikey=17b7d079")
    suspend fun getDetails(
        @Query("i") id: Int
    ): DetailResponse
}