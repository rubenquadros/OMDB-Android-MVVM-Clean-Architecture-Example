package com.ruben.domain.interactor

import com.ruben.domain.BaseUseCase
import com.ruben.domain.model.base.Record
import com.ruben.domain.model.details.DetailsRecord
import com.ruben.domain.repository.OmdbRepository

/**
 * Created by ruben.quadros on 26/05/21.
 **/
class GetDetailsUseCase(private val repository: OmdbRepository): BaseUseCase<GetDetailsUseCase.RequestValue, Record<DetailsRecord>>() {

    override suspend fun run(request: RequestValue): Record<DetailsRecord> {
        return repository.getDetails(request.id)
    }

    data class RequestValue(
        val id: String
    )
}