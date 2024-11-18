package com.example.newarchstudy.data.services

import com.example.newarchstudy.data.models.news.News
import kotlinx.coroutines.Deferred
import retrofit2.http.GET
import retrofit2.http.Query

interface NewsWebService {

    @GET("latest-news/")
    fun getLatestNews(): Deferred<News>

    @GET("search/")
    fun getSearchNews(@Query(value = "description") description:String?= "",@Query(value = "title") title:String? = "", @Query(value = "author") author:String?="", @Query(value = "language") language:String?="",@Query(value = "region") region:String?=""): Deferred<News>


}
