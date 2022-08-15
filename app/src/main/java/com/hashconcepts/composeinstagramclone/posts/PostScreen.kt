package com.hashconcepts.composeinstagramclone.posts

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Icon
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.hashconcepts.composeinstagramclone.R
import com.hashconcepts.composeinstagramclone.ui.theme.FormFieldBgDark
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
        Column(modifier = Modifier.fillMaxSize()) {
            ToolbarSection {
                navigator.navigateUp()
            }
        }

        Row(
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier
                .clip(RoundedCornerShape(20.dp))
                .background(FormFieldBgDark)
                .padding(10.dp)
                .align(Alignment.BottomEnd)
        ) {
            Text(text = "POST", style = MaterialTheme.typography.body1, color = Color.White)
            Spacer(modifier = Modifier.width(10.dp))
            Text(text = "STORY", style = MaterialTheme.typography.body1, color = Color.White)
            Spacer(modifier = Modifier.width(10.dp))
            Text(text = "REEL", style = MaterialTheme.typography.body1, color = Color.White)
        }
    }
}

@Composable
fun ToolbarSection(onClose: () -> Unit) {
    val darkTheme: Boolean = isSystemInDarkTheme()
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(top = 20.dp, start = 20.dp, end = 20.dp)
            .background(color = if (darkTheme) Color.Black else Color.White),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Icon(
            painter = painterResource(id = R.drawable.ic_exit),
            contentDescription = null,
            modifier = Modifier
                .size(25.dp)
                .clickable { onClose() }
        )

        Spacer(modifier = Modifier.width(25.dp))

        Text(text = "New post", style = MaterialTheme.typography.h1)
    }
}
