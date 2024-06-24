package com.example.mienfermerafav.presentation

import android.util.Log
import com.google.android.gms.wearable.DataEvent
import com.google.android.gms.wearable.DataEventBuffer
import com.google.android.gms.wearable.DataMapItem
import com.google.android.gms.wearable.WearableListenerService

class DataLayerListenerService : WearableListenerService() {
    override fun onDataChanged(dataEvents: DataEventBuffer) {
        for (event in dataEvents) {
            if (event.type == DataEvent.TYPE_CHANGED) {
                val dataItem = event.dataItem
                if (dataItem.uri.path == "/path/to/data") {
                    val dataMap = DataMapItem.fromDataItem(dataItem).dataMap
                    val data = dataMap.getString("key")
                    Log.d("DataLayerListenerService", "Data received: $data")
                }
            }
        }
    }
}