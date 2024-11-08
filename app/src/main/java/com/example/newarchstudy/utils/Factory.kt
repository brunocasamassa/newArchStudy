package com.example.newarchstudy.utils

import android.app.Application
import android.content.Context
import androidx.lifecycle.ViewModelProvider
import com.example.newarchstudy.data.repositories.latest.LatestNewsRepository
import com.example.newarchstudy.data.source.latest.LatestNewsDataSourceImpl
import com.example.newarchstudy.data.repositories.latest.LatestNewsRepositoryImpl
import com.example.newarchstudy.data.repositories.search.SearchNewsRepository
import com.example.newarchstudy.data.repositories.search.SearchNewsRepositoryImpl
import com.example.newarchstudy.data.source.latest.LatestNewsDataSource
import com.example.newarchstudy.data.source.search.SearchNewsDataSource
import com.example.newarchstudy.data.source.search.SearchNewsDataSourceImpl
import com.example.newarchstudy.viewmodels.LatestNewsViewModel
import com.example.newarchstudy.viewmodels.SearchNewsViewModel

object Factory
{

    lateinit var globalContext: Context
    lateinit var latestNewsDataSource: LatestNewsDataSource
    lateinit var latestNewsRepository: LatestNewsRepository
    lateinit var latestNewsViewModel: LatestNewsViewModel
    lateinit var searchNewsDataSource: SearchNewsDataSource
    lateinit var searchNewsRepository: SearchNewsRepository
    lateinit var searchNewsViewModel: SearchNewsViewModel

    fun setDataSource(mainApplication: Application) {
        globalContext = mainApplication.applicationContext
        latestNewsDataSource = LatestNewsDataSourceImpl()
        latestNewsRepository = LatestNewsRepositoryImpl()
        searchNewsDataSource = SearchNewsDataSourceImpl()
        searchNewsRepository = SearchNewsRepositoryImpl()
        latestNewsViewModel =
            ViewModelProvider.AndroidViewModelFactory.getInstance(mainApplication).create(
                LatestNewsViewModel::class.java
            )

        searchNewsViewModel =
            ViewModelProvider.AndroidViewModelFactory.getInstance(mainApplication).create(
                SearchNewsViewModel::class.java
            )
    }
}