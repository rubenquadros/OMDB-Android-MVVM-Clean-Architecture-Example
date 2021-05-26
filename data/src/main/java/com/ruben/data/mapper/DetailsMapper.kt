package com.ruben.data.mapper

import com.ruben.domain.model.details.DetailsRecord
import com.ruben.remote.model.detail.DetailsResponse

/**
 * Created by ruben.quadros on 26/05/21.
 **/
class DetailsMapper {

    fun mapDetailsResponse(detailsResponse: DetailsResponse): DetailsRecord {
        return DetailsRecord().apply {
            title = detailsResponse.title
            poster = detailsResponse.poster
            rating = detailsResponse.imdbRating
            overview = detailsResponse.plot
            releaseDate = detailsResponse.released
            actors = detailsResponse.actors
            language = detailsResponse.language
        }
    }
}