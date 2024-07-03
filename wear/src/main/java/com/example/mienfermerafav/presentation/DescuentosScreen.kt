package com.example.mienfermerafav.presentation

import android.widget.Toast
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.navigation.NavController
import androidx.compose.ui.Alignment
import androidx.compose.ui.text.style.TextAlign
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import okhttp3.OkHttpClient
import okhttp3.Request
import org.json.JSONArray
import java.io.IOException

data class Producto(val nombre: String, val descuento: String?)

@Composable
fun DescuentosScreen(navController: NavController) {
    var productos by remember { mutableStateOf<List<Producto>>(emptyList()) }
    var message by remember { mutableStateOf<String?>(null) }
    val context = LocalContext.current
    val scope = rememberCoroutineScope()

    LaunchedEffect(Unit) {
        scope.launch {
            obtenerProductos(
                onSuccess = { productosList ->
                    productos = productosList.filter { it.descuento != null } // Filtrar productos con descuento válido
                    message = "Productos obtenidos exitosamente"
                },
                onError = { error ->
                    message = error
                }
            )
        }
    }

    LaunchedEffect(message) {
        message?.let {
            Toast.makeText(context, it, Toast.LENGTH_LONG).show()
        }
    }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Spacer(modifier = Modifier.height(3.dp))
        Text(
            text = "Productos",
            fontSize = 24.sp,
            color = Color.Black,
            textAlign = TextAlign.Center,
            modifier = Modifier.fillMaxWidth() // Ocupa todo el ancho disponible
        )
        Spacer(modifier = Modifier.height(2.dp))

        if (productos.isEmpty()) {
            Text(
                text = "No hay productos con descuento disponibles",
                fontSize = 15.sp,
                color = Color.Gray)
        } else {
            LazyColumn(
                modifier = Modifier.fillMaxSize(),
                verticalArrangement = Arrangement.Top,
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                items(productos) { producto ->
                    val displayText = "${producto.nombre}  ${producto.descuento}%"
                    Text(
                        text = displayText,
                        fontSize = 15.sp,
                        color = Color.Black,
                        textAlign = TextAlign.Center,
                        modifier = Modifier.fillMaxWidth() // Ocupa todo el ancho disponible
                    )
                    Spacer(modifier = Modifier.height(14.dp))
                }
            }
        }
    }
}

private suspend fun obtenerProductos(
    onSuccess: (List<Producto>) -> Unit,
    onError: (String) -> Unit
) {
    val client = OkHttpClient()
    val request = Request.Builder()
        .url("https://back-end-enfermera.vercel.app/api/productos/productos")
        .build()

    try {
        val response = withContext(Dispatchers.IO) {
            client.newCall(request).execute()
        }
        val responseData = response.body?.string()

        if (response.isSuccessful && responseData != null) {
            val productosArray = JSONArray(responseData)
            val productosList = mutableListOf<Producto>()
            for (i in 0 until productosArray.length()) {
                val producto = productosArray.getJSONObject(i)
                val nombre = producto.optString("nombre")
                val descuento = producto.optString("descuento")

                if (!nombre.isNullOrEmpty() && !descuento.isNullOrEmpty() && descuento != "null" && descuento != "0") {
                    productosList.add(Producto(nombre, descuento))
                }
            }
            onSuccess(productosList)
        } else {
            onError("Error al obtener productos: ${response.message}")
        }
    } catch (e: IOException) {
        onError("Error de red: ${e.message}")
    }
}