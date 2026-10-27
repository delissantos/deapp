
package com.example.deapp.ui.theme

import android.widget.Toast
import androidx.activity.compose.LocalActivity
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Close
import androidx.compose.material.icons.rounded.FavoriteBorder
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.deapp.MainViewModel
import com.example.deapp.model.City

@Composable
fun CityItem(
    city: City,
    onClick: () -> Unit,
    onClose: () -> Unit,
    modifier: Modifier = Modifier
) {
    Row(
        modifier = modifier
            .fillMaxWidth()
            .padding(8.dp)
            .clickable { onClick() },
        verticalAlignment = Alignment.CenterVertically
    ) {
        Icon(
            imageVector = Icons.Rounded.FavoriteBorder,
            contentDescription = "Favorito",
            tint = Color.White
        )

        Spacer(modifier = Modifier.size(12.dp))

        Column(modifier = Modifier.weight(1f)) {
            Text(
                text = city.name,
                fontSize = 24.sp,
                color = Color.White
            )

            Text(
                text = city.weather ?: "Carregando clima...",
                fontSize = 16.sp,
                color = Color.White
            )
        }

        IconButton(onClick = onClose) {
            Icon(
                imageVector = Icons.Filled.Close,
                contentDescription = "Excluir cidade",
                tint = Color.White
            )
        }
    }
}

@Composable
fun ListPage(
    viewModel: MainViewModel,
    modifier: Modifier = Modifier
) {
    val cityList = viewModel.cities
    val activity = LocalActivity.current

    LazyColumn(
        modifier = modifier
            .fillMaxSize()
            .background(Color.Magenta)
            .padding(8.dp)
    ) {
        items(
            items = cityList,
            key = { city -> city.name }
        ) { city ->
            CityItem(
                city = city,
                onClick = {
                    Toast.makeText(
                        activity,
                        "Você clicou em ${city.name}",
                        Toast.LENGTH_SHORT
                    ).show()
                },
                onClose = {
                    viewModel.remove(city)
                }
            )
        }
    }
}
