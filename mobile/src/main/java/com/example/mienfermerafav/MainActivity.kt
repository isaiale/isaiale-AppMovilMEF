package com.example.mienfermerafav

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.auth0.android.jwt.JWT
import com.example.mienfermerafav.R // Asegúrate de que esta referencia apunte al paquete correcto
import com.google.android.gms.wearable.PutDataMapRequest
import com.google.android.gms.wearable.Wearable
import okhttp3.*
import okhttp3.MediaType.Companion.toMediaTypeOrNull
import org.json.JSONObject
import java.io.IOException

class MainActivity : AppCompatActivity() {
    private val client = OkHttpClient()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        // Referencias a los campos de texto y botón
        val emailEditText = findViewById<EditText>(R.id.emailEditText)
        val passwordEditText = findViewById<EditText>(R.id.passwordEditText)
        val loginButton = findViewById<Button>(R.id.loginButton)

        // Configurar listener para el botón de inicio de sesión
        loginButton.setOnClickListener {
            val email = emailEditText.text.toString()
            val password = passwordEditText.text.toString()

            if (email.isNotEmpty() && password.isNotEmpty()) {
                login(email, password)
            } else {
                Toast.makeText(this, "Por favor complete todos los campos", Toast.LENGTH_SHORT).show()
            }
        }
    }

    private fun login(email: String, password: String) {
        val url = "https://back-end-enfermera.vercel.app/api/auth/login"
        val json = """
            {
                "correo": "$email",
                "contraseña": "$password"
            }
        """
        val body = RequestBody.create("application/json; charset=utf-8".toMediaTypeOrNull(), json)
        val request = Request.Builder()
            .url(url)
            .post(body)
            .build()

        client.newCall(request).enqueue(object : Callback {
            override fun onFailure(call: Call, e: IOException) {
                runOnUiThread {
                    Toast.makeText(this@MainActivity, "Error de red: ${e.message}", Toast.LENGTH_SHORT).show()
                }
            }

            override fun onResponse(call: Call, response: Response) {
                val responseData = response.body?.string()
                if (response.isSuccessful && responseData != null) {
                    try {
                        val jsonResponse = JSONObject(responseData)
                        val token = jsonResponse.getString("token")
                        val decodedJWT = JWT(token)
                        val userId = decodedJWT.getClaim("_id").asString()
                        val nombre = decodedJWT.getClaim("nombre").asString()
                        val apellido = decodedJWT.getClaim("apellido").asString()
                        val correo = decodedJWT.getClaim("correo").asString()

                        runOnUiThread {
                            // Enviar datos al Wearable
<<<<<<< HEAD
                            sendDataToWearable("Usuario: $nombre $apellido")
=======
                            //sendDataToWearable("Usuario: $nombre $apellido")
>>>>>>> jamesB

                            Toast.makeText(this@MainActivity, "Bienvenido, $nombre $apellido", Toast.LENGTH_SHORT).show()
                            // Redirigir a HomeActivity pasando el ID del usuario, nombre, apellido y correo
                            val intent = Intent(this@MainActivity, HomeActivity::class.java).apply {
                                putExtra("USER_ID", userId)
                                putExtra("NOMBRE", nombre)
                                putExtra("APELLIDO", apellido)
                                putExtra("CORREO", correo)
                            }
                            startActivity(intent)
                            finish() // Cierra la actividad actual para que no pueda regresar con el botón atrás
                        }
                    } catch (e: Exception) {
                        runOnUiThread {
                            Toast.makeText(this@MainActivity, "Error al procesar la respuesta: ${e.message}", Toast.LENGTH_SHORT).show()
                        }
                    }
                } else {
                    runOnUiThread {
                        Toast.makeText(this@MainActivity, "Credenciales incorrectas", Toast.LENGTH_SHORT).show()
                    }
                }
            }
        })
    }

<<<<<<< HEAD
    private fun sendDataToWearable(data: String) {
=======
    /*
    * private fun sendDataToWearable(data: String) {
>>>>>>> jamesB
        val dataMapRequest = PutDataMapRequest.create("/path/to/data")
        val dataMap = dataMapRequest.dataMap
        dataMap.putString("key", data)

        val putDataRequest = dataMapRequest.asPutDataRequest().setUrgent()
        val dataClient = Wearable.getDataClient(this)

        dataClient.putDataItem(putDataRequest)
            .addOnSuccessListener {
<<<<<<< HEAD
                Toast.makeText(this, "Datos enviados exitosamente al wearable", Toast.LENGTH_SHORT).show()
=======
                Toast.makeText(this, "Datos enviados: $data", Toast.LENGTH_SHORT).show()
>>>>>>> jamesB
            }
            .addOnFailureListener { e ->
                Toast.makeText(this, "Error al enviar datos al wearable: ${e.message}", Toast.LENGTH_SHORT).show()
            }
    }
<<<<<<< HEAD
=======
    * */
>>>>>>> jamesB
}