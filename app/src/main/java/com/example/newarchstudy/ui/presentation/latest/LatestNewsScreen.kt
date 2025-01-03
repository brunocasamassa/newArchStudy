package com.example.newarchstudy.ui.presentation.latest

import android.widget.Toast
import androidx.compose.foundation.layout.BoxWithConstraints
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.semantics.contentDescription
import androidx.compose.ui.semantics.semantics
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.newarchstudy.R
import com.example.newarchstudy.ui.presentation.IndeterminateCircularIndicator
import com.example.newarchstudy.ui.presentation.NewsItemCard
import com.example.newarchstudy.ui.theme.NewArchStudyTheme
import com.example.newarchstudy.viewmodels.LatestNewsViewModel

@Composable
fun LatestNewsScreen(viewModel: LatestNewsViewModel = hiltViewModel()) {

    val uiState by viewModel.uiState.collectAsState()
    var contentTitle = stringResource(R.string.latest_news)

    BoxWithConstraints(modifier = Modifier.semantics{ contentDescription = contentTitle}) {

        var isSelected = rememberSaveable {
            mutableStateOf("")
        }

        var isLoading: MutableState<Boolean> =
            rememberSaveable { mutableStateOf(uiState.isLoading) }

        if (uiState.isLoading) {
            IndeterminateCircularIndicator(loading = isLoading, this)
        } else {

            LazyColumn(modifier = Modifier.padding(vertical = 4.dp)) {
                items(items = uiState?.latestNews?.news.orEmpty()) {
                    NewsItemCard(
                        isSelected,
                        name = it.title,
                        image = it.image,
                        description = it.description,
                        url = it.url
                    )
                }
            }

            if (uiState.isError) Toast.makeText(
                LocalContext.current,
                uiState.latestNews?.status,
                Toast.LENGTH_LONG
            ).show()

        }


    }

}


@Composable
fun Greeting(name: String, modifier: Modifier = Modifier) {
    Text(
        text = "Hello $name!",
        modifier = modifier
    )
}

@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    NewArchStudyTheme {
        Greeting(stringResource(id = R.string.app_name))
    }
}

