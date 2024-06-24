package com.example.mienfermerafav.presentation

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            val navController = rememberNavController()
            NavHost(navController = navController, startDestination = "bienvenida") {
                composable("bienvenida") { WelcomeScreen(navController) }
                composable("guia") { GuiaScreen(navController) }
                composable("token") { TokenScreen(navController) }
            }
        }
    }
}
