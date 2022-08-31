package com.hashconcepts.composeinstagramclone.posts

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Icon
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import com.google.accompanist.pager.ExperimentalPagerApi
import com.google.accompanist.pager.HorizontalPager
import com.google.accompanist.pager.PagerState
import com.google.accompanist.pager.rememberPagerState
import com.hashconcepts.composeinstagramclone.R
import com.hashconcepts.composeinstagramclone.ui.theme.FormFieldBgDark
import com.ramcosta.composedestinations.annotation.Destination
import com.ramcosta.composedestinations.navigation.DestinationsNavigator
import kotlinx.coroutines.launch

/**
 * @created 05/08/2022 - 12:48 AM
 * @project ComposeInstagramClone
 * @author  ifechukwu.udorji
 */

@OptIn(ExperimentalPagerApi::class)
@Destination
@Composable
fun PostScreen(
    navigator: DestinationsNavigator
) {
    Column(modifier = Modifier.fillMaxSize()) {
        ToolbarSection {
            navigator.navigateUp()
        }

        Box(
            modifier = Modifier.weight(1f),
            contentAlignment = Alignment.Center,
        ) {
            val options = listOf("POST", "STORY", "REEL")
            val scope = rememberCoroutineScope()
            val pagerState = rememberPagerState()

            MainSection(pagerState = pagerState)

            BottomOptionSection(
                options,
                selectedItem = pagerState.currentPage,
                onOptionSelected = { position ->
                    scope.launch {
                        pagerState.scrollToPage(position)
                    }
                }
            )
        }
    }
}

@OptIn(ExperimentalPagerApi::class)
@Composable
fun MainSection(pagerState: PagerState) {
    val darkTheme: Boolean = isSystemInDarkTheme()

    HorizontalPager(
        count = 3,
        state = pagerState,
        modifier = Modifier.fillMaxSize()
    ) { page ->
        Text(text = "Screen $page", style = MaterialTheme.typography.h1, textAlign = TextAlign.Center)
    }
}

@Composable
fun BoxScope.BottomOptionSection(
    options: List<String>,
    onOptionSelected: (Int) -> Unit,
    selectedItem: Int,
) {
    Row(
        horizontalArrangement = Arrangement.SpaceAround,
        verticalAlignment = Alignment.CenterVertically,
        modifier = Modifier
            .padding(10.dp)
            .clip(RoundedCornerShape(20.dp))
            .background(FormFieldBgDark.copy(alpha = 0.8f))
            .padding(horizontal = 7.dp, vertical = 5.dp)
            .align(Alignment.BottomEnd)
    ) {

        options.forEach { option ->
            Text(
                text = option,
                style = MaterialTheme.typography.body1,
                color = if (options.indexOf(option) == selectedItem) Color.White else Color.Gray,
                modifier = Modifier
                    .padding(5.dp)
                    .clickable { onOptionSelected(options.indexOf(option)) }
            )
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
                .size(22.dp)
                .clickable { onClose() }
        )

        Spacer(modifier = Modifier.width(25.dp))

        Text(text = "New post", style = MaterialTheme.typography.h1)
    }
}
