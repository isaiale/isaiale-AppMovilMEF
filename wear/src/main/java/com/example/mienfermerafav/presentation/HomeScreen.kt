package com.example.mienfermerafav.presentation

import androidx.compose.foundation.layout.*
import androidx.compose.material.Button
import androidx.compose.material.ButtonDefaults
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextAlign
import androidx.navigation.NavController

@Composable
fun HomeScreen(navController: NavController) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Text(
            text = "Ahora puedes explorar nuestras ofertas y vigencias.",
            fontSize = 18.sp,
            color = Color.Black,
            textAlign = TextAlign.Center,
            modifier = Modifier.fillMaxWidth().padding(bottom = 16.dp)
        )
        Button(
            onClick = {
                navController.navigate("descuentos")
            },
            colors = ButtonDefaults.buttonColors(backgroundColor = Color(0xFF6200EE)),
            modifier = Modifier.align(Alignment.CenterHorizontally)
        ) {
            Text(text = "Descuentos")
        }

//        Button(onClick = {
//                navController.navigate("vencimiento")
//            },
//            colors = ButtonDefaults.buttonColors(backgroundColor = Color(0xFF6200EE)),
//            modifier = Modifier.align(Alignment.CenterHorizontally)
//        ) {
//            Text(text = "Vencimiento")
//        }
    }
}
