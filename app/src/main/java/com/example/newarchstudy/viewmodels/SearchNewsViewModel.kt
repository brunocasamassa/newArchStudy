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
import kotlinx.coroutines.flow.single
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch


/** View Model for Latest News */
class SearchNewsViewModel: ViewModel() {

    //necessario esses mutables para utilizar dentro da classe, mesmo soh observando o UIstate
    private val _isLoading = MutableStateFlow(true)
    private val _searchNews = MutableStateFlow(News())
    private val description  = MutableStateFlow("")


    var title : Flow<String> = MutableStateFlow("")
    var author : Flow<String> = MutableStateFlow("")
    var language : Flow<String> = MutableStateFlow("")
    var region : Flow<String> = MutableStateFlow("")


    //todo test
    var uiState: StateFlow<SearchNewsUiState> = combine(
        _isLoading,
        _searchNews,
        title,
        author,
        description,
        language,
        region
    ) {
        SearchNewsUiState(
            isLoading = true
        )

        val response = searchNewsRepository.searchNews(
            title = title.single(),
            author = author.single(),
            description = description.single(),
            language = language.single(), region = region.single()?:"").single()

        when(response.news.isNullOrEmpty().not()){
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


    }.stateIn(initialValue = SearchNewsUiState(false,News()), scope = viewModelScope, started = SharingStarted.WhileSubscribed(5000))


    fun searchNews(text: String){
        viewModelScope.launch {
            _isLoading.value = true
            description.value = text

    }

}
}



/** ui state for latest news */
data class SearchNewsUiState(
    val isLoading: Boolean = false,
    val searchedNews : News? = null,
    val isError: Boolean = false
)