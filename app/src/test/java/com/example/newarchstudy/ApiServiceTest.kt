package com.example.newarchstudy

import com.example.newarchstudy.data.apis.NewsApi
import com.example.newarchstudy.data.models.dto.ResponseNews
import com.google.gson.Gson
import com.google.gson.GsonBuilder
import io.mockk.MockKAnnotations
import io.mockk.impl.annotations.MockK
import kotlinx.coroutines.runBlocking
import okhttp3.mockwebserver.MockResponse
import okhttp3.mockwebserver.MockWebServer
import org.hamcrest.CoreMatchers.notNullValue
import org.hamcrest.MatcherAssert.assertThat
import org.junit.After
import org.junit.Before
import org.junit.Test
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory


class ApiServiceTest {

    @MockK
    lateinit var mockWebServer: MockWebServer

    @MockK
    lateinit var apiService: NewsApi

    private lateinit var gson: Gson

    @Before
    fun setup() {
        MockKAnnotations.init(this)
        gson = GsonBuilder().create()
        mockWebServer = MockWebServer()
        apiService = Retrofit.Builder()
            .baseUrl(mockWebServer.url("/"))
            .addConverterFactory(GsonConverterFactory.create(gson))
            .build()
            .create(NewsApi::class.java)
    }

    @After
    fun deconstruct() {
        mockWebServer.shutdown()
        mockWebServer.close()
    }

    @Test
    fun `validate getLatestNews returns data successfully`() {
        runBlocking {

            val mockedResponse = MockResponse()

            mockWebServer.enqueue(mockedResponse.setBody(gson.toJson(ResponseNews())))

            val response = apiService.getLatestNews()
            assertThat(response, (notNullValue()))

        }
    }

    @Test
    fun `validate getSearchNews returns data successfully`() {
        runBlocking {

            val mockedResponse = MockResponse()

            mockWebServer.enqueue(mockedResponse.setBody(gson.toJson(ResponseNews())))

            val response = apiService.getSearchNews()
            assertThat(response, (notNullValue()))

        }
    }


}