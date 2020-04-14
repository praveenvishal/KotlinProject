package com.demo.kotlinproject.data.api.util

import com.demo.kotlinproject.R
import com.demo.kotlinproject.ApplicationController
import java.net.SocketTimeoutException


class ApiResponse<T>(val status: Status, val data: T?, val error: Throwable?) {

    var errorMessage: String? = null
    var errorType: Error? = null

    init {
        if (error == null && status == Status.ERROR) {
            errorType = Error.API_ERROR
        } else if (error is SocketTimeoutException) {
            errorType = Error.TIMEOUT_ERROR
            errorMessage = ApplicationController.context.getString(R.string.network_error)
        } else {
            errorType = Error.SERVER_ERROR
            errorMessage = ApplicationController.context.getString(R.string.internal_server_error)
        }
    }

    companion object {
        fun <T> loading(): ApiResponse<T> {
            return ApiResponse(
                Status.LOADING,
                null,
                null
            )
        }

        fun <T> success(data: T): ApiResponse<T> {
            return ApiResponse(
                Status.SUCCESS,
                data,
                null
            )
        }

        fun <T> error(error: Throwable?): ApiResponse<T> {
            return ApiResponse(
                Status.ERROR,
                null,
                error
            )
        }
    }

    enum class Status {
        LOADING,
        SUCCESS,
        ERROR,
    }

    enum class Error {
        SERVER_ERROR,
        TIMEOUT_ERROR,
        API_ERROR

    }
}