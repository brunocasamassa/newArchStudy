package com.example.newarchstudy

import com.example.newarchstudy.data.apis.NewsApi
import com.google.gson.Gson
import com.google.gson.GsonBuilder
import kotlinx.coroutines.runBlocking
import okhttp3.mockwebserver.MockResponse
import okhttp3.mockwebserver.MockWebServer
import org.hamcrest.MatcherAssert.assertThat
import org.junit.After
import org.junit.Before
import org.junit.Test
import org.mockito.Mock
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class ApiTest {

    @Mock
    lateinit var mockWebServer: MockWebServer
    @Mock
    lateinit var apiService: NewsApi
    lateinit var gson: Gson

    @Before
    fun setup() {
        gson = GsonBuilder().create()
        mockWebServer = MockWebServer()
        apiService = Retrofit.Builder()
            .baseUrl(mockWebServer.url(BuildConfig.BASE_URL))
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
    fun validateUserData_return_success() {
        runBlocking {
            val mockResponse = MockResponse()
            mockWebServer.enqueue(mockResponse.setBody("{}"))

           // val response = apiService.getLatestNews().await()
            val request = mockWebServer.takeRequest()

            assert(request.path == "/latest-news")
        }
    }



}