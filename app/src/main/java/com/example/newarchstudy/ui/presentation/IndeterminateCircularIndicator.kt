package com.example.newarchstudy.ui.presentation

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.layout.BoxWithConstraints
import androidx.compose.foundation.layout.BoxWithConstraintsScope
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.VerticalAlignmentLine
import androidx.compose.ui.unit.dp

@Composable
fun IndeterminateCircularIndicator(loading: MutableState<Boolean>, constraint: BoxWithConstraintsScope) {

        AnimatedVisibility(visible = loading.value) {

            CircularProgressIndicator(
                modifier = Modifier.padding(
                    start = (constraint.maxWidth.div(2.3.toFloat())),
                    top = constraint.maxHeight.div(2.3.toFloat())
                ),
                color = MaterialTheme.colorScheme.secondary,
                trackColor = MaterialTheme.colorScheme.surfaceVariant

            )

        }

}
