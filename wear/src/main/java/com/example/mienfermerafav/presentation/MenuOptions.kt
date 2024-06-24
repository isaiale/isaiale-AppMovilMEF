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
fun MenuOptions(navController: NavHostController, userViewModel: UserViewModel) {
    val userName = userViewModel.userData?.get("user") ?: "Usuario"
    LazyColumn(
        modifier = Modifier
            .fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        item {
            Spacer(modifier = Modifier.height(16.dp))
            Text("Clínica Veterinaria", color = colorResource(id = R.color.secondaryBlue), style = TextStyle(fontSize = 16.sp))
            Text("JIREH", color = colorResource(id = R.color.secondaryBlue), style = TextStyle(fontSize = 14.sp))
            Text("Bienvenido, $userName", color = colorResource(id = R.color.secondaryBlue), style = TextStyle(fontSize = 14.sp))
        }
        item {
            Spacer(modifier = Modifier.height(16.dp))
            Card(
                onClick = { navController.navigate("menu") },
                backgroundColor = colorResource(id = R.color.primaryBlue)
            ) {
                Box(modifier = Modifier.padding(5.dp)) {
                    Text(text = "\uD83D\uDCF2 Ir a Menú", color = colorResource(id = R.color.secondaryBlue), style = TextStyle(fontSize = 10.sp))
                }
            }
        }
    }
}
* */