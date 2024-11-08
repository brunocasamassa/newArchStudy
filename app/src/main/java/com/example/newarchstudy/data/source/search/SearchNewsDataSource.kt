package com.example.newarchstudy.data.source.search

import com.example.newarchstudy.data.models.news.News
import kotlinx.coroutines.flow.Flow

interface SearchNewsDataSource {

    fun searchNews(
        description: String?,
        title: String?,
        author: String?,
        language: String?,
        region: String?
    ): Flow<News>
}
