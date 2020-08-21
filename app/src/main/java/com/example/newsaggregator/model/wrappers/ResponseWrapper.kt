package com.example.newsaggregator.model.wrappers

data class ResponseWrapper<out T>(val status: Status, val body: T?, val message: String?) {
    companion object {
        fun <T> onSuccess(body: T? = null): ResponseWrapper<T> =
            ResponseWrapper(
                Status.SUCCESS,
                body,
                null
            )

        fun <T> onError(message: String? = null): ResponseWrapper<T> =
            ResponseWrapper(
                Status.ERROR,
                null,
                message
            )

        fun <T> onLoading(body: T? = null): ResponseWrapper<T> =
            ResponseWrapper(
                Status.LOADING,
                body,
                null
            )
    }
}