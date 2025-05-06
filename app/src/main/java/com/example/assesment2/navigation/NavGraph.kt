package com.example.assesment2.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.example.assesment2.ui.screen.KEY_ID_TASK
import com.example.assesment2.ui.screen.ListTask
import com.example.assesment2.ui.screen.MainScreen

@Composable
fun SetUpGraph(
    navController: NavHostController = rememberNavController()
) {
    NavHost(
        navController = navController,
        startDestination = Screen.Home.route
    ) {
        composable(Screen.Home.route){
            MainScreen(navController)
        }
        composable(
            route = Screen.ListTask.route,
            arguments = listOf(
                navArgument(KEY_ID_TASK) { type = NavType.LongType }
            )
        ) { navBackStackEntry ->
            val id = navBackStackEntry.arguments?.getLong(KEY_ID_TASK)
            if (id != null) {
                ListTask(navController, id)
            }
        }
    }
}