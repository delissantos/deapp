
package com.example.deapp

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import com.example.deapp.model.City

private fun getCities(): List<City> {
    return List(20) { i ->
        City(
            name = "Cidade $i",
            weather = "Carregando clima..."
        )
    }
}

class MainViewModel : ViewModel() {

    private val _cities = mutableStateListOf<City>().apply {
        addAll(getCities())
    }

    val cities: List<City>
        get() = _cities

    fun remove(city: City) {
        _cities.remove(city)
    }

    fun add(name: String) {
        _cities.add(
            City(
                name = name,
                weather = "Carregando clima..."
            )
        )
    }
}
