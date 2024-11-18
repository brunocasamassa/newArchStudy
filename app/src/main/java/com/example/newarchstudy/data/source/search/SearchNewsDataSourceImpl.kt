package com.example.newarchstudy.data.source.search

import com.example.newarchstudy.data.services.NewsWebService
import com.example.newarchstudy.data.models.news.News
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.withTimeoutOrNull
import javax.inject.Inject

class SearchNewsDataSourceImpl @Inject constructor(
    private val newsWebService: NewsWebService
)  : SearchNewsDataSource {

    /**  a Job class is a representation of a coroutine itself, a way to handle it*/

    /** A deffered is a job with a result , like the result of a thread*/

    override fun searchNews(text: String?): Flow<News> = flow {

        val timeoutDuration = 10000L // 10 seconds timeout

        val descriptionDeferred =  withTimeoutOrNull(timeoutDuration){ newsWebService.getSearchNews(description = text).await()}
        val authorDeferred = withTimeoutOrNull(timeoutDuration){ newsWebService.getSearchNews(author = text).await()}
        val titleDeferred =  withTimeoutOrNull(timeoutDuration){  newsWebService.getSearchNews(title = text).await()}

        val newsFilteredByDescription = descriptionDeferred?.news ?: listOf()
        val newsFilteredByAuthor = authorDeferred?.news ?: listOf()
        val newsFilteredByTitle = titleDeferred?.news ?: listOf()

        val combinedNews = (newsFilteredByTitle + newsFilteredByAuthor + newsFilteredByDescription)
            .distinctBy { it.id } // Assuming News has an 'id' property

        emit(News(combinedNews, status = titleDeferred?.status))
        delay(5000)

    }
}
