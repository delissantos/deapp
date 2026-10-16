package com.example.deapp

import com.example.deapp.ui.theme.nav.BottomNavBar
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.navigation.compose.rememberNavController
import com.example.deapp.ui.theme.nav.BottomNavItem
import com.example.deapp.ui.theme.DeappTheme
import com.example.deapp.ui.theme.nav.MainNavHost



class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContent {
            DeappTheme {
                val navController = rememberNavController()

                Scaffold(
                    bottomBar = {
                        BottomNavBar(
                            navController = navController,
                            items = listOf(
                                BottomNavItem.HomeButton,
                                BottomNavItem.ListButton,
                                BottomNavItem.MapButton
                            )
                        )
                    }
                ) { innerPadding ->
                    MainNavHost(
                        navController = navController,
                        modifier = androidx.compose.ui.Modifier
                            .padding(innerPadding)
                    )
                }
            }
        }
    }
}
