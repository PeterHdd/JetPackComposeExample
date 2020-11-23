package com.example.jetpackcomposeexample

import androidx.compose.runtime.Composable
import androidx.compose.ui.focus.ExperimentalFocus
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.jetpackcomposeexample.common.Constants
import com.example.jetpackcomposeexample.presentation.viewmodels.PostsViewModel
import com.example.jetpackcomposeexample.screens.HomeScreen
import com.example.jetpackcomposeexample.screens.addPostScreen
import com.example.jetpackcomposeexample.theme.JetPackComposeExampleTheme

@ExperimentalFocus
@Composable
fun toNav(viewModel: PostsViewModel, mainActivity: MainActivity) {
    JetPackComposeExampleTheme {
        val navController: NavHostController = rememberNavController()
        NavHost(navController, startDestination = Constants.HOME_SCREEN)
        {
            composable(Constants.HOME_SCREEN)
            {
                HomeScreen(viewModel, navController)
            }
            composable(Constants.ADD_POST)
            {
                addPostScreen(viewModel, navController, mainActivity)
            }
        }
    }
}