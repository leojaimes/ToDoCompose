package com.example.to_docompose.navigation

import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import com.example.to_docompose.navigation.destinations.listComposable
import com.example.to_docompose.navigation.destinations.taskComposable
import com.example.to_docompose.ui.viewmodels.SharedViewModel
import com.example.to_docompose.util.Constants.LIST_SCREEN


@ExperimentalMaterialApi
@Composable
fun SetupNavigation(
    navHostController: NavHostController,
    sharedViewModel: SharedViewModel
) {
    val screen = remember(navHostController) {
        Screens(navHostController)
    }

    NavHost(navController = navHostController, startDestination = LIST_SCREEN) {
        listComposable(
            navigateToTaskScreen = screen.task,
            sharedViewModel = sharedViewModel
        )

        taskComposable(
            sharedViewModel= sharedViewModel,
            navigateToListScreen = screen.list)

    }
}