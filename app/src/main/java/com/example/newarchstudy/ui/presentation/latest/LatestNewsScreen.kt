package com.example.newarchstudy.ui.presentation.latest

import android.widget.Toast
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.core.animate
import androidx.compose.animation.core.tween
import androidx.compose.animation.fadeIn
import androidx.compose.animation.slideInVertically
import androidx.compose.foundation.layout.BoxWithConstraints
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.semantics.contentDescription
import androidx.compose.ui.semantics.semantics
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.example.newarchstudy.R
import com.example.newarchstudy.ui.presentation.IndeterminateCircularIndicator
import com.example.newarchstudy.ui.presentation.NewsItemCard
import com.example.newarchstudy.ui.theme.NewArchStudyTheme
import com.example.newarchstudy.viewmodels.LatestNewsUiState
import com.example.newarchstudy.viewmodels.LatestNewsViewModel
import kotlinx.coroutines.delay

@Composable
fun LatestNewsScreen(
    viewModel: LatestNewsViewModel = hiltViewModel(),
    onNewsClicked: (image: String, name: String, description: String, url: String) -> Unit,
) {

    val uiState by viewModel.uiState.collectAsStateWithLifecycle()

    var contentTitle = stringResource(R.string.latest_news)

    BoxWithConstraints(modifier = Modifier.semantics { contentDescription = contentTitle }) {

        var isSelected = rememberSaveable {
            mutableStateOf("")
        }


        when (uiState) {
            is LatestNewsUiState.Loading -> {
                IndeterminateCircularIndicator(
                    loading = rememberSaveable { mutableStateOf(true) },
                    this
                )
            }

            is LatestNewsUiState.Success -> {
                LazyColumn(
                    modifier = Modifier
                        .padding(vertical = 4.dp)
                ) {
                    itemsIndexed(
                        items = (uiState as? LatestNewsUiState.Success)?.response?.news.orEmpty(),
                        key = { _, item -> item.url }) { index, newItem ->
                        var isVisible by remember { mutableStateOf(false) }

                        LaunchedEffect(key1 = true) {
                            delay(index * 100L)
                            isVisible = true
                        }

                        AnimatedVisibility(
                            visible = isVisible,
                            enter = /*slideInVertically(
                                initialOffsetY = { it / 2 },
                                animationSpec = tween(durationMillis = 500)
                            ) + */fadeIn(animationSpec = tween(durationMillis = 1000))
                        ) {
                            NewsItemCard(
                                isSelected,
                                name = newItem.title,
                                image = newItem.image,
                                description = newItem.description,
                                url = newItem.url,
                                onNewsLineClicked = {
                                    onNewsClicked(
                                        newItem.image,
                                        newItem.title,
                                        newItem.description,
                                        newItem.url
                                    )
                                }
                            )
                        }
                    }
                }

            }

            is LatestNewsUiState.Error -> {
                Toast.makeText(
                    LocalContext.current,
                    (uiState as? LatestNewsUiState.Error)?.message,
                    Toast.LENGTH_LONG
                ).show()
            }


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

