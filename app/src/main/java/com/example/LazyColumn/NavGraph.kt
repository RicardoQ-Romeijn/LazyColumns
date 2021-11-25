package com.example.navigationcomposercondatos

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.navArgument

@Composable
fun SetUpNavGraph(navController: NavHostController) {
    NavHost(
        navController = navController,
        startDestination = Screen.Home.route

    ) {
        composable(
            Screen.Home.route
        ) {
            HomeScreen(navController = navController)
        }
        composable(
            Screen.Second.route,
            arguments = listOf(navArgument("id"){
                type=NavType.IntType
            },
            navArgument("name"){
                type= NavType.StringType
            })
        ) { navBackStackEntry ->
            SecondScreen(navBackStackEntry.arguments?.getInt("id")!!,navBackStackEntry.arguments?.getString("name")!!)
        }
    }
}