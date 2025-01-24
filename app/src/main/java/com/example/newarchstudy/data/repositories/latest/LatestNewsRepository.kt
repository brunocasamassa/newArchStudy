package com.example.newarchstudy.data.repositories.latest

import com.example.newarchstudy.data.models.dto.ResponseNews
import com.example.newarchstudy.data.models.entities.News
import kotlinx.coroutines.flow.Flow


interface LatestNewsRepository {

    val fetchNewsData: Flow<News>
}
