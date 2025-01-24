package com.example.newarchstudy.data.source.search

import com.example.newarchstudy.data.apis.NewsApi
import com.example.newarchstudy.data.models.dto.ResponseNews
import com.example.newarchstudy.data.models.entities.News
import com.example.newarchstudy.utils.extensions.toEntity
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.withTimeoutOrNull
import javax.inject.Inject

class SearchNewsDataSourceImpl @Inject constructor(
    private val newsApi: NewsApi
)  : SearchNewsDataSource {

    /**  a Job class is a representation of a coroutine itself, a way to handle it*/

    /** A deffered is a job with a result , like the result of a thread*/

    override fun searchNews(text: String?): Flow<News> = flow {

        val timeoutDuration = 10000L // 10 seconds timeout

        val descriptionDeferred =  withTimeoutOrNull(timeoutDuration){ newsApi.getSearchNews(description = text)}
        val authorDeferred = withTimeoutOrNull(timeoutDuration){ newsApi.getSearchNews(author = text)}
        val titleDeferred =  withTimeoutOrNull(timeoutDuration){  newsApi.getSearchNews(title = text)}

        val newsFilteredByDescription = descriptionDeferred?.news ?: listOf()
        val newsFilteredByAuthor = authorDeferred?.news ?: listOf()
        val newsFilteredByTitle = titleDeferred?.news ?: listOf()

        val combinedNews = (newsFilteredByTitle + newsFilteredByAuthor + newsFilteredByDescription)
            .distinctBy { it.id } // Assuming News has an 'id' property

        emit(ResponseNews(news = combinedNews, status = "ok").toEntity())
        delay(5000)

    }
}
