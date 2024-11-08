package com.example.newarchstudy.data.repositories.latest

import com.example.newarchstudy.data.models.news.News
import kotlinx.coroutines.flow.Flow

interface LatestNewsRepository {

    val fetchNewsData: Flow<News>
}
