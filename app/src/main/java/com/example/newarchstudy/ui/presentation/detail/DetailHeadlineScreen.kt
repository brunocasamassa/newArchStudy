package com.example.newarchstudy.ui.presentation.detail

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import com.bumptech.glide.integration.compose.ExperimentalGlideComposeApi
import com.bumptech.glide.integration.compose.GlideImage
import java.net.URLDecoder
import java.nio.charset.StandardCharsets

@OptIn(ExperimentalGlideComposeApi::class)
@Composable
fun DetailHeadlineScreen(name: String?, image: String?, description: String?, url: String?) {


    Column(modifier = Modifier.verticalScroll(rememberScrollState())) {
        GlideImage(
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp),
            model = image,
            contentDescription = "",
            alignment = Alignment.Center
        )
        Text(
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp),
            text = name.orEmpty().removeSurrounding("{","}"),
            fontWeight = FontWeight.Bold
        )
        Text(
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp),
            text = description.orEmpty().removeSurrounding("{","}")
        )


    }


}