/*

package com.example.proyectojireh.presentation

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
fun RecordatorioScreen(navController: NavHostController) {
    LazyColumn(
        modifier = Modifier
            .fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        item {
            Spacer(modifier = Modifier.height(30.dp))
            Text("Recordatorios", color = colorResource(id = R.color.secondaryBlue), style = TextStyle(fontSize = 17.sp))
        }
        item {
            Spacer(modifier = Modifier.height(10.dp))
            Card(
                onClick = { navController.navigate("menu") },
                backgroundColor = colorResource(id = R.color.secondaryBlue)
            ) {
                Box(modifier = Modifier.padding(7.dp).fillMaxWidth(0.7f)) {
                    Column {
                        Row(
                            modifier = Modifier.fillMaxWidth(),
                            verticalAlignment = Alignment.CenterVertically
                        ) {
                            Text(
                                text = "\uD83D\uDCA1",
                                color = colorResource(id = R.color.primaryBlue),
                                style = TextStyle(fontSize = 14.sp)
                            )
                            Spacer(modifier = Modifier.width(8.dp))
                            Text(
                                text = "Crear Recordatorios",
                                color = colorResource(id = R.color.primaryBlue),
                                style = TextStyle(fontSize = 14.sp)
                            )
                        }
                    }
                }
            }
        }
    }
}
 */