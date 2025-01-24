package com.example.newarchstudy.utils.extensions

import com.example.newarchstudy.data.interceptors.NewsInterceptor
import com.example.newarchstudy.data.models.dto.ResponseNews
import com.example.newarchstudy.data.models.entities.New
import com.example.newarchstudy.data.models.entities.News
import com.jakewharton.retrofit2.adapter.kotlin.coroutines.CoroutineCallAdapterFactory
import okhttp3.HttpUrl
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

fun buildRetrofit(rootUrl: HttpUrl): Retrofit {
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

fun ResponseNews.toEntity() : News {

    var entityNews = News(news = arrayListOf(), status = this.status)

    this.news?.forEach {
        entityNews.news?.add(New(it.author, it.category, it.description, it.id, it.language, it.title, it.url))
    }

    return entityNews

}