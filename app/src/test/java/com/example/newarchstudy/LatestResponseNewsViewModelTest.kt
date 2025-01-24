package com.example.newarchstudy

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.example.newarchstudy.data.apis.NewsApi
import com.example.newarchstudy.data.models.dto.ResponseNews
import com.example.newarchstudy.data.models.entities.News
import com.example.newarchstudy.data.repositories.latest.LatestNewsRepository
import com.example.newarchstudy.data.source.latest.LatestNewsDataSourceImpl.Companion.STATUS_OK
import com.example.newarchstudy.viewmodels.LatestNewsUiState
import com.example.newarchstudy.viewmodels.LatestNewsViewModel
import io.mockk.every
import io.mockk.mockk
import junit.framework.TestCase.assertEquals
import junit.framework.TestCase.assertTrue
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.runBlocking
import kotlinx.coroutines.test.TestCoroutineDispatcher
import kotlinx.coroutines.test.resetMain
import kotlinx.coroutines.test.setMain
import org.junit.After
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.junit.runners.JUnit4

@ExperimentalCoroutinesApi
@RunWith(JUnit4::class)
class LatestResponseNewsViewModelTest {

    private lateinit var newsApi: NewsApi
    private lateinit var latestNewsRepository: LatestNewsRepository
    private lateinit var latestNewsViewModel: LatestNewsViewModel

    //importante para funcionar coroutines
    @get:Rule
    val instantTaskExecutorRule: InstantTaskExecutorRule = InstantTaskExecutorRule()
    private val testDispatcher = TestCoroutineDispatcher()

    @Before
    fun setup(){
        Dispatchers.setMain(testDispatcher)
        newsApi = mockk<NewsApi>()
        latestNewsRepository = mockk<LatestNewsRepository>()
        latestNewsViewModel = mockk<LatestNewsViewModel>()
    }

    @Test
    fun `uiState should trigger success when data is fetched successfully`() = runBlocking {
        every { latestNewsRepository.fetchNewsData } returns MutableStateFlow(News(status = STATUS_OK))
        every { latestNewsViewModel.uiState.value } returns LatestNewsUiState.Success(News(status = STATUS_OK))

        assertEquals( STATUS_OK, (latestNewsViewModel.uiState.value as LatestNewsUiState.Success).response.status)
    }

    @Test
    fun `uiState should trigger error when data fetching fails`() = runBlocking {

        every { latestNewsViewModel.uiState.value } returns LatestNewsUiState.Error("error")

        assertTrue(latestNewsViewModel.uiState.value is LatestNewsUiState.Error )
    }


    @Test
    fun `uiState should trigger loading when data is being fetched`() = runBlocking {

        every { latestNewsViewModel.uiState.value } returns LatestNewsUiState.Loading

        assertTrue( latestNewsViewModel.uiState.value is LatestNewsUiState.Loading)
    }



    @Test
    fun testSuccessState() {
        // Arrange
        val expectedNews = News(status = STATUS_OK)
       every {latestNewsRepository.fetchNewsData} returns (flowOf(expectedNews))
       every {latestNewsViewModel.latestNewResponse} returns latestNewsRepository.fetchNewsData

        // Assert
        runBlocking {
            val actualState = latestNewsViewModel.latestNewResponse.first()
            assertEquals(expectedNews, actualState)
        }
    }

    @After
    fun tearDown() {
        Dispatchers.resetMain()
        testDispatcher.cleanupTestCoroutines()
    }



}