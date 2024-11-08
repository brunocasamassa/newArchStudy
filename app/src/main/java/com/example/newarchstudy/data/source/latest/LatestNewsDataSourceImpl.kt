package com.example.newarchstudy.data.source.latest

import com.example.newarchstudy.data.NewsWebService
import com.example.newarchstudy.data.interceptors.NewsInterceptor
import com.example.newarchstudy.data.models.news.News
import com.example.newarchstudy.utils.extensions.buildRetrofit
import com.jakewharton.retrofit2.adapter.kotlin.coroutines.CoroutineCallAdapterFactory
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class LatestNewsDataSourceImpl : LatestNewsDataSource {

    private var newsWebService: NewsWebService = buildRetrofit("https://api.currentsapi.services/v1/").create(NewsWebService::class.java)

    override val latestNews : Flow<News> =
        flow {
            while (true){
                val response = newsWebService.getLatestNews()
                emit(response.await())
                delay(5000)
        }
        }

}
