package com.example.newarchstudy.data.source.latest

import android.util.Log
import com.example.newarchstudy.data.apis.NewsApi
import com.example.newarchstudy.data.models.dto.ResponseNews
import com.example.newarchstudy.data.models.entities.News
import com.example.newarchstudy.utils.extensions.toEntity
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

//ESTÁ CERTO  POIS SÓ CONTEM UM GET NESSA API, MAS O DATA SOURCE CUIDA DE -> CACHING , CRUD E transformar DTO em Entity
class LatestNewsDataSourceImpl @Inject constructor(
    private val newsApi: NewsApi
) : LatestNewsDataSource {

    override val latestNews : Flow<News> =
        flow {
            while (true){
                val response = newsApi.getLatestNews()
                emit(response.toEntity())
                delay(5000)
        }
        }.catch {
            Log.d(this.javaClass.name, it.localizedMessage ?: "null")
            emit(News(status = it.localizedMessage))
        }

companion object {
    const val STATUS_OK = "ok"
}

}


