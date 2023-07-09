package com.example.ezo

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.example.ezo.presentation.utils.BottonNavItem
import com.example.ezo.presentation.api.ApiScreen
import com.example.ezo.presentation.atm.AtmScreen

@Composable
fun NavGraph(navController : NavHostController) {
    NavHost(navController = navController, startDestination = BottonNavItem.ApiScreen.route){
        composable(BottonNavItem.ApiScreen.route){
            ApiScreen()
        }
        composable(BottonNavItem.AtmScreen.route){
            AtmScreen()
        }
    }
}