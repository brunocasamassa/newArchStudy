package com.example.newarchstudy.data.source.latest

import android.util.Log
import com.example.newarchstudy.data.apis.NewsApi
import com.example.newarchstudy.data.models.news.News
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class LatestNewsDataSourceImpl @Inject constructor(
    private val newsApi: NewsApi
) : LatestNewsDataSource {

    override val latestNews : Flow<News> =
        flow {
            while (true){
                val response = newsApi.getLatestNews()
                emit(response.await())
                delay(5000)
        }
        }.catch {
            Log.d(this.javaClass.name, it.localizedMessage)
            emit(News(status = it.localizedMessage))
        }

companion object {
    const val STATUS_OK = "ok"
}

}


