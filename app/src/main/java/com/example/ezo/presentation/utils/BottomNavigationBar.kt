package com.example.ezo.presentation.utils

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AccountBox
import androidx.compose.material.icons.filled.Home
import androidx.compose.material3.Icon
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.navigation.compose.currentBackStackEntryAsState

@Composable
fun BottomNavigationBar(
    navController: NavController
) {
    val items = listOf(
        BottonNavItem.ApiScreen,
        BottonNavItem.AtmScreen,
    )

    NavigationBar(
        containerColor = Color.Gray,
        contentColor = Color.Black
    ) {
        val navBackStackEntry by navController.currentBackStackEntryAsState()
        val currentRoute = navBackStackEntry?.destination?.route

        items.forEach {item->
            NavigationBarItem(
                label = { Text(text = item.title, fontSize = 10.sp) },
                selected = currentRoute == item.route,
                icon = {
                    Icon(imageVector = item.icon , contentDescription = "")
                       },
                onClick = {
                    navController.navigate(item.route){
                        navController.graph.startDestinationRoute?.let{
                            popUpTo(it){
                                saveState = true
                            }
                        }
                        launchSingleTop = true
                        restoreState = true
                    }
                }
            )
        }
    }

}


sealed class BottonNavItem(
    var title : String,
    var route : String,
    var icon : ImageVector
){
    object ApiScreen : BottonNavItem("API","api", Icons.Filled.Home)
    object AtmScreen : BottonNavItem("ATM","atm",Icons.Filled.AccountBox)
}