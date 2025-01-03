package com.example.newarchstudy.viewmodels

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.newarchstudy.data.models.news.News
import com.example.newarchstudy.data.repositories.latest.LatestNewsRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.flow.stateIn
import javax.inject.Inject


@HiltViewModel
class LatestNewsViewModel @Inject constructor(
     latestNewsRepository: LatestNewsRepository
) : ViewModel() {

    //necessario esses mutables para utilizar dentro da classe, mesmo soh observando o UIstate
    private val _isLoading = MutableStateFlow(true)
    //
    val isLoading: StateFlow<Boolean>
        get() = _isLoading

    val latestNewResponse: Flow<News> = latestNewsRepository.fetchNewsData

    val uiState: StateFlow<LatestNewsUiState> = combine(
        isLoading,
        latestNewResponse
    ) { _, response ->
        LatestNewsUiState(
            isLoading = true
        )

        when (response.news.isNullOrEmpty().not()) {
            true -> {
                LatestNewsUiState(
                    isLoading = false,
                    latestNews = response
                )

            }

            else -> {
                LatestNewsUiState(
                    isLoading = false,
                    isError = true
                )

            }

        }


    }.stateIn(
        initialValue = LatestNewsUiState(true, News()),
        scope = viewModelScope,
        started = SharingStarted.WhileSubscribed(5000)
    )


    /** ui state for latest news */
    data class LatestNewsUiState(
        val isLoading: Boolean = false,
        val latestNews: News? = null,
        val isError: Boolean = false
    )

}