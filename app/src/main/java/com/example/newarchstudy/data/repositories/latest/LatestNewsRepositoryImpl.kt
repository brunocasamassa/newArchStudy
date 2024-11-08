package com.example.newarchstudy.data.repositories.latest

import com.example.newarchstudy.utils.Factory.latestNewsDataSource
import com.example.newarchstudy.data.models.news.News
import kotlinx.coroutines.flow.Flow

class LatestNewsRepositoryImpl : LatestNewsRepository {

    override val fetchNewsData : Flow<News> = latestNewsDataSource.latestNews

}
