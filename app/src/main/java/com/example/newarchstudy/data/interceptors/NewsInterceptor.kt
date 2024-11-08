package com.example.newarchstudy.data.interceptors

import okhttp3.Interceptor
import okhttp3.Response
import java.io.IOException


class NewsInterceptor : Interceptor {

    @Throws(IOException::class)
    override fun intercept(chain: Interceptor.Chain): Response {
        val request = chain.request()
        val builder = request.newBuilder()
            .addHeader("Authorization", "fnP9i4cdX3j2cCYhLA_SmsKTss04PDcBBlBLMVL3p9gDjrxN")
        return chain.proceed(builder.build())
    }

}