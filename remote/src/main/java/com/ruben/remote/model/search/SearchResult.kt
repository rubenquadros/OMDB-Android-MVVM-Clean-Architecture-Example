package com.ruben.remote.model.search

import com.google.gson.annotations.SerializedName

/**
 * Created by ruben.quadros on 26/05/21.
 **/
data class SearchResult(
    @SerializedName("Title")
    val title : String,
    @SerializedName("Year")
    val year : String,
    @SerializedName("imdbID")
    val id : String,
    @SerializedName("Type")
    val type : String,
    @SerializedName("Poster")
    val poster : String
)