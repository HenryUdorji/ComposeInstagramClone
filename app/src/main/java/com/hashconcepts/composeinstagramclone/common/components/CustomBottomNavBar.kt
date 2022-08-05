package com.hashconcepts.composeinstagramclone.common.components

import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material.BottomNavigation
import androidx.compose.material.BottomNavigationItem
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.navigation.compose.currentBackStackEntryAsState
import com.hashconcepts.composeinstagramclone.R
import com.hashconcepts.composeinstagramclone.destinations.*
import com.hashconcepts.composeinstagramclone.ui.theme.FormFieldBgDark
import com.hashconcepts.composeinstagramclone.ui.theme.FormFieldBgLight

/**
 * @created 04/08/2022 - 10:50 PM
 * @project ComposeInstagramClone
 * @author  ifechukwu.udorji
 */
sealed class BottomNavItem(val iconRes: Int, val destination: DirectionDestination) {
    object HomeScreen: BottomNavItem(R.drawable.ic_home, HomeScreenDestination)
    object SearchScreen: BottomNavItem(R.drawable.ic_search, SearchScreenDestination)
    object PostScreen: BottomNavItem(R.drawable.ic_post, PostScreenDestination)
    object ActivityScreen: BottomNavItem(R.drawable.ic_activity, ActivityScreenDestination)
    object ProfileScreen: BottomNavItem(0, ProfileScreenDestination)
}

val bottomNavItems = listOf(
    BottomNavItem.HomeScreen,
    BottomNavItem.SearchScreen,
    BottomNavItem.PostScreen,
    BottomNavItem.ActivityScreen,
    BottomNavItem.ProfileScreen,
)

@Composable
fun CustomBottomNavBar(
    modifier: Modifier = Modifier,
    profileImage: String,
    navController: NavController,
    navItems: List<BottomNavItem> = bottomNavItems,
) {
    val darkTheme: Boolean = isSystemInDarkTheme()
    val navBackStackEntry by navController.currentBackStackEntryAsState()
    val currentRoute = navBackStackEntry?.destination?.route
    
    BottomNavigation(
        backgroundColor = if (darkTheme) FormFieldBgDark else FormFieldBgLight,
        elevation = 5.dp
    ) {
        navItems.forEach { item ->
            val selectedNavItem = currentRoute?.contains(item.destination.route) == true
        }
        
    }
}