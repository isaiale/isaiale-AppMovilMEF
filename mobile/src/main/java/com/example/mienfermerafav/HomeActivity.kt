package com.example.mienfermerafav

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import com.example.mienfermerafav.R

class HomeActivity : AppCompatActivity() {
    private lateinit var userId: String
    private lateinit var nombre: String
    private lateinit var apellido: String
    private lateinit var correo: String

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_home)

        // Asumimos que los datos del usuario se pasan como extras en el Intent
        userId = intent.getStringExtra("USER_ID") ?: ""
        nombre = intent.getStringExtra("NOMBRE") ?: ""
        apellido = intent.getStringExtra("APELLIDO") ?: ""
        correo = intent.getStringExtra("CORREO") ?: ""

        val welcomeTextView = findViewById<TextView>(R.id.welcomeTextView)
        welcomeTextView.text = "$nombre $apellido"

        val generateTokenButton = findViewById<Button>(R.id.generateTokenButton)
        generateTokenButton.setOnClickListener {
            val intent = Intent(this, GenerateTokenActivity::class.java).apply {
                putExtra("USER_ID", userId)
            }
            startActivity(intent)
        }

        val logoutButton = findViewById<Button>(R.id.logoutButton)
        logoutButton.setOnClickListener {
            finishAffinity() // Cierra todas las actividades y sale de la aplicación
        }
    }
}
