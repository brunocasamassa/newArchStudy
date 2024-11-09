package com.example.newarchstudy.viewmodels

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.newarchstudy.data.models.news.News
import com.example.newarchstudy.utils.Factory.searchNewsRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.flow.single
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch

/**
 * cold streams -> starts only after a subscribers (flows)
 *
 * hot streams -> starts immediately (channels, Set)
 *
 *
 * */

/** View Model for Latest News */
class SearchNewsViewModel : ViewModel() {

    //necessario esses mutables para utilizar dentro da classe, mesmo soh observando o UIstate
    private val _isLoading = MutableStateFlow(true)
    private val description = MutableStateFlow("")


    var title: Flow<String> = MutableStateFlow("")
    var author: Flow<String> = MutableStateFlow("")
    var language: Flow<String> = MutableStateFlow("")
    var region: Flow<String> = MutableStateFlow("")

    private var _searchNews: Flow<News> =
        searchNewsRepository.searchNews(author = description.value)

    //todo test
    val uiState = MutableStateFlow(SearchNewsUiState()) /*= combine(
        _isLoading,
        _searchNews
    ) { _, response ->
        SearchNewsUiState(
            isLoading = true
        )

        when (response.news.isNullOrEmpty().not()) {
            true -> {
                SearchNewsUiState(
                    isLoading = false,
                    searchedNews = response
                )

            }

            else -> {
                SearchNewsUiState(
                    isLoading = false,
                    isError = true
                )

            }

        }


    }.stateIn(
        initialValue = SearchNewsUiState(false, News()),
        scope = viewModelScope,
        started = SharingStarted.WhileSubscribed(5000)
    )*/


    fun searchNews(text: String) {
        viewModelScope.launch {
            searchNewsRepository.searchNews(author = text)
                .collect {
                    uiState.value = when (it.news.isNullOrEmpty().not()) {
                        true -> {
                            SearchNewsUiState(
                                isLoading = false,
                                searchedNews = it
                            )

                        }

                        else -> {
                            SearchNewsUiState(
                                isLoading = false,
                                isError = true
                            )

                        }

                    }
                }
        }
    }


    /** ui state for latest news */
    data class SearchNewsUiState(
        val isLoading: Boolean = false,
        val searchedNews: News? = null,
        val isError: Boolean = false
    )

}