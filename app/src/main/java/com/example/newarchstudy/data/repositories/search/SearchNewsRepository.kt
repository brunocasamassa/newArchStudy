package com.example.newarchstudy.data.repositories.search

import com.example.newarchstudy.data.models.news.News
import kotlinx.coroutines.flow.Flow

interface SearchNewsRepository {

     fun searchNews(description : String?, title : String?, author : String?, language : String?, region: String?) : Flow<News>

}
