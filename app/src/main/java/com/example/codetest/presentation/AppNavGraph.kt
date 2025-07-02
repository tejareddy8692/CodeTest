package com.example.codetest.presentation

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.example.codetest.domain.model.Animal
import com.example.codetest.util.Routes

@Composable
fun AppNavGraph(navController: NavHostController, viewModel: MainViewModel) {
    NavHost(navController = navController, startDestination = Routes.MAIN_SCREEN.routeName) {
        composable(Routes.MAIN_SCREEN.routeName) {
            MainScreen(viewModel = viewModel, navController = navController)
        }
        composable(Routes.DETAIL_SCREEN.routeName) { backStackEntry ->
            val animal = backStackEntry.savedStateHandle.get<Animal>("animal")
            animal?.let {
                DetailScreen(it, navController)
            }
        }
    }
}