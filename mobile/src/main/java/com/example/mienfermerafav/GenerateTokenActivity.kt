package com.example.mienfermerafav

import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import okhttp3.*
import okhttp3.MediaType.Companion.toMediaTypeOrNull
import org.json.JSONObject
import java.io.IOException

class GenerateTokenActivity : AppCompatActivity() {
    private val client = OkHttpClient()
    private lateinit var userId: String

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_genera_token)

        // Asumimos que el ID del usuario se pasa como un extra en el Intent
        userId = intent.getStringExtra("USER_ID") ?: ""

        val userIdTextView = findViewById<TextView>(R.id.userIdTextView)
        val tokenTextView = findViewById<TextView>(R.id.tokenTextView)
        val generateTokenButton = findViewById<Button>(R.id.generateTokenButton)

        // Mostrar el ID del usuario en el TextView
        userIdTextView.text = "User ID: $userId"

        generateTokenButton.setOnClickListener {
            val token = generateToken()
            tokenTextView.text = token
            saveTokenToApi(token)
        }
    }

    private fun generateToken(): String {
        return (10000..99999).random().toString()
    }

    private fun saveTokenToApi(token: String) {
        val url = "https://back-end-enfermera.vercel.app/api/Token/usuarios/$userId"
        val json = JSONObject().apply {
            put("token", token)
        }
        val body = RequestBody.create("application/json; charset=utf-8".toMediaTypeOrNull(), json.toString())
        val request = Request.Builder()
            .url(url)
            .post(body)
            .build()

        client.newCall(request).enqueue(object : Callback {
            override fun onFailure(call: Call, e: IOException) {
                runOnUiThread {
                    // Manejar el error
                    showToast("Error al guardar el token con ID ${userId}: ${e.message}")
                }
            }

            override fun onResponse(call: Call, response: Response) {
                if (response.isSuccessful) {
                    runOnUiThread {
                        // Mostrar un mensaje de éxito
                        showToast("Token guardado exitosamente")
                    }
                } else {
                    runOnUiThread {
                        // Manejar el error
                        showToast("Error al guardar el token con ID ${userId}: ${response.message}")
                    }
                }
            }
        })
    }

    private fun showToast(message: String) {
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show()
    }
}
