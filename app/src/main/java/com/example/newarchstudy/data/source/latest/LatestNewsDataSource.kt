package com.example.newarchstudy.data.source.latest

import com.example.newarchstudy.data.models.news.News
import kotlinx.coroutines.flow.Flow

interface LatestNewsDataSource {

    val latestNews: Flow<News>
}
