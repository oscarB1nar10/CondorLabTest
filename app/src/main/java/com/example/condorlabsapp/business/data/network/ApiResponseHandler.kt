package com.example.condorlabsapp.business.data.network

import com.example.condorlabsapp.business.domain.state.*
import com.example.condorlabsapp.util.NetworkConstants.NETWORK_DATA_NULL
import com.example.condorlabsapp.util.NetworkConstants.NETWORK_ERROR


abstract class ApiResponseHandler<ViewState, Data>(
    private val response: ApiResult<Data?>,
    private val stateEvent: StateEvent?
) {

    suspend fun getResult(): State<ViewState> {

        return when (response) {

            is ApiResult.GenericError -> {
                State.failed(
                    message = "${stateEvent?.errorInfo()}\n\nReason: ${response.errorMessage.toString()}"
                )
            }

            is ApiResult.NetworkError -> {
                State.failed(
                    message = "${stateEvent?.errorInfo()}\n\nReason: ${NETWORK_ERROR}"
                )
            }

            is ApiResult.Success -> {
                if (response.value == null) {
                    State.failed(
                        message = "${stateEvent?.errorInfo()}\n\nReason: ${NETWORK_DATA_NULL}.",
                    )
                } else {
                    handleSuccess(resultObj = response.value)
                }
            }

        }
    }

    abstract suspend fun handleSuccess(resultObj: Data): State<ViewState>

}