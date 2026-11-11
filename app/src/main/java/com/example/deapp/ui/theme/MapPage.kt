package com.example.deapp.ui.theme

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.sp
import com.google.android.gms.maps.model.BitmapDescriptorFactory
import com.google.maps.android.compose.Marker
import com.google.maps.android.compose.MarkerState
import androidx.compose.runtime.remember
import androidx.compose.foundation.layout.fillMaxSize
import com.google.android.gms.maps.model.LatLng

import com.google.maps.android.compose.GoogleMap
import com.google.maps.android.compose.Marker

@Composable
fun MapPage(
    modifier: Modifier = Modifier
) {
    val recife = remember {
        MarkerState(LatLng(-8.05, -34.9))
    }

    val caruaru = remember {
        MarkerState(LatLng(-8.27, -35.98))
    }

    val joaopessoa = remember {
        MarkerState(LatLng(-7.12, -34.84))
    }

    GoogleMap(
        modifier = modifier.fillMaxSize()
    ) {
        // Marcadores aqui
    }
}