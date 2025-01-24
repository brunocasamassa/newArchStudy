package com.example.newarchstudy.data.source.latest

import com.example.newarchstudy.data.models.dto.ResponseNews
import com.example.newarchstudy.data.models.entities.News
import kotlinx.coroutines.flow.Flow

interface LatestNewsDataSource {

    val latestNews: Flow<News>
}
