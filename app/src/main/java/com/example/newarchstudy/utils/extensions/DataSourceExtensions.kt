package com.example.newarchstudy.utils.extensions

import com.example.newarchstudy.data.interceptors.NewsInterceptor
import com.jakewharton.retrofit2.adapter.kotlin.coroutines.CoroutineCallAdapterFactory
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

fun buildRetrofit(rootUrl: String): Retrofit {
    return  Retrofit.Builder()
        .baseUrl(rootUrl)
        .client(
            OkHttpClient.Builder().connectTimeout(15, TimeUnit.SECONDS)
                .readTimeout(15, TimeUnit.SECONDS)
                .writeTimeout(15, TimeUnit.SECONDS).addInterceptor(NewsInterceptor())
                .build()
        ).addConverterFactory(GsonConverterFactory.create())
        .addCallAdapterFactory(CoroutineCallAdapterFactory())
        .build()
}