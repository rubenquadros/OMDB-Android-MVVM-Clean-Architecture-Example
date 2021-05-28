package com.ruben.remote.retrofit

import com.ruben.remote.model.detail.DetailsResponse
import com.ruben.remote.model.search.SearchResponse
import com.ruben.remote.util.ApiConstants
import retrofit2.http.GET
import retrofit2.http.Query
import retrofit2.http.Url

/**
 * Created by ruben.quadros on 26/05/21.
 **/
interface RetrofitApi {

    @GET
    suspend fun searchQuery(
        @Url url: String = ApiConstants.BASE_URL,
        @Query("s") searchTerm: String,
        @Query("page") pageNo: Int
    ): SearchResponse

    @GET("?apikey=17b7d079")
    suspend fun getDetails(
        @Query("i") id: String
    ): DetailsResponse
}