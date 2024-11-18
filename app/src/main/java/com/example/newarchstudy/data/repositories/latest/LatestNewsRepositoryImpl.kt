package com.example.newarchstudy.data.repositories.latest

import com.example.newarchstudy.data.models.news.News
import com.example.newarchstudy.data.source.latest.LatestNewsDataSource
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject


class LatestNewsRepositoryImpl @Inject constructor(
     latestNewsDataSource: LatestNewsDataSource
)  : LatestNewsRepository {

    override val fetchNewsData : Flow<News> = latestNewsDataSource.latestNews

}

