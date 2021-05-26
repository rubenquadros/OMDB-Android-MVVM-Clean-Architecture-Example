package com.ruben.domain.model.details

/**
 * Created by ruben.quadros on 26/05/21.
 **/
data class DetailsRecord(
    var title: String,
    var poster: String,
    var rating: String,
    var overview: String,
    var releaseDate: String,
    var language: String,
    var actors: String
) {
    constructor(): this("", "", "", "", "", "", "")
}