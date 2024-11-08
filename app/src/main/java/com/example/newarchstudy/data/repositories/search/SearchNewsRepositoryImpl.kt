package com.example.newarchstudy.data.repositories.search

import com.example.newarchstudy.utils.Factory.latestNewsDataSource
import com.example.newarchstudy.data.models.news.News
import com.example.newarchstudy.utils.Factory.searchNewsDataSource
import kotlinx.coroutines.flow.Flow

class SearchNewsRepositoryImpl : SearchNewsRepository {


    override fun searchNews(description : String? , title : String?, author : String?, language : String?, region: String?): Flow<News> {

        return searchNewsDataSource.searchNews(description?:null, title ?:null, author?:null, language?:null, region?:null)

    }


}
