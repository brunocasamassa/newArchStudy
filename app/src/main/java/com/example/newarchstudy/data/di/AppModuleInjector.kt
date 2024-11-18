package com.example.newarchstudy.data.di

import com.example.newarchstudy.BuildConfig
import com.example.newarchstudy.data.services.NewsWebService
import com.example.newarchstudy.data.repositories.latest.LatestNewsRepository
import com.example.newarchstudy.data.repositories.latest.LatestNewsRepositoryImpl
import com.example.newarchstudy.data.repositories.search.SearchNewsRepository
import com.example.newarchstudy.data.repositories.search.SearchNewsRepositoryImpl
import com.example.newarchstudy.data.source.latest.LatestNewsDataSource
import com.example.newarchstudy.data.source.latest.LatestNewsDataSourceImpl
import com.example.newarchstudy.data.source.search.SearchNewsDataSource
import com.example.newarchstudy.data.source.search.SearchNewsDataSourceImpl
import com.example.newarchstudy.utils.extensions.buildRetrofit
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
object AppModuleInjector {

    @Provides
    fun providesNewsWebService(
    ): NewsWebService {
        return buildRetrofit(BuildConfig.BASE_URL).create(
            NewsWebService::class.java
        )
    }


    @Provides
    fun providesLatestNewsDataSource(
        dataSource: LatestNewsDataSourceImpl
    ): LatestNewsDataSource {
        return dataSource
    }

    @Provides
    fun providesSearchNewsDataSource(
        dataSource: SearchNewsDataSourceImpl
    ): SearchNewsDataSource{
        return dataSource
    }


    @Provides
    fun providesLatestNewsRepository(
        repository: LatestNewsRepositoryImpl
    ): LatestNewsRepository {
        return repository
    }



    @Provides
    fun providesSearchNewsRepository(
        repository: SearchNewsRepositoryImpl
    ): SearchNewsRepository {
        return repository
    }




}