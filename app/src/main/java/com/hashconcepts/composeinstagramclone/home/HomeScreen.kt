package com.hashconcepts.composeinstagramclone.home

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.material.Icon
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.hashconcepts.composeinstagramclone.R
import com.hashconcepts.composeinstagramclone.destinations.PostScreenDestination
import com.ramcosta.composedestinations.annotation.Destination
import com.ramcosta.composedestinations.navigation.DestinationsNavigator

/**
 * @created 05/08/2022 - 12:48 AM
 * @project ComposeInstagramClone
 * @author  ifechukwu.udorji
 */

@Destination
@Composable
fun HomeScreen(
    navigator: DestinationsNavigator
) {
    Column(
        modifier = Modifier.fillMaxSize(),
    ) {
        TopSection(
            onCameraClick = {},
            onCreatePostClick = {
                navigator.navigate(PostScreenDestination)
            },
            onMessageClick = {},
        )
    }
}

@Composable
fun TopSection(
    onCameraClick: () -> Unit,
    onCreatePostClick: () -> Unit,
    onMessageClick: () -> Unit
) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(top = 20.dp, start = 20.dp, end = 20.dp)
            .height(40.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Icon(painter = painterResource(id = R.drawable.ic_camera), contentDescription = null)

        Icon(
            painter = painterResource(id = R.drawable.ic_instagram_logo),
            contentDescription = null,
            modifier = Modifier
                .weight(1f)
                .height(28.dp)
                .width(105.dp)
        )

        Icon(
            painter = painterResource(id = R.drawable.ic_post),
            contentDescription = null,
            modifier = Modifier.clickable { onCreatePostClick() }
        )

        Spacer(modifier = Modifier.width(18.dp))

        Icon(painter = painterResource(id = R.drawable.ic_messanger), contentDescription = null)
    }
}
