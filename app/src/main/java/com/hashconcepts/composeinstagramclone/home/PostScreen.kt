package com.hashconcepts.composeinstagramclone.home

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import com.ramcosta.composedestinations.annotation.Destination
import com.ramcosta.composedestinations.navigation.DestinationsNavigator

/**
 * @created 05/08/2022 - 12:48 AM
 * @project ComposeInstagramClone
 * @author  ifechukwu.udorji
 */

@Destination
@Composable
fun PostScreen(
    navigator: DestinationsNavigator
) {
    Box(
        modifier = Modifier.fillMaxSize(),
        contentAlignment = Alignment.Center
    ) {
        Text(text = "POST", style = MaterialTheme.typography.h1)
    }
}