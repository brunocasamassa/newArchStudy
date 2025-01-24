package com.example.newarchstudy.viewmodels

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.newarchstudy.data.models.dto.ResponseNews
import com.example.newarchstudy.data.models.entities.News
import com.example.newarchstudy.data.repositories.latest.LatestNewsRepository
import com.example.newarchstudy.data.source.latest.LatestNewsDataSourceImpl.Companion.STATUS_OK
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject


@HiltViewModel
class LatestNewsViewModel @Inject constructor(
    latestNewsRepository: LatestNewsRepository
) : ViewModel() {

    val latestNewResponse: Flow<News> = latestNewsRepository.fetchNewsData

    private val _uiState =
        MutableStateFlow<LatestNewsUiState>(LatestNewsUiState.Success(News()))


    val uiState: StateFlow<LatestNewsUiState> = _uiState


    init {

        viewModelScope.launch(Dispatchers.IO) {
            _uiState.value = LatestNewsUiState.Loading
            latestNewResponse.collect {
                when (it.status == STATUS_OK) {

                    true -> {
                        _uiState.value = LatestNewsUiState.Success(it)
                    }

                    else -> {
                        _uiState.value = LatestNewsUiState.Error(it.status)

                    }
                }

            }

        }

    }

}


/** ui state for latest news */
sealed class LatestNewsUiState {
    data object Loading : LatestNewsUiState()
    data class Error(val message: String?) : LatestNewsUiState()
    data class Success(val response: News) : LatestNewsUiState()
}
