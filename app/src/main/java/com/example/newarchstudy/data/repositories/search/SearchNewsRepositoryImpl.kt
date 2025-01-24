package com.example.newarchstudy.data.repositories.search

import com.example.newarchstudy.data.models.dto.ResponseNews
import com.example.newarchstudy.data.models.entities.News
import com.example.newarchstudy.data.source.search.SearchNewsDataSource
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class SearchNewsRepositoryImpl @Inject constructor(
    val searchNewsDataSource: SearchNewsDataSource
)  : SearchNewsRepository {


    override fun searchNews(text:String): Flow<News> {
        return searchNewsDataSource.searchNews(text)

    }


}
