import android.service.notification.NotificationListenerService
import android.service.notification.StatusBarNotification

class MyNotificationListenerService : NotificationListenerService() {

    override fun onNotificationPosted(sbn: StatusBarNotification) {
        // Aquí puedes manejar las notificaciones entrantes
        val packageName = sbn.packageName
        val notification = sbn.notification
        // Procesar la notificación...
    }

    override fun onNotificationRemoved(sbn: StatusBarNotification) {
        // Este método se llama cuando se elimina una notificación
    }
}