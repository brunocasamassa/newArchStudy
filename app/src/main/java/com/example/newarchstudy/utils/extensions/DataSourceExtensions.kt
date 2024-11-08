package com.example.newarchstudy.utils.extensions

import com.example.newarchstudy.data.interceptors.NewsInterceptor
import com.jakewharton.retrofit2.adapter.kotlin.coroutines.CoroutineCallAdapterFactory
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

fun buildRetrofit(rootUrl: String): Retrofit {
    return  Retrofit.Builder()
        .baseUrl(rootUrl)
        .client(
            OkHttpClient.Builder().addInterceptor(NewsInterceptor())
                .build()
        ).addConverterFactory(GsonConverterFactory.create())
        .addCallAdapterFactory(CoroutineCallAdapterFactory())
        .build()
}