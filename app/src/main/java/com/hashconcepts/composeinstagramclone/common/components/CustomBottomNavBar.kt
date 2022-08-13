package com.hashconcepts.composeinstagramclone.common.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.border
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.BottomNavigation
import androidx.compose.material.BottomNavigationItem
import androidx.compose.material.Divider
import androidx.compose.material.Icon
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.navigation.compose.currentBackStackEntryAsState
import coil.compose.AsyncImage
import com.hashconcepts.composeinstagramclone.R
import com.hashconcepts.composeinstagramclone.destinations.*
import com.hashconcepts.composeinstagramclone.ui.theme.FormFieldBgDark
import com.hashconcepts.composeinstagramclone.ui.theme.FormFieldBgLight
import com.hashconcepts.composeinstagramclone.ui.theme.IconDark
import com.hashconcepts.composeinstagramclone.ui.theme.IconLight

/**
 * @created 04/08/2022 - 10:50 PM
 * @project ComposeInstagramClone
 * @author  ifechukwu.udorji
 */
sealed class BottomNavItem(
    val iconRes: Int,
    val filledIconRes: Int,
    val destination: DirectionDestination
) {
    object HomeScreen :
        BottomNavItem(R.drawable.ic_home, R.drawable.ic_home_filled, HomeScreenDestination)

    object SearchScreen :
        BottomNavItem(R.drawable.ic_search, R.drawable.ic_search_filled, SearchScreenDestination)

    object ReelsScreen :
        BottomNavItem(R.drawable.ic_reels, R.drawable.ic_reels_filled, ReelsScreenDestination)

    object ActivityScreen : BottomNavItem(
        R.drawable.ic_activity,
        R.drawable.ic_activity_filled,
        ActivityScreenDestination
    )

    object ProfileScreen : BottomNavItem(0, 0, ProfileScreenDestination)
}

val bottomNavItems = listOf(
    BottomNavItem.HomeScreen,
    BottomNavItem.SearchScreen,
    BottomNavItem.ReelsScreen,
    BottomNavItem.ActivityScreen,
    BottomNavItem.ProfileScreen,
)

@Composable
fun CustomBottomNavBar(
    profileImage: String,
    navController: NavController,
    navItems: List<BottomNavItem> = bottomNavItems,
) {
    val darkTheme: Boolean = isSystemInDarkTheme()
    val navBackStackEntry by navController.currentBackStackEntryAsState()
    val currentRoute = navBackStackEntry?.destination?.route

    BottomNavigation(
        backgroundColor = if (darkTheme) Color.Black else Color.White,
        elevation = 5.dp
    ) {
        navItems.forEach { item ->
            val selectedNavItem = currentRoute?.contains(item.destination.route) == true

            BottomNavigationItem(
                selected = selectedNavItem,
                selectedContentColor = if (darkTheme) IconDark else IconLight,
                alwaysShowLabel = false,
                icon = {
                    if (item.destination == ProfileScreenDestination) {
                        AsyncImage(
                            model = profileImage,
                            contentDescription = null,
                            modifier = Modifier
                                .clip(CircleShape)
                                .border(
                                    width = 1.dp,
                                    color = if (darkTheme) IconDark else IconLight,
                                    shape = CircleShape
                                )
                                .size(30.dp),
                            error = painterResource(id = R.drawable.profile_placeholder),
                            fallback = painterResource(id = R.drawable.profile_placeholder),
                            placeholder = painterResource(id = R.drawable.profile_placeholder),
                            contentScale = ContentScale.Crop
                        )
                    } else {
                        Icon(
                            painter = if (selectedNavItem) painterResource(id = item.filledIconRes) else painterResource(
                                id = item.iconRes
                            ),
                            contentDescription = null
                        )
                    }
                },
                onClick = {
                    if (!selectedNavItem) {
                        navController.popBackStack()
                        navController.navigate(item.destination.route) {
                            navController.graph.startDestinationRoute?.let { route ->
                                popUpTo(route) {
                                    saveState = true
                                }
                            }
                            launchSingleTop = true
                            restoreState = true
                        }
                    }
                }
            )
        }
    }
}