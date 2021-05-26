package com.ruben.remote.model.detail

import com.google.gson.annotations.SerializedName

/**
 * Created by ruben.quadros on 26/05/21.
 **/
data class Ratings(
    @SerializedName("Source") val source : String,
    @SerializedName("Value") val value : String
)