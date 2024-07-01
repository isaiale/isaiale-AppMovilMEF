package com.example.mienfermerafav.presentation

import android.os.Handler
import android.os.Looper
import android.util.Log
import android.widget.Toast
import androidx.lifecycle.MutableLiveData
import com.google.android.gms.wearable.MessageClient
import com.google.android.gms.wearable.MessageEvent
import com.google.android.gms.wearable.Wearable
import com.google.android.gms.wearable.WearableListenerService

class WearableDataListenerService : WearableListenerService(), MessageClient.OnMessageReceivedListener {

    companion object {
        val receivedData = MutableLiveData<String>()
    }

    override fun onCreate() {
        super.onCreate()
        Wearable.getMessageClient(this).addListener(this)
        Log.d("WearableDataListenerService", "Listener added")
    }

    override fun onMessageReceived(messageEvent: MessageEvent) {
        if (messageEvent.path == "/message_path") {
            val message = String(messageEvent.data)
            Log.d("WearableDataListenerService", "Mensaje recibido: $message")

            // Actualizar el LiveData
            receivedData.postValue(message)

            // Mostrar un Toast con el mensaje recibido
            Handler(Looper.getMainLooper()).post {
                Toast.makeText(this, "Mensaje recibido: $message", Toast.LENGTH_SHORT).show()
            }
        } else {
            Log.d("WearableDataListenerService", "Ruta no coincidente: ${messageEvent.path}")
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        Wearable.getMessageClient(this).removeListener(this)
        Log.d("WearableDataListenerService", "Listener removed")
    }
}
