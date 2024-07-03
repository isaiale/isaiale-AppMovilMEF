package com.example.mienfermerafav.presentation

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.material.Button
import androidx.compose.material.ButtonDefaults
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.mienfermerafav.R

@Composable
fun WelcomeScreen(navController: NavController) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {

        Image(
            painter = painterResource(id = R.mipmap.ic_launcher_enfermera),
            contentDescription = null,
            modifier = Modifier.size(50.dp)
        )
        // Título de bienvenida
        Text(
            text = "¡Bienvenido a Mi Enfermera Favorita!",
            fontSize = 18.sp,
            color = Color.Black,
            textAlign = TextAlign.Center,
            modifier = Modifier.fillMaxWidth()
        )
        Spacer(modifier = Modifier.height(5.dp))

        // Botón para navegar a la pantalla de "guía"
        Button(
            onClick = {
                navController.navigate("guia")
            },
            colors = ButtonDefaults.buttonColors(backgroundColor = Color(0xFF6200EE)),
            modifier = Modifier.align(Alignment.CenterHorizontally)
        ) {
            Text(text = "Iniciar")
        }
    }
}
