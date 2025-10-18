package edu.uesan.practica1moviles22200118.presentation.Screen



import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import coil.compose.rememberAsyncImagePainter
import edu.uesan.practica1moviles22200118.data.Model.Car // Asegúrate de que la ruta a tu data class sea correcta.
import java.text.DecimalFormat

// 1. Lista de ejemplo de autos deportivos (Mock Data).
//    Utiliza el data class "Car" que creaste.
val sampleCars = listOf(
        Car("Ferrari", "SF90 Stradale", 507000.00, "https://images.unsplash.com/photo-1583121274602-3e2820c69888?ixlib=rb-4.1.0&ixid=M3wxMjA3fDB8MHxzZWFyY2h8Mnx8ZmVycmFyaXxlbnwwfHwwfHx8MA%3D%3D&fm=jpg&q=60&w=3000"),
        Car("Lamborghini", "Huracan EVO", 261274.00, "https://images.pexels.com/photos/6462662/pexels-photo-6462662.png?cs=srgb&dl=pexels-broderick-armbrister-1186919-6462662.jpg&fm=jpg"),
        Car("Porsche", "911 GT3 RS", 241300.00, "https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcQYPqrztKXrM4D9OOI2VywDoUGOd-VBDTx38g&s"),
        Car("McLaren", "720S", 310500.00, "https://hips.hearstapps.com/hmg-prod/images/mclaren-750s-coupe-1-6447ba5ef1f0d.jpg?crop=0.8896296296296295xw:1xh;center,top&resize=1200:*"),
        Car("Bugatti", "Chiron", 3300000.00, "https://www.shutterstock.com/image-photo/szczecinpolandjuly-2024bugatti-tourbillon-v16-1800-600nw-2492193479.jpg")
    )

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CarCatalogScreen(navController: NavController) {
    // 2. Calcula el precio total de los autos en la lista.
    val totalAmount = sampleCars.sumOf { it.price }
    val decimalFormat = DecimalFormat("$,###.##")

    // 3. Scaffold proporciona una estructura de pantalla básica.
    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("Catálogo de Autos Deportivos") }
            )
        },
        bottomBar = {
            // La barra inferior muestra el costo total.
            BottomAppBar {
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(horizontal = 16.dp),
                    horizontalArrangement = Arrangement.SpaceBetween
                ) {
                    Text("Costo Total:", style = MaterialTheme.typography.titleMedium)
                    Text(decimalFormat.format(totalAmount), style = MaterialTheme.typography.titleMedium, fontWeight = FontWeight.Bold)
                }
            }
        }
    ) { innerPadding ->
        // 4. LazyColumn crea una lista desplazable y eficiente.
        LazyColumn(
            modifier = Modifier
                .fillMaxSize()
                .padding(innerPadding)
                .padding(16.dp),
            verticalArrangement = Arrangement.spacedBy(16.dp)
        ) {
            // Crea una tarjeta para cada auto en la lista.
            items(sampleCars) { car ->
                CarCard(car = car)
            }

            // Agrega el botón de regreso al final de la lista.
            item {
                Spacer(modifier = Modifier.height(16.dp))
                Button(
                    onClick = { navController.popBackStack() },
                    modifier = Modifier.fillMaxWidth()
                ) {
                    Text("Regresar al Menú")
                }
            }
        }
    }
}

// 5. Composable separado para diseñar la tarjeta de un solo auto.
@Composable
fun CarCard(car: Car) {
    Card(
        modifier = Modifier.fillMaxWidth(),
        elevation = CardDefaults.cardElevation(defaultElevation = 4.dp)
    ) {
        Column {
            // La imagen se carga desde una URL de internet.
            Image(
                painter = rememberAsyncImagePainter(car.imageUrl),
                contentDescription = car.model,
                modifier = Modifier
                    .fillMaxWidth()
                    .height(200.dp),
                contentScale = ContentScale.Crop
            )
            Column(modifier = Modifier.padding(16.dp)) {
                Text(car.brand, style = MaterialTheme.typography.titleLarge) // Marca 
                Text(car.model, style = MaterialTheme.typography.bodyMedium) // Modelo 
                Text(DecimalFormat("$,###.##").format(car.price), style = MaterialTheme.typography.bodyLarge, fontWeight = FontWeight.Bold) // Precio 
            }
        }
    }
}