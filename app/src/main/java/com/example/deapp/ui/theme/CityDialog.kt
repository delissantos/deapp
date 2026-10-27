
package com.example.deapp.ui.theme

import androidx.compose.material3.AlertDialog
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.getValue
import androidx.compose.runtime.setValue

@Composable
fun CityDialog(
    onDismiss: () -> Unit,
    onConfirm: (String) -> Unit
) {
    var cityName by remember { mutableStateOf("") }

    AlertDialog(
        onDismissRequest = onDismiss,
        title = {
            Text("Adicionar cidade")
        },
        text = {
            OutlinedTextField(
                value = cityName,
                onValueChange = { cityName = it },
                label = { Text("Nome da cidade") },
                singleLine = true
            )
        },
        confirmButton = {
            TextButton(
                onClick = {
                    val name = cityName.trim()
                    if (name.isNotEmpty()) {
                        onConfirm(name)
                    }
                },
                enabled = cityName.trim().isNotEmpty()
            ) {
                Text("Adicionar")
            }
        },
        dismissButton = {
            TextButton(onClick = onDismiss) {
                Text("Cancelar")
            }
        }
    )
}
