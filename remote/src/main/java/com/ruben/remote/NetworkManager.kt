package com.ruben.remote

import com.ruben.remote.restapi.RestApi

/**
 * Created by ruben.quadros on 26/05/21.
 **/
interface NetworkManager {
    fun restApi(): RestApi
}