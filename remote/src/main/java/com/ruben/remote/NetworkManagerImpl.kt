package com.ruben.remote

import com.google.gson.Gson
import com.google.gson.GsonBuilder
import com.ruben.remote.restapi.RestApi
import com.ruben.remote.restapi.RestApiImpl
import com.ruben.remote.retrofit.RetrofitApi
import com.ruben.remote.util.ApiConstants
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

/**
 * Created by ruben.quadros on 26/05/21.
 **/
class NetworkManagerImpl: NetworkManager {

    private val restApi: RestApi = RestApiImpl(getRetrofitApi())

    override fun restApi(): RestApi {
        return restApi
    }

    private fun getRetrofitApi(): RetrofitApi {
        return Retrofit.Builder().baseUrl(ApiConstants.BASE_URL)
            .addConverterFactory(GsonConverterFactory.create(getGson()))
            .client(getOkHttpClient()).build()
            .create(RetrofitApi::class.java)
    }

    private fun getGson(): Gson {
        val gsonBuilder = GsonBuilder()
        return gsonBuilder.create()
    }

    private fun getOkHttpClient(): OkHttpClient {
        val okHttpClient = OkHttpClient.Builder()
        val loggingInterceptor = HttpLoggingInterceptor()
        loggingInterceptor.level = HttpLoggingInterceptor.Level.BODY
        okHttpClient.readTimeout(ApiConstants.TIMEOUT, TimeUnit.SECONDS)
        okHttpClient.writeTimeout(ApiConstants.TIMEOUT, TimeUnit.SECONDS)
        okHttpClient.connectTimeout(ApiConstants.TIMEOUT, TimeUnit.SECONDS)
        okHttpClient.addInterceptor(loggingInterceptor)
        return okHttpClient.build()
    }
}