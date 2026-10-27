
package com.example.deapp

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.navigation.compose.rememberNavController
import com.example.deapp.ui.theme.CityDialog
import com.example.deapp.ui.theme.DeappTheme
import com.example.deapp.ui.theme.nav.BottomNavBar
import com.example.deapp.ui.theme.nav.BottomNavItem
import com.example.deapp.ui.theme.nav.MainNavHost

class MainActivity : ComponentActivity() {

    private val viewModel: MainViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContent {
            DeappTheme {
                val navController = rememberNavController()
                var showDialog by remember {
                    mutableStateOf(false)
                }

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
                    },
                    floatingActionButton = {
                        FloatingActionButton(
                            onClick = {
                                showDialog = true
                            }
                        ) {
                            Icon(
                                imageVector = Icons.Default.Add,
                                contentDescription = "Adicionar cidade"
                            )
                        }
                    }
                ) { innerPadding ->
                    MainNavHost(
                        navController = navController,
                        viewModel = viewModel,
                        modifier = Modifier.padding(innerPadding)
                    )
                }

                if (showDialog) {
                    CityDialog(
                        onDismiss = {
                            showDialog = false
                        },
                        onConfirm = { name ->
                            viewModel.add(name)
                            showDialog = false
                        }
                    )
                }
            }
        }
    }
}
