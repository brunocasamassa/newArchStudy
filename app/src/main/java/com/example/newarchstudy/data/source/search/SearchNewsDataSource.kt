package com.example.newarchstudy.data.source.search

import com.example.newarchstudy.data.models.news.News
import kotlinx.coroutines.flow.Flow

interface SearchNewsDataSource {

    fun searchNews(
        text: String?
    ): Flow<News>
}
