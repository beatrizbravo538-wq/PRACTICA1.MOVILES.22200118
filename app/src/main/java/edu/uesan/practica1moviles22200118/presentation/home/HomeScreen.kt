package edu.uesan.practica1moviles22200118.presentation.home

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController

@Composable
fun HomeScreen(navController: NavController) {
    // Column organizes the buttons vertically and centers them on the screen.
    Column(
        modifier = Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        // Button for the Water Consumption Calculator
        Button(onClick = { navController.navigate("water_calculator") }) {
            Text("Calculadora de consumo de agua")
        }

        Spacer(modifier = Modifier.height(16.dp)) // Space between buttons

        // Button for the Physical Activity Log
        Button(onClick = { navController.navigate("activity_log") }) {
            Text("Registro de actividad física")
        }

        Spacer(modifier = Modifier.height(16.dp)) // Space between buttons

        // Button for the Sports Car Catalog
        Button(onClick = { navController.navigate("car_catalog") }) {
            Text("Catálogo de Autos deportivos")
        }
    }
}