package com.example.mienfermerafav

import android.os.Bundle
<<<<<<< HEAD
=======
import android.util.Log
>>>>>>> jamesB
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
<<<<<<< HEAD
=======
import com.google.android.gms.tasks.Tasks
import com.google.android.gms.wearable.Node
import com.google.android.gms.wearable.Wearable
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
>>>>>>> jamesB
import okhttp3.*
import okhttp3.MediaType.Companion.toMediaTypeOrNull
import org.json.JSONObject
import java.io.IOException
<<<<<<< HEAD
=======
import java.util.concurrent.ExecutionException
import java.util.concurrent.TimeUnit
import java.util.concurrent.TimeoutException

>>>>>>> jamesB

class GenerateTokenActivity : AppCompatActivity() {
    private val client = OkHttpClient()
    private lateinit var userId: String

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_genera_token)

        // Asumimos que el ID del usuario se pasa como un extra en el Intent
        userId = intent.getStringExtra("USER_ID") ?: ""
<<<<<<< HEAD
=======
        Log.d("GenerateTokenActivity", "$userId")
>>>>>>> jamesB

        val userIdTextView = findViewById<TextView>(R.id.userIdTextView)
        val tokenTextView = findViewById<TextView>(R.id.tokenTextView)
        val generateTokenButton = findViewById<Button>(R.id.generateTokenButton)

        // Mostrar el ID del usuario en el TextView
        userIdTextView.text = "User ID: $userId"

        generateTokenButton.setOnClickListener {
<<<<<<< HEAD
            val token = generateToken()
            tokenTextView.text = token
            saveTokenToApi(token)
=======
            try {
                val token = generateToken()
                tokenTextView.text = token
                saveTokenToApi(token)
                sendMessageToWearable("$userId")
            } catch (e: Exception) {
                Log.e("GenerateTokenActivity", "Error al generar token: ${e.message}")
                showToast("Error al generar token: ${e.message}")
            }
>>>>>>> jamesB
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
<<<<<<< HEAD
                    showToast("Error al guardar el token con ID ${userId}: ${e.message}")
                }
=======
                    showToast("Error al guardar el token con ID $userId: ${e.message}")
                }
                Log.e("GenerateTokenActivity", "Error al guardar el token con ID $userId: ${e.message}")
>>>>>>> jamesB
            }

            override fun onResponse(call: Call, response: Response) {
                if (response.isSuccessful) {
                    runOnUiThread {
                        // Mostrar un mensaje de éxito
                        showToast("Token guardado exitosamente")
                    }
<<<<<<< HEAD
                } else {
                    runOnUiThread {
                        // Manejar el error
                        showToast("Error al guardar el token con ID ${userId}: ${response.message}")
                    }
=======
                    Log.d("GenerateTokenActivity", "Token guardado exitosamente")
                } else {
                    runOnUiThread {
                        // Manejar el error
                        showToast("Error al guardar el token con ID $userId: ${response.message}")
                    }
                    Log.e("GenerateTokenActivity", "Error al guardar el token con ID $userId: ${response.message}")
>>>>>>> jamesB
                }
            }
        })
    }

<<<<<<< HEAD
=======
    private fun sendMessageToWearable(message: String) {
        CoroutineScope(Dispatchers.IO).launch {
            try {
                val nodes: List<Node> = Tasks.await(Wearable.getNodeClient(this@GenerateTokenActivity).connectedNodes)
                for (node in nodes) {
                    Wearable.getMessageClient(this@GenerateTokenActivity).sendMessage(node.id, "/message_path", message.toByteArray())
                        .addOnSuccessListener {
                            Log.d("GenerateTokenActivity", "Mensaje enviado: $message")
                            runOnUiThread {
                                showToast("Mensaje enviado: $message")
                            }
                        }
                        .addOnFailureListener { e ->
                            Log.e("GenerateTokenActivity", "Error al enviar mensaje: ${e.message}")
                            runOnUiThread {
                                showToast("Error al enviar mensaje: ${e.message}")
                            }
                        }
                }
            } catch (e: ExecutionException) {
                e.printStackTrace()
                Log.e("GenerateTokenActivity", "Error al enviar mensaje: ${e.message}")
            } catch (e: InterruptedException) {
                e.printStackTrace()
                Log.e("GenerateTokenActivity", "Error al enviar mensaje: ${e.message}")
            }
        }
    }

>>>>>>> jamesB
    private fun showToast(message: String) {
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show()
    }
}
