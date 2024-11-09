package com.example.newarchstudy.ui.presentation.search

import android.widget.Toast
import androidx.compose.foundation.layout.BoxWithConstraints
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Close
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.SearchBar
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.example.newarchstudy.R
import com.example.newarchstudy.ui.presentation.IndeterminateCircularIndicator
import com.example.newarchstudy.ui.presentation.NewsItemCompose
import com.example.newarchstudy.utils.Factory
import com.example.newarchstudy.viewmodels.SearchNewsUiState
import com.example.newarchstudy.viewmodels.SearchNewsViewModel
import kotlinx.coroutines.flow.flow

@Composable
fun SearchNewsScreen(uiState: SearchNewsUiState) {

    BoxWithConstraints() {

        var selectedNew = rememberSaveable {
            mutableStateOf("")
        }

        val isLoading: MutableState<Boolean> =
            rememberSaveable { mutableStateOf(uiState.isLoading) }

        if (uiState.isLoading) {
          IndeterminateCircularIndicator(loading = isLoading, this)
        } else {

            LazyColumn(modifier = Modifier.padding(vertical = 4.dp)) {
                items(items = uiState?.searchedNews?.news.orEmpty()) {
                    NewsItemCompose(
                        selectedNew,
                        name = it.title,
                        image = it.image,
                        description = it.description,
                        url = it.url
                    )
                }
            }

            if (uiState.isError) Toast.makeText(
                LocalContext.current,
                uiState.searchedNews?.status,
                Toast.LENGTH_LONG
            ).show()

        }


    }

}



