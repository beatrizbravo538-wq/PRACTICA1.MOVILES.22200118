package edu.uesan.practica1moviles22200118.presentation.navigation


import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import edu.uesan.practica1moviles22200118.presentation.Screen.WaterCalculatorScreen
import edu.uesan.practica1moviles22200118.presentation.Screen.ActivityLogScreen
import edu.uesan.practica1moviles22200118.presentation.Screen.CarCatalogScreen
import edu.uesan.practica1moviles22200118.presentation.home.HomeScreen

@Composable
fun AppNavGraph() {
    val navController = rememberNavController()


    // NavHost es el contenedor que muestra la pantalla actual según la ruta.
    NavHost(
        navController = navController,
        startDestination = "home" // La primera pantalla que se muestra es HomeScreen.
    ) {
        // Define la ruta "home" y la enlaza con el Composable HomeScreen.
        composable(route = "home") {
            HomeScreen(navController = navController)
        }

        // Define la ruta para la pantalla de la Calculadora de Agua.
        composable(route = "water_calculator") {
            WaterCalculatorScreen(navController = navController)
        }

        // Define la ruta para la pantalla de Registro de Actividad.
        composable(route = "activity_log") {
            ActivityLogScreen(navController = navController)
        }

        // Define la ruta para la pantalla del Catálogo de Autos.
        composable(route = "car_catalog") {
            CarCatalogScreen(navController = navController)
        }
    }
}