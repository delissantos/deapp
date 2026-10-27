
package com.example.deapp.ui.theme.nav

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.example.deapp.MainViewModel
import com.example.deapp.ui.theme.HomePage
import com.example.deapp.ui.theme.ListPage
import com.example.deapp.ui.theme.MapPage

@Composable
fun MainNavHost(
    navController: NavHostController,
    viewModel: MainViewModel,
    modifier: Modifier = Modifier
) {
    NavHost(
        navController = navController,
        startDestination = Route.Home,
        modifier = modifier
    ) {
        composable<Route.Home> {
            HomePage(modifier = Modifier)
        }

        composable<Route.List> {
            ListPage(viewModel = viewModel)
        }

        composable<Route.Map> {
            MapPage(modifier = Modifier)
        }
    }
}
