package edu.uesan.practica1moviles22200118.presentation.Screen
import androidx.compose.foundation.layout.*
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
fun WaterCalculatorScreen(navController: NavController) {
    // --- Estados para guardar los datos del usuario ---
    var name by remember { mutableStateOf("") }
    var weight by remember { mutableStateOf("") }
    var isDropdownExpanded by remember { mutableStateOf(false) }
    var selectedGender by remember { mutableStateOf("Masculino") }
    var resultMessage by remember { mutableStateOf("") }
    var errorMessage by remember { mutableStateOf("") }
    val genderOptions = listOf("Masculino", "Femenino", "Sin especificar")

    // --- Diseño de la interfaz ---
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Text("Calculadora de Consumo de Agua", style = MaterialTheme.typography.headlineMedium)
        Spacer(modifier = Modifier.height(24.dp))

        // Campo para el nombre de la persona [cite: 24]
        OutlinedTextField(
            value = name,
            onValueChange = { name = it },
            label = { Text("Nombre de la persona") },
            singleLine = true
        )
        Spacer(modifier = Modifier.height(16.dp))

        // Campo para el peso corporal [cite: 25]
        OutlinedTextField(
            value = weight,
            onValueChange = { weight = it },
            label = { Text("Peso corporal (en kg)") },
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
            singleLine = true
        )
        Spacer(modifier = Modifier.height(16.dp))

        // Menú desplegable para el género [cite: 26, 27]
        ExposedDropdownMenuBox(
            expanded = isDropdownExpanded,
            onExpandedChange = { isDropdownExpanded = !isDropdownExpanded }
        ) {
            OutlinedTextField(
                value = selectedGender,
                onValueChange = {},
                readOnly = true,
                label = { Text("Género") },
                trailingIcon = { ExposedDropdownMenuDefaults.TrailingIcon(expanded = isDropdownExpanded) },
                modifier = Modifier.menuAnchor()
            )
            ExposedDropdownMenu(
                expanded = isDropdownExpanded,
                onDismissRequest = { isDropdownExpanded = false }
            ) {
                genderOptions.forEach { gender ->
                    DropdownMenuItem(
                        text = { Text(gender) },
                        onClick = {
                            selectedGender = gender
                            isDropdownExpanded = false
                        }
                    )
                }
            }
        }
        Spacer(modifier = Modifier.height(24.dp))

        // Botón para ejecutar el cálculo
        Button(onClick = {
            val weightDouble = weight.toDoubleOrNull()
            // Validaciones [cite: 35, 36]
            when {
                name.isBlank() -> errorMessage = "El nombre es obligatorio."
                weightDouble == null -> errorMessage = "El peso debe ser un número."
                weightDouble !in 5.0..200.0 -> errorMessage = "El peso debe estar entre 5 y 200 kg."
                else -> {
                    // Si todo es válido, se procede con el cálculo
                    errorMessage = ""
                    val genderFactor = when (selectedGender) {
                        "Masculino" -> 1.02
                        "Femenino" -> 1.01
                        else -> 1.00
                    }
                    val recommendedLitres = weightDouble * 0.035 * genderFactor
                    val df = DecimalFormat("#.##")
                    resultMessage = "$name debe beber aproximadamente ${df.format(recommendedLitres)} litros de agua al día."
                }
            }
        }) {
            Text("Calcular")
        }
        Spacer(modifier = Modifier.height(24.dp))

        // Muestra el mensaje de error o el resultado
        if (errorMessage.isNotEmpty()) {
            Text(errorMessage, color = MaterialTheme.colorScheme.error)
        } else if (resultMessage.isNotEmpty()) {
            Text(resultMessage, style = MaterialTheme.typography.titleLarge)
        }

        Spacer(modifier = Modifier.height(16.dp))

        // Botón para regresar al menú principal [cite: 69]
        Button(onClick = { navController.popBackStack() }) {
            Text("Regresar al Menú")
        }
    }
}