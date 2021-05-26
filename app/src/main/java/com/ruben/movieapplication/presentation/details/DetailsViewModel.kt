package com.ruben.movieapplication.presentation.details

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.ruben.domain.interactor.GetDetailsUseCase
import com.ruben.domain.model.details.DetailsRecord
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow

/**
 * Created by ruben.quadros on 26/05/21.
 **/
class DetailsViewModel(private val detailsUseCase: GetDetailsUseCase): ViewModel() {

    private var detailsResult: MutableStateFlow<DetailsRecord> = MutableStateFlow(DetailsRecord())

    fun getDetails(id: String) {
        detailsUseCase.invoke(
            viewModelScope,
            GetDetailsUseCase.RequestValue(id)
        ) { handleDetailsResponse(it) }
    }

    private fun handleDetailsResponse(detailsRecord: DetailsRecord) {
        detailsResult.value = detailsRecord
    }

    fun getDetailsResult(): StateFlow<DetailsRecord> {
        return detailsResult
    }
}