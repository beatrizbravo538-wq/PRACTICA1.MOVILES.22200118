package edu.uesan.practica1moviles22200118

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import edu.uesan.practica1moviles22200118.presentation.navigation.AppNavGraph
import edu.uesan.practica1moviles22200118.ui.theme.PRACTICA1MOVILES22200118Theme // Make sure the theme name is correct

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        // Allows the app to use the full screen for a more modern look.
        enableEdgeToEdge()
        setContent {
            // Applies your app's visual theme (colors, fonts, etc.).
            PRACTICA1MOVILES22200118Theme {
                // Starts the navigation system, which will display the main screen.
                AppNavGraph()
            }
        }
    }
}