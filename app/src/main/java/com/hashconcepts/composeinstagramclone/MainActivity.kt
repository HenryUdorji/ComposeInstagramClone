package com.hashconcepts.composeinstagramclone

import android.annotation.SuppressLint
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Scaffold
import androidx.compose.material.Surface
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.hashconcepts.composeinstagramclone.common.components.CustomBottomNavBar
import com.hashconcepts.composeinstagramclone.common.data.SharedPrefUtil
import com.hashconcepts.composeinstagramclone.common.utils.Constants
import com.hashconcepts.composeinstagramclone.destinations.*
import com.hashconcepts.composeinstagramclone.ui.theme.ComposeInstagramCloneTheme
import com.ramcosta.composedestinations.DestinationsNavHost
import com.ramcosta.composedestinations.rememberNavHostEngine
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    @Inject
    lateinit var sharedPrefUtil: SharedPrefUtil

    @SuppressLint("UnusedMaterialScaffoldPaddingParameter")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            val navController = rememberNavController()
            val navHostEngine = rememberNavHostEngine()
            val newBackStackEntry by navController.currentBackStackEntryAsState()
            val route = newBackStackEntry?.destination?.route

            val showBottomBar = route in listOf(
                HomeScreenDestination.route,
                PostScreenDestination.route,
                ProfileScreenDestination.route,
                SearchScreenDestination.route,
                ActivityScreenDestination.route
            )

            ComposeInstagramCloneTheme {
                // A surface container using the 'background' color from the theme
                Scaffold(
                    modifier = Modifier.fillMaxSize(),
                    backgroundColor = MaterialTheme.colors.background,
                    bottomBar = {
                        if (showBottomBar) {
                            CustomBottomNavBar(
                                profileImage = sharedPrefUtil.getString(Constants.PROFILE_IMAGE_URL)!!,
                                navController = navController
                            )
                        }
                    }
                ) {
                    DestinationsNavHost(
                        navGraph = NavGraphs.root,
                        navController = navController,
                        engine = navHostEngine
                    )
                }
            }
        }
    }
}
