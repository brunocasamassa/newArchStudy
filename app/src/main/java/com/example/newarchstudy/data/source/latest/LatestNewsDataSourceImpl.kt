package com.example.newarchstudy.data.source.latest

import com.example.newarchstudy.data.services.NewsWebService
import com.example.newarchstudy.data.models.news.News
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class LatestNewsDataSourceImpl @Inject constructor(
    private val newsWebService: NewsWebService
) : LatestNewsDataSource {

    override val latestNews : Flow<News> =
        flow {
            while (true){
                val response = newsWebService.getLatestNews()
                emit(response.await())
                delay(5000)
        }
        }

}
