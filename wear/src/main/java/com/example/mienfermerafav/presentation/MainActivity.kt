package com.example.mienfermerafav.presentation

import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.Surface
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.google.android.gms.wearable.MessageClient
import com.google.android.gms.wearable.MessageEvent
import com.google.android.gms.wearable.Wearable
import kotlinx.coroutines.launch

class MainActivity : ComponentActivity(), MessageClient.OnMessageReceivedListener {
    private val sharedViewModel: SharedViewModel by lazy {
        ViewModelProvider(this).get(SharedViewModel::class.java)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        // Registrar el listener para recibir mensajes
        Wearable.getMessageClient(this).addListener(this)

        setContent {
            val navController = rememberNavController()
            Surface(
                modifier = Modifier.fillMaxSize(),
                color = Color.White // Color de fondo blanco
            ) {
                NavHost(navController = navController, startDestination = "bienvenida") {
                    composable("bienvenida") { WelcomeScreen(navController) }
                    composable("guia") {
                        val receivedData by sharedViewModel.receivedData.observeAsState("")
                        GuiaScreen(navController, receivedData ?: "")
                    }
                    composable("token") {
                        val receivedData by sharedViewModel.receivedData.observeAsState("")
                        TokenScreen(navController, receivedData ?: "", this@MainActivity)
                    }
                    composable("home") { HomeScreen(navController) }
                    composable("descuentos") { DescuentosScreen(navController) }
                    composable("vencimiento") { VencimientoScreen(navController) }
                }
            }
        }
    }

    override fun onMessageReceived(messageEvent: MessageEvent) {
        if (messageEvent.path == "/message_path") {
            val message = String(messageEvent.data)
            Log.d("MainActivity", "Mensaje recibido: $message")

            // Actualizar el LiveData en el ViewModel
            sharedViewModel.setReceivedData(message)

            // Mostrar un Toast con el mensaje recibido
            runOnUiThread {
                Toast.makeText(this, "Mensaje recibido: $message", Toast.LENGTH_SHORT).show()
            }
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        // Eliminar el listener al destruir la actividad
        Wearable.getMessageClient(this).removeListener(this)
    }
}