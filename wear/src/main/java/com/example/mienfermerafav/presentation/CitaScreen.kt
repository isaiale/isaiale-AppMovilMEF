/*
* package com.example.proyectojireh.presentation

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
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
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import okhttp3.OkHttpClient
import okhttp3.Request
import org.json.JSONArray

data class Cita(val mascota: String, val servicio: String, val fecha: String)

@OptIn(ExperimentalMaterialApi::class)
@Composable
fun CitaScreen(navController: NavHostController, userViewModel: UserViewModel) {
    val userName = userViewModel.userData?.get("_id") ?: "ID"

    val citas: MutableState<List<Cita>> = remember { mutableStateOf(emptyList()) }

    LaunchedEffect(key1 = true) {
        citas.value = fetchCitas(userName)
    }

    LazyColumn(
        modifier = Modifier.fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        item {
            Spacer(modifier = Modifier.height(30.dp))
            Text(
                "Citas",
                color = colorResource(id = R.color.secondaryBlue),
                style = TextStyle(fontSize = 17.sp)
            )
        }

        // Iterar sobre las citas y mostrar cada una en una Card
        itemsIndexed(citas.value) { _, cita ->
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
                                text = "\uD83D\uDCC5\uD83D\uDC36",
                                color = colorResource(id = R.color.primaryBlue),
                                style = TextStyle(fontSize = 10.sp)
                            )
                            Spacer(modifier = Modifier.weight(1f))
                            Text(
                                text = cita.mascota,
                                color = colorResource(id = R.color.primaryBlue),
                                style = TextStyle(fontSize = 10.sp)
                            )
                        }
                        Spacer(modifier = Modifier.height(5.dp))
                        Text(
                            text = "Tiene una cita de ${cita.servicio} para el día ${cita.fecha}",
                            color = colorResource(id = R.color.primaryBlue),
                            style = TextStyle(fontSize = 10.sp)
                        )
                    }
                }
            }
            Spacer(modifier = Modifier.height(15.dp))
        }
    }

}

suspend fun fetchCitas(userName: String): List<Cita> {
    val client = OkHttpClient()
    val url = "https://backend-jireh.onrender.com/api/v1/cita/wearos/$userName"
    val request = Request.Builder()
        .url(url)
        .build()

    return withContext(Dispatchers.IO) {
        val response = client.newCall(request).execute()
        val responseData = response.body?.string()

        val citasList = mutableListOf<Cita>()

        responseData?.let {
            val jsonArray = JSONArray(it)
            for (i in 0 until jsonArray.length()) {
                val jsonObject = jsonArray.getJSONObject(i)
                val mascota = jsonObject.getString("mascota")
                val servicio = jsonObject.getString("servicio")
                val fecha = jsonObject.getString("fecha")
                citasList.add(Cita(mascota, servicio, fecha))
            }
        }

        return@withContext citasList
    }
}

* */