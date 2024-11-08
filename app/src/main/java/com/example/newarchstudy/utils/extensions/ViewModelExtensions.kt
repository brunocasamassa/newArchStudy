package com.example.newarchstudy.utils.extensions

import androidx.core.app.ComponentActivity
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import com.example.newarchstudy.data.models.news.News
import com.example.newarchstudy.utils.Factory.latestNewsViewModel
import kotlinx.coroutines.async


fun ComponentActivity.observeLatestNews(function: (News) -> Unit) {
    lifecycleScope.async {
        repeatOnLifecycle(Lifecycle.State.STARTED) {
            latestNewsViewModel.latestNewResponse.collect {
                function(it)
            }
        }
    }}
