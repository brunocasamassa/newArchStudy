package com.example.newarchstudy.ui.presentation

import android.content.Intent
import android.net.Uri
import androidx.compose.animation.core.Spring
import androidx.compose.animation.core.animateDpAsState
import androidx.compose.animation.core.spring
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.ElevatedCard
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.bumptech.glide.integration.compose.ExperimentalGlideComposeApi
import com.bumptech.glide.integration.compose.GlideImage


@OptIn(ExperimentalGlideComposeApi::class, ExperimentalMaterial3Api::class)
@Composable
fun NewsItemCard(
    selectedNew: MutableState<String>?,
    name: String,
    image: String,
    description: String,
    url: String,
    onNewsLineClicked: () -> Unit = {}
) {
    Box {

        val context = LocalContext.current
        val intent = Intent(Intent.ACTION_VIEW, Uri.parse(url))

        val extraHeightAnimation by animateDpAsState(
            if (selectedNew?.value == name) 400.dp else 200.dp, label = "",
            animationSpec = spring(dampingRatio = Spring.DampingRatioMediumBouncy)
        )



        ElevatedCard(
            onClick = {
                selectedNew?.value = name
            },
            shape = RoundedCornerShape(16.dp),
            modifier = Modifier
                .padding(start = 16.dp, top = 16.dp, end = 16.dp, bottom = 16.dp)
                .height((extraHeightAnimation.takeIf { it > (-0.1).dp } ?: 200.dp))

        )
        {
            Column()
            {
                Text(
                    color = if (selectedNew?.value == name) Color.Red else Color.Black,
                    modifier = Modifier.padding(16.dp),
                    textAlign = TextAlign.Center,
                    text = name,
                    fontWeight = FontWeight.Bold
                )
                GlideImage(
                    contentScale = ContentScale.Crop,
                    modifier = Modifier
                        .padding(16.dp)
                        .width(200.dp)
                        .height(200.dp)
                        .clip(RoundedCornerShape(12))
                        .align(Alignment.CenterHorizontally)
                        .clickable { onNewsLineClicked() },
                    model = image, contentDescription = description,
                )
                ElevatedCard(
                    shape = RoundedCornerShape(8.dp),
                    modifier = Modifier.padding(16.dp),
                    onClick = { context.startActivity(intent) }) {
                    Text(
                        modifier = Modifier
                            .align(Alignment.CenterHorizontally)
                            .padding(8.dp),
                        text = description,
                        fontSize = 12.sp,
                        maxLines = 4
                    )
                }
            }
        }

    }
}