package com.example.newsaggregator.model.remote

import com.example.newsaggregator.BuildConfig
import okhttp3.Interceptor
import okhttp3.Response

class AuthInterceptor : Interceptor {
    override fun intercept(chain: Interceptor.Chain): Response {
        var req = chain.request()
        val url = req.url.newBuilder().build()
        req = req.newBuilder().addHeader("X-Api-Key", BuildConfig.TOKEN).url(url).build()
        return chain.proceed(req)
    }
}