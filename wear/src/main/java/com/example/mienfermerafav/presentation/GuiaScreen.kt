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
fun GuiaScreen(navController: NavController, message: String) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(5.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {

        Image(
            painter = painterResource(id = R.mipmap.ic_launcher_enfermera),
            contentDescription = null,
            modifier = Modifier.size(45.dp)
        )
        // Título principal
        Text(
            text = "Esta aplicación te ayudará a recibir notificaciones.",
            fontSize = 16.sp,
            color = Color.Black,
            textAlign = TextAlign.Center,
            modifier = Modifier.fillMaxWidth()
        )
        // Mostrar el mensaje recibido
        Text(
            text = message,
            fontSize = 14.sp,
            color = Color.Black,
            modifier = Modifier.align(Alignment.CenterHorizontally)
        )
        // Botón para navegar a la siguiente pantalla "token"
        Button(
            onClick = {
                navController.navigate("token")
            },
            colors = ButtonDefaults.buttonColors(backgroundColor = Color(0xFF6200EE)),
            modifier = Modifier.align(Alignment.CenterHorizontally)
        ) {
            Text(text = "Acceder")
        }
    }
}
