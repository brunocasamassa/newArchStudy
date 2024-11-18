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
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.example.newarchstudy.R
import com.example.newarchstudy.ui.presentation.IndeterminateCircularIndicator
import com.example.newarchstudy.ui.presentation.NewsItemCard import com.example.newarchstudy.viewmodels.SearchNewsViewModel

@Composable
fun SearchNewsScreen(viewModel: SearchNewsViewModel = hiltViewModel()) {

    val uiState by viewModel.uiState.collectAsStateWithLifecycle()

    BoxWithConstraints() {

        /**
         *
         *  rememberSaveable = keep its value after lifecycle changes
         *  remember = lose its value after lifecycle changes
         *
         * */

        var selectedNew = rememberSaveable {
            mutableStateOf("")
        }

        val isLoading: MutableState<Boolean> =
            remember { mutableStateOf(uiState.isLoading) }

        /**
         *
         * */

        if (uiState.isLoading) {
          IndeterminateCircularIndicator(loading = isLoading, this)
        } else {

            LazyColumn(modifier = Modifier.padding(vertical = 4.dp)) {
                items(items = uiState?.searchedNews?.news.orEmpty()) {
                    NewsItemCard(
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

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SearchTopBar(viewModel: SearchNewsViewModel = hiltViewModel()) {

    var currentlyText by remember {
        mutableStateOf("")
    }
    var isActive by remember { mutableStateOf(false) }

    SearchBar(
        onQueryChange = { currentlyText = it }, //update the value of searchText
        onSearch = {
            isActive = false
            viewModel.searchNews(currentlyText)

        }, //the callback to be invoked when the input service triggers the ImeAction.Search action
        onActiveChange = {
            isActive = it
        }, //the callback to be invoked when this search bar's active state is changed
        modifier = Modifier
            .fillMaxWidth()
            .padding(16.dp),
        active = isActive,
        content = { currentlyText },
        query = currentlyText,
        placeholder = { Text(text = stringResource(R.string.search)) },
        leadingIcon = {
            Icon(
                imageVector = Icons.Filled.Search, contentDescription = stringResource(
                    R.string.search
                )
            )
        },
        trailingIcon = {
            Icon(
                imageVector = Icons.Filled.Close, contentDescription = stringResource(
                    R.string.close
                )
            )
        }
    )


}



