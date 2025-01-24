package com.example.newarchstudy.viewmodels

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.newarchstudy.data.models.dto.ResponseNews
import com.example.newarchstudy.data.models.entities.News
import com.example.newarchstudy.data.repositories.search.SearchNewsRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

/**
 * cold streams -> starts only after a subscribers (flows)
 *
 * hot streams -> starts immediately (channels, Set)
 *
 *
 * */

@HiltViewModel
class SearchNewsViewModel @Inject constructor(
    private val searchNewsRepository: SearchNewsRepository
) : ViewModel() {

    private val _isLoading = MutableStateFlow(true)
    val isLoading: StateFlow<Boolean>
        get() = _isLoading


    private val _uiState = MutableStateFlow(SearchNewsUiState())
    val uiState: StateFlow<SearchNewsUiState>
        get() = _uiState


    fun searchNews(typedText: String) {
        viewModelScope.launch {
            _uiState.value = SearchNewsUiState(isLoading = true)
            searchNewsRepository.searchNews(text = typedText)
                .collect {
                    _uiState.value = when (it.news.isNullOrEmpty().not()) {
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