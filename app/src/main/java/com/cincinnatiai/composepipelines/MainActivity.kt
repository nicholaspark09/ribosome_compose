package com.cincinnatiai.composepipelines

import android.annotation.SuppressLint
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.cincinnatiai.composepipelines.ui.home.HomeScreen
import com.cincinnatiai.composepipelines.ui.navbar.BottomNavigationBar
import com.cincinnatiai.composepipelines.ui.navbar.Destination
import com.cincinnatiai.composepipelines.ui.navbar.Destination.Companion.getDestinationFromRoute
import com.cincinnatiai.composepipelines.ui.pipelinedetails.PipelineDetailsScreen
import com.cincinnatiai.composepipelines.ui.theme.ComposepipelinesTheme

class MainActivity : ComponentActivity() {


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            ComposepipelinesTheme {
                MainScreen()
            }
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
fun MainScreen() {
    val navController = rememberNavController()
    var title by remember {
        mutableStateOf("")
    }
    LaunchedEffect(key1 = navController.currentBackStackEntry) {
        title =
            getDestinationFromRoute(navController.currentBackStackEntry?.destination?.route).title
    }
    Scaffold(
        bottomBar = { BottomNavigationBar(navController = navController) }
    ) {
        MainNavigationHost(navController = navController)
    }
}

@Composable
fun MainNavigationHost(navController: NavHostController) {
    NavHost(navController = navController, startDestination = Destination.Home.route) {
        composable(Destination.Home.route) { HomeScreen(navController = navController) }
        composable(Destination.PipelineDetails.route) { PipelineDetailsScreen(navController) }
    }
}