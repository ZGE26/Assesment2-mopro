package com.example.assesment2.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.assesment2.ui.screen.MainScreen

@Composable
fun SetUpGraph(
    navController: NavHostController = rememberNavController()
) {
    NavHost(
        navController = navController,
        startDestination = MainScreen
    ) {
        composable<MainScreen>{
            MainScreen()
        }
    }
}