package com.example.newarchstudy.data.repositories.search

import com.example.newarchstudy.data.models.dto.ResponseNews
import com.example.newarchstudy.data.models.entities.News
import kotlinx.coroutines.flow.Flow

interface SearchNewsRepository {

     fun searchNews(text:String) : Flow<News>

}
