package edu.uesan.practica1moviles22200118.presentation.Screen

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.selection.selectable
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import java.text.DecimalFormat

@OptIn(ExperimentalMaterial3Api::class)

@Composable
fun ActivityLogScreen(navController: NavController) {
    // --- Estados para guardar los datos del usuario ---
    var isActivityDropdownExpanded by remember { mutableStateOf(false) }
    var selectedActivity by remember { mutableStateOf("Correr") }
    var duration by remember { mutableStateOf("") }
    var selectedIntensity by remember { mutableStateOf("Media") }
    var resultMessage by remember { mutableStateOf("") }
    var errorMessage by remember { mutableStateOf("") }

    val activityOptions = listOf("Correr", "Caminar", "Nadar", "Ciclismo", "Yoga")
    val intensityOptions = listOf("Baja", "Media", "Alta")

    // --- Diseño de la interfaz ---
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Text("Registro de Actividad Física", style = MaterialTheme.typography.headlineMedium)
        Spacer(modifier = Modifier.height(24.dp))

        // Menú desplegable para el tipo de actividad
        ExposedDropdownMenuBox(
            expanded = isActivityDropdownExpanded,
            onExpandedChange = { isActivityDropdownExpanded = !isActivityDropdownExpanded }
        ) {
            OutlinedTextField(
                value = selectedActivity,
                onValueChange = {},
                readOnly = true,
                label = { Text("Tipo de actividad") },
                trailingIcon = { ExposedDropdownMenuDefaults.TrailingIcon(expanded = isActivityDropdownExpanded) },
                modifier = Modifier.menuAnchor()
            )
            ExposedDropdownMenu(
                expanded = isActivityDropdownExpanded,
                onDismissRequest = { isActivityDropdownExpanded = false }
            ) {
                activityOptions.forEach { activity ->
                    DropdownMenuItem(
                        text = { Text(activity) },
                        onClick = {
                            selectedActivity = activity
                            isActivityDropdownExpanded = false
                        }
                    )
                }
            }
        }
        Spacer(modifier = Modifier.height(16.dp))

        // Campo para la duración
        OutlinedTextField(
            value = duration,
            onValueChange = { duration = it },
            label = { Text("Duración (en minutos)") },
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
            singleLine = true
        )
        Spacer(modifier = Modifier.height(16.dp))

        // RadioButtons para la intensidad
        Column {
            Text("Intensidad", style = MaterialTheme.typography.bodyLarge)
            intensityOptions.forEach { intensity ->
                Row(
                    Modifier
                        .fillMaxWidth()
                        .selectable(
                            selected = (intensity == selectedIntensity),
                            onClick = { selectedIntensity = intensity }
                        ),
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    RadioButton(
                        selected = (intensity == selectedIntensity),
                        onClick = null
                    )
                    Spacer(modifier = Modifier.width(8.dp))
                    Text(text = intensity)
                }
            }
        }
        Spacer(modifier = Modifier.height(24.dp))

        // Botón para calcular
        Button(onClick = {
            val durationInt = duration.toIntOrNull()
            // Validaciones
            when {
                duration.isBlank() -> errorMessage = "Todos los campos son obligatorios."
                durationInt == null || durationInt <= 0 -> errorMessage = "La duración debe ser un número entero positivo."
                else -> {
                    // Si todo es válido, se procede con el cálculo
                    errorMessage = ""
                    val caloriesPerMinute = when (selectedActivity) {
                        "Correr" -> 10
                        "Caminar" -> 5
                        "Nadar" -> 8
                        "Ciclismo" -> 7
                        else -> 4 // Yoga
                    }
                    val intensityFactor = when (selectedIntensity) {
                        "Baja" -> 0.8
                        "Media" -> 1.0
                        else -> 1.2 // Alta
                    }

                    // Fórmula: calorías quemadas = calorías por minuto * duración * factor por intensidad
                    val caloriesBurned = caloriesPerMinute * durationInt * intensityFactor
                    val df = DecimalFormat("#.#")
                    resultMessage = "Calorías quemadas: ${df.format(caloriesBurned)} kcal"
                }
            }
        }) {
            Text("Calcular Calorías")
        }
        Spacer(modifier = Modifier.height(24.dp))

        // Muestra el mensaje de error o el resultado
        if (errorMessage.isNotEmpty()) {
            Text(errorMessage, color = MaterialTheme.colorScheme.error)
        } else if (resultMessage.isNotEmpty()) {
            Text(resultMessage, style = MaterialTheme.typography.titleLarge)
        }

        Spacer(modifier = Modifier.height(16.dp))

        // Botón para regresar al menú principal
        Button(onClick = { navController.popBackStack() }) {
            Text("Regresar al Menú")
        }
    }
}
