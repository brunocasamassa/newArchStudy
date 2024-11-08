package com.example.newarchstudy.data.source.search

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

class SearchNewsDataSourceImpl : SearchNewsDataSource {

    private var newsWebService: NewsWebService = buildRetrofit("https://api.currentsapi.services/v1/").create(NewsWebService::class.java)

    override fun searchNews(description : String?, title : String?, author : String?, language : String?, region: String?) : Flow<News> {
        return flow {
            val response = newsWebService.getSearchNews(description, title, author, language, region)
            emit(response.await())
            delay(5000)

        }
    }



}
