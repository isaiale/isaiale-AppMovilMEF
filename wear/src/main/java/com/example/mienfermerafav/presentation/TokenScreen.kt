package com.example.mienfermerafav.presentation

import androidx.compose.foundation.layout.*
import androidx.compose.material.Button
import androidx.compose.material.Text
import androidx.compose.material.TextField
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController

@Composable
fun TokenScreen(navController: NavController) {
    var token by remember { mutableStateOf("") }
    Column(
        modifier = Modifier.fillMaxSize(),

        verticalArrangement = Arrangement.Center
    ) {
        TextField(
            value = token,
            onValueChange = { token = it },
            label = { Text("Ingrese el token") }
        )
        Spacer(modifier = Modifier.height(16.dp))
        Button(onClick = {
            // Aquí puedes agregar la lógica para manejar el token
        }) {
            Text(text = "Enviar")
        }
    }
}
