/*
* package com.example.proyectojireh.presentation

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import com.example.proyectojireh.R

@OptIn(ExperimentalMaterialApi::class)
@Composable
fun MenuScreen(navController: NavHostController) {
    LazyColumn(
        modifier = Modifier
            .fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        item {
            Spacer(modifier = Modifier.height(16.dp))
            Text("Aplicaciones", color = colorResource(id = R.color.secondaryBlue), style = TextStyle(fontSize = 17.sp))
            Spacer(modifier = Modifier.height(10.dp))
        }
        item {
            Card(
                onClick = { navController.navigate("cita") },
                backgroundColor = colorResource(id = R.color.secondaryBlue)
            ) {
                Box(modifier = Modifier.padding(10.dp).fillMaxWidth(0.7f)) {
                    Text(text = "\uD83D\uDCC5 Citas", color = colorResource(id = R.color.primaryBlue), style = TextStyle(fontSize = 12.sp))
                }
            }
        }
        item {
            Card(
                onClick = { navController.navigate("cita") },
                backgroundColor = colorResource(id = R.color.secondaryBlue)
            ) {
                Box(modifier = Modifier.padding(10.dp).fillMaxWidth(0.7f)) {
                    Text(text = "\uD83D\uDC3E Mascotas", color = colorResource(id = R.color.primaryBlue), style = TextStyle(fontSize = 12.sp))
                }
            }
        }
        item {
            Card(
                onClick = { navController.navigate("recordatorio") },
                backgroundColor = colorResource(id = R.color.secondaryBlue)
            ) {
                Box(modifier = Modifier.padding(10.dp).fillMaxWidth(0.7f)) {
                    Text(text = "\uD83D\uDCA1 Recordatorios", color = colorResource(id = R.color.primaryBlue), style = TextStyle(fontSize = 12.sp))
                }
            }
        }
        item {
            Card(
                onClick = { navController.navigate("preferencia") },
                backgroundColor = colorResource(id = R.color.secondaryBlue)
            ) {
                Box(modifier = Modifier.padding(10.dp).fillMaxWidth(0.7f)) {
                    Text(text = "⚙\uFE0F Ajustes", color = colorResource(id = R.color.primaryBlue), style = TextStyle(fontSize = 12.sp))
                }
            }
        }
    }
}
* */