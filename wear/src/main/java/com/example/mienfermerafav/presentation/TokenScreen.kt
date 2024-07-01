package com.example.mienfermerafav.presentation

import android.content.Context
import android.widget.Toast
import androidx.compose.foundation.layout.*
import androidx.compose.material.Button
import androidx.compose.material.Text
import androidx.compose.material.TextField
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.graphics.Color
import androidx.navigation.NavController
import androidx.compose.ui.Alignment
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import okhttp3.*
import okhttp3.MediaType.Companion.toMediaTypeOrNull
import org.json.JSONObject
import java.io.IOException

@Composable
fun TokenScreen(navController: NavController, message: String, context: Context) {
    var token by remember { mutableStateOf("") }
    val scope = rememberCoroutineScope()

    Column(
        modifier = Modifier.fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Text(text = message, fontSize = 10.sp, color = Color.Black) // Mostrar el mensaje recibido
        TextField(
            value = token,
            onValueChange = { token = it },
            label = { Text("Ingrese el token", fontSize = 20.sp, color = Color.Black )}
        )
        Spacer(modifier = Modifier.height(16.dp))
        Button(onClick = {
            scope.launch {
                validateToken(context, navController, message, token)
            }
        }) {
            Text(text = "Enviar")
        }
        Spacer(modifier = Modifier.height(16.dp))
    }
}

suspend fun validateToken(context: Context, navController: NavController, userId: String, token: String) {
    val client = OkHttpClient()
    val url = "https://back-end-enfermera.vercel.app/api/Token/usuarios/$userId"
    val json = JSONObject().apply {
        put("token", token)
    }
    val body = RequestBody.create("application/json; charset=utf-8".toMediaTypeOrNull(), json.toString())
    val request = Request.Builder()
        .url(url)
        .post(body)
        .build()

    withContext(Dispatchers.IO) {
        try {
            val response = client.newCall(request).execute()
            withContext(Dispatchers.Main) {
                if (response.isSuccessful) {
                    val responseData = response.body?.string()
                    if (responseData != null) {
                        val jsonResponse = JSONObject(responseData)
                        val message = jsonResponse.getString("message")

                        if (message == "Token guardado exitosamente") {
                            Toast.makeText(context, "Bienvenido", Toast.LENGTH_SHORT).show()
                            navController.navigate("home") // Navegar a la pantalla de inicio
                        } else {
                            Toast.makeText(context, "Error al guardar el token: $message", Toast.LENGTH_SHORT).show()
                        }
                    } else {
                        Toast.makeText(context, "Respuesta vacía del servidor", Toast.LENGTH_SHORT).show()
                    }
                } else {
                    // Obtener el mensaje de error del cuerpo de la respuesta
                    val responseBody = response.body?.string()
                    val errorMessage = if (responseBody != null) {
                        val jsonResponse = JSONObject(responseBody)
                        jsonResponse.getString("message")
                    } else {
                        "Token inválido"
                    }
                    Toast.makeText(context, "Error al guardar el token: $errorMessage", Toast.LENGTH_SHORT).show()
                }
            }
        } catch (e: IOException) {
            withContext(Dispatchers.Main) {
                Toast.makeText(context, "Error: ${e.message}", Toast.LENGTH_SHORT).show()
            }
        }
    }
}