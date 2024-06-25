package com.example.mienfermerafav.presentation

import androidx.compose.foundation.layout.*
import androidx.compose.material.Button
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController

@Composable
fun GuiaScreen(navController: NavController) {
    Column(
        modifier = Modifier.fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Text(text = "La app es para mostrar notificaciones", fontSize = 20.sp, color = Color.White)
        Spacer(modifier = Modifier.height(16.dp))
        Button(onClick = {
            navController.navigate("token")
        }) {
            Text(text = "Siguiente1")
        }
    }
}
