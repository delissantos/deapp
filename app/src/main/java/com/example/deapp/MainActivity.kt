package com.example.deapp

import android.Manifest
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.navigation.NavDestination.Companion.hasRoute
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.example.deapp.ui.theme.CityDialog
import com.example.deapp.ui.theme.DeappTheme
import com.example.deapp.ui.theme.nav.BottomNavBar
import com.example.deapp.ui.theme.nav.BottomNavItem
import com.example.deapp.ui.theme.nav.MainNavHost
import com.example.deapp.ui.theme.nav.Route

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

                // Identifica a tela atual
                val currentRoute = navController.currentBackStackEntryAsState()

                // O botão + aparece somente na tela de lista
                val showButton = currentRoute.value?.destination
                    ?.hasRoute(Route.List::class) == true

                // Solicita permissão de localização
                val launcher = rememberLauncherForActivityResult(
                    contract = ActivityResultContracts.RequestPermission(),
                    onResult = {}
                )

                LaunchedEffect(Unit) {
                    launcher.launch(Manifest.permission.ACCESS_FINE_LOCATION)
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
                        if (showButton) {
                            FloatingActionButton(
                                onClick = {
                                    showDialog = true
                                }
                            ) {
                                Icon(
                                    Icons.Default.Add,
                                    contentDescription = "Adicionar"
                                )
                            }
                        }
                    }

                ) { innerPadding ->

                    Box(
                        modifier = Modifier.padding(innerPadding)
                    ) {
                        MainNavHost(
                            navController = navController,
                            viewModel = viewModel,
                            modifier = Modifier
                        )
                    }
                }

                // Janela para adicionar uma cidade
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