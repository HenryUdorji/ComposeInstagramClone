package com.hashconcepts.composeinstagramclone.auth.presentation

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.hashconcepts.composeinstagramclone.R
import com.ramcosta.composedestinations.annotation.Destination
import com.ramcosta.composedestinations.navigation.DestinationsNavigator

/**
 * @created 29/07/2022 - 11:04 PM
 * @project ComposeInstagramClone
 * @author  ifechukwu.udorji
 */

@Destination
@Composable
fun LoginScreen(
    navigator: DestinationsNavigator
) {

    Box(modifier = Modifier.fillMaxSize().padding(top = 20.dp)) {

        Image(
            painter = painterResource(id = R.drawable.ic_back_arrow),
            contentDescription = null,
            modifier = Modifier
                .align(Alignment.TopStart)
                .padding(start = 16.dp)
                .clickable { navigator.navigateUp() }
        )
    }
}