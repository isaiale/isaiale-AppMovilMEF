package com.example.mienfermerafav.presentation

import androidx.compose.foundation.layout.*
import androidx.compose.material.Button
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.graphics.Color
import androidx.navigation.NavController

@Composable
fun HomeScreen(navController: NavController) {
    Column(
        modifier = Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Center
    ) {
        Text(text = "Bienvenido", fontSize = 24.sp, color = Color.Black)
        Spacer(modifier = Modifier.height(16.dp))

        Button(onClick = { navController.navigate("descuentos") }) {
            Text(text = "Descuentos")
        }
        Spacer(modifier = Modifier.height(8.dp))

        Button(onClick = { navController.navigate("vencimiento") }) {
            Text(text = "Vencimiento")
        }
    }
}
