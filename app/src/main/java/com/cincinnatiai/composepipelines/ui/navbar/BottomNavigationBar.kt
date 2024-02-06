package com.cincinnatiai.composepipelines.ui.navbar

import androidx.compose.material.BottomNavigation
import androidx.compose.material.BottomNavigationItem
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.navigation.NavController
import androidx.navigation.compose.currentBackStackEntryAsState

@Composable
fun BottomNavigationBar(navController: NavController) {
    val destinations = remember {
        listOf(
            Destination.Home,
            Destination.Campaigns,
            Destination.Profile,
            Destination.Settings
        )
    }

    BottomNavigation {
        val navBackStackEntry by navController.currentBackStackEntryAsState()
        val currentRoute = navBackStackEntry?.destination?.route

        destinations.forEach { destination ->
            BottomNavigationItem(selected = destination.route == currentRoute,
                onClick = {
                    navController.navigate(destination.route)
                },
                label = { Text(destination.title) },
                icon = { destination.icon })
        }
    }
}