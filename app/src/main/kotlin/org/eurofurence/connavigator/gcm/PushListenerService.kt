package org.eurofurence.connavigator.gcm

import com.google.firebase.messaging.FirebaseMessaging
import com.google.firebase.messaging.FirebaseMessagingService
import com.google.firebase.messaging.RemoteMessage
import com.pawegio.kandroid.d
import com.pawegio.kandroid.i
import com.pawegio.kandroid.w
import org.eurofurence.connavigator.BuildConfig
import org.eurofurence.connavigator.database.UpdateIntentService
import org.jetbrains.anko.*

/**
 * Created by David on 14-4-2016.
 */
class PushListenerService : FirebaseMessagingService(), AnkoLogger {
    val factory by lazy { NotificationFactory(applicationContext) }

    fun subscribe() {
        val messaging = FirebaseMessaging.getInstance()
        if (BuildConfig.DEBUG) {
            messaging.subscribeToTopic("test")
        }
        messaging.subscribeToTopic("announcements")
    }

    override fun onMessageReceived(message: RemoteMessage) {
        info { "Received push message" }
        debug { "Message payload :" + message.data }

        when (message.data["Event"]) {
            "Announcement" -> createAnnouncement(message)
            "ImportantAnnouncement" -> createHighPriorityAnnouncement(message)
            "Sync" -> syncData(message)
            else -> warn("Message did not contain a valid event. Abandoning!")
        }


    }

    private fun syncData(message: RemoteMessage) {
        info { "Received request to sync data" }

        applicationContext.sendBroadcast(intentFor<UpdateIntentService>())
    }

    private fun createAnnouncement(message: RemoteMessage) {
        info { "Received request to create announcement notification" }

        val notification = factory.createBasicNotification()
                .setContentTitle(message.data["Title"])
                .setContentText(message.data["Text"])

        factory.broadcastNotification(notification)
    }

    private fun createHighPriorityAnnouncement(message: RemoteMessage) {
        info { "Received request to make high priority announcement" }

        val notification = factory.createBasicNotification()
                .setContentTitle(message.data["Title"])
                .setContentText(message.data["Text"])

        factory.broadcastNotification(factory.makeHighPriority(notification))
    }
}