package com.hashconcepts.composeinstagramclone.auth.presentation

import android.window.SplashScreen
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.hashconcepts.composeinstagramclone.R
import com.hashconcepts.composeinstagramclone.auth.presentation.destinations.AuthWelcomeScreenDestination
import com.hashconcepts.composeinstagramclone.auth.presentation.destinations.LoginScreenDestination
import com.ramcosta.composedestinations.annotation.Destination
import com.ramcosta.composedestinations.annotation.RootNavGraph
import com.ramcosta.composedestinations.navigation.DestinationsNavigator
import kotlinx.coroutines.delay

/**
 * @created 29/07/2022 - 9:49 PM
 * @project ComposeInstagramClone
 * @author  ifechukwu.udorji
 */

@RootNavGraph(true)
@Destination
@Composable
fun SplashScreen(
    navigator: DestinationsNavigator,
) {

    LaunchedEffect(key1 = true) {
        delay(500L)
        navigator.popBackStack()
        navigator.navigate(AuthWelcomeScreenDestination)
    }

    Box(modifier = Modifier.fillMaxSize()) {
        Image(
            painter = painterResource(id = R.drawable.app_icon),
            contentDescription = null,
            modifier = Modifier
                .align(Alignment.Center)
                .size(100.dp)
        )

        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            modifier = Modifier
                .fillMaxWidth()
                .align(Alignment.BottomCenter)
                .padding(bottom = 10.dp)
        ) {
            Text(
                text = "From",
                style = MaterialTheme.typography.body1,
                textAlign = TextAlign.Center,
                color = Color.Gray
            )
            Image(
                painter = painterResource(id = R.drawable.ic_meta),
                contentDescription = null,
                modifier = Modifier.size(40.dp)
            )
        }
    }
}