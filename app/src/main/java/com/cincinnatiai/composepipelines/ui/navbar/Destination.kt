package com.cincinnatiai.composepipelines.ui.navbar

import androidx.compose.material.Icon
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.Home
import androidx.compose.material.icons.outlined.Person
import androidx.compose.material.icons.outlined.Settings
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color

sealed class Destination(val route: String, val title: String, val icon: @Composable () -> Unit) {
    object Home : Destination(
        "home",
        "Home",
        {
            Icon(
                imageVector = Icons.Outlined.Home,
                contentDescription = "Home",
                tint = Color.Black
            )
        })

    object Campaigns : Destination(
        "campaigns",
        "Campaigns",
        { Icon(Icons.Outlined.Person, contentDescription = "Campaigns", tint = Color.Black) })

    object Profile : Destination(
        "profile",
        "Profile",
        { Icon(Icons.Outlined.Person, contentDescription = "Profile", tint = Color.Black) })

    object Settings : Destination(
        "settings",
        "Settings",
        { Icon(Icons.Outlined.Settings, contentDescription = "Settings", tint = Color.Black) })

    object PipelineDetails : Destination("pipelinedetails", "Pipeline Details" , {

    })

    companion object {
        fun getDestinationFromRoute(route: String?): Destination =
            when (route) {
                "home" -> Home
                "campaigns" -> Campaigns
                "profile" -> Profile
                "settings" -> Settings
                else -> Home
            }
    }
}