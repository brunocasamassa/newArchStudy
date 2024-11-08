package com.example.newarchstudy.ui.presentation.latest

import android.content.Intent
import android.net.Uri
import android.widget.Toast
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.core.Spring
import androidx.compose.animation.core.animateDpAsState
import androidx.compose.animation.core.spring
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.BoxWithConstraints
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.ElevatedCard
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Alignment.Companion.CenterHorizontally
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.bumptech.glide.integration.compose.ExperimentalGlideComposeApi
import com.bumptech.glide.integration.compose.GlideImage
import com.example.newarchstudy.R
import com.example.newarchstudy.ui.presentation.IndeterminateCircularIndicator
import com.example.newarchstudy.ui.presentation.NewsItemCompose
import com.example.newarchstudy.ui.theme.NewArchStudyTheme
import com.example.newarchstudy.utils.Factory

@Composable
fun LatestNewsScreen() {

    val uiState by Factory.latestNewsViewModel.uiState.collectAsStateWithLifecycle()

    BoxWithConstraints() {


        var selectedNew = rememberSaveable {
            mutableStateOf("")
        }

        var isLoading: MutableState<Boolean> =
            rememberSaveable { mutableStateOf(uiState.isLoading) }

        if (uiState.isLoading) {
            IndeterminateCircularIndicator(loading = isLoading, this)
        } else {

            LazyColumn(modifier = Modifier.padding(vertical = 4.dp)) {
                items(items = uiState?.latestNews?.news.orEmpty()) {
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

