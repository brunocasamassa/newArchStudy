package com.example.newarchstudy.data.interceptors

import com.example.newarchstudy.BuildConfig
import okhttp3.Interceptor
import okhttp3.Response
import java.io.IOException
import java.net.SocketTimeoutException


class NewsInterceptor : Interceptor {

    @Throws(SocketTimeoutException::class)
    override fun intercept(chain: Interceptor.Chain): Response {
        val request = chain.request()
        val builder = request.newBuilder()
            .addHeader("Authorization", BuildConfig.API_KEY)
        return chain.proceed(builder.build())
    }

}