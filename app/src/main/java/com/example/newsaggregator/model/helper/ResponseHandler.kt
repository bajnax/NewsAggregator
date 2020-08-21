package com.example.newsaggregator.model.helper

import com.example.newsaggregator.model.wrappers.ResponseWrapper
import retrofit2.Response
import java.lang.Exception

abstract class ResponseHandler {

    protected suspend fun <T> getResponse(request: suspend() -> Response<T>): ResponseWrapper<T> {
        return try {
            val response = request()
            if (response.isSuccessful) {
                response.body()?.let {
                    ResponseWrapper.onSuccess(it)
                } ?: onError(response.errorBody()?.byteStream().toString())
            } else {
                onError(response.errorBody()?.byteStream().toString())
            }
        } catch (e: Exception) {
            onError(e.toString())
        }
    }

    private fun <T> onError(error: String): ResponseWrapper<T> = ResponseWrapper.onError(error)
}