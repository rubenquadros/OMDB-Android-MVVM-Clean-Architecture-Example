package com.ruben.data.mapper

import com.ruben.domain.model.base.Record
import com.ruben.domain.model.base.StatusRecord
import com.ruben.domain.model.details.DetailsRecord
import com.ruben.remote.model.detail.DetailsResponse
import com.ruben.remote.util.ApiConstants

/**
 * Created by ruben.quadros on 26/05/21.
 **/
class DetailsMapper {

    fun mapDetailsResponse(detailsResponse: DetailsResponse): Record<DetailsRecord> {
        return if (detailsResponse.response == ApiConstants.API_FAIL) {
            Record(StatusRecord.FAIL, DetailsRecord())
        } else {
            Record(StatusRecord.SUCCESS,
                DetailsRecord().apply {
                title = detailsResponse.title
                poster = detailsResponse.poster
                rating = detailsResponse.imdbRating
                overview = detailsResponse.plot
                releaseDate = detailsResponse.released
                actors = detailsResponse.actors
                language = detailsResponse.language
            })
        }
    }
}