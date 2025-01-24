package com.example.newarchstudy.data.apis

import com.example.newarchstudy.data.models.dto.ResponseNews
import retrofit2.http.GET
import retrofit2.http.Query

interface NewsApi {

    @GET("latest-news/")
    suspend fun getLatestNews(): ResponseNews

    @GET("search/")
    suspend fun getSearchNews(@Query(value = "description") description:String?= "",@Query(value = "title") title:String? = "", @Query(value = "author") author:String?="", @Query(value = "language") language:String?="",@Query(value = "region") region:String?=""): ResponseNews


}
