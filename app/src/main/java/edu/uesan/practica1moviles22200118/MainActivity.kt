package edu.uesan.practica1moviles22200118

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import edu.uesan.practica1moviles22200118.presentation.navigation.AppNavGraph
import edu.uesan.practica1moviles22200118.ui.theme.PRACTICA1MOVILES22200118Theme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            PRACTICA1MOVILES22200118Theme {
                // Navegación entre pantallas
                AppNavGraph()
            }
        }
    }
}