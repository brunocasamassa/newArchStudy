package com.example.newarchstudy

import com.example.newarchstudy.data.apis.NewsApi
import com.example.newarchstudy.data.models.news.News
import com.example.newarchstudy.data.repositories.latest.LatestNewsRepository
import com.example.newarchstudy.data.source.latest.LatestNewsDataSourceImpl
import com.example.newarchstudy.data.source.latest.LatestNewsDataSourceImpl.Companion.STATUS_OK
import com.example.newarchstudy.viewmodels.LatestNewsUiState
import com.example.newarchstudy.viewmodels.LatestNewsViewModel
import io.mockk.every
import io.mockk.mockk
import junit.framework.TestCase.assertEquals
import junit.framework.TestCase.assertTrue
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.runBlocking
import org.junit.Before
import org.junit.Test

class LatestNewsViewModelTest {

    private lateinit var newsApi: NewsApi
    private lateinit var latestNewsDataSourceImpl: LatestNewsDataSourceImpl
    private lateinit var latestNewsRepository: LatestNewsRepository
    private lateinit var latestNewsViewModel: LatestNewsViewModel
    val mockedNews = News(news = listOf(), status = STATUS_OK)


    @Before
    fun setup(){
        newsApi = mockk<NewsApi>()
        latestNewsDataSourceImpl = mockk<LatestNewsDataSourceImpl>()
        latestNewsRepository = mockk<LatestNewsRepository>()
        latestNewsViewModel = mockk<LatestNewsViewModel>()

    }

    @ExperimentalCoroutinesApi
    @Test
    fun `uiState should trigger success when data is fetched successfully`() = runBlocking {
        every { latestNewsRepository.fetchNewsData } returns MutableStateFlow(mockedNews)
        every { latestNewsViewModel.uiState.value } returns LatestNewsUiState.Success(mockedNews)

        assertEquals( STATUS_OK, (latestNewsViewModel.uiState.value as LatestNewsUiState.Success).response.status)
    }

    @ExperimentalCoroutinesApi
    @Test
    fun `uiState should trigger error when data fetching fails`() = runBlocking {

        every { latestNewsViewModel.uiState.value } returns LatestNewsUiState.Error("error")

        assertTrue(latestNewsViewModel.uiState.value is LatestNewsUiState.Error )
    }

    @ExperimentalCoroutinesApi
    @Test
    fun `uiState should trigger loading when data is being fetched`() = runBlocking {

        every { latestNewsViewModel.uiState.value } returns LatestNewsUiState.Loading

        assertTrue( latestNewsViewModel.uiState.value is LatestNewsUiState.Loading)
    }


}