package com.example.fcm

import android.app.NotificationManager
import android.app.PendingIntent
import android.content.Context
import android.content.Intent
import android.media.RingtoneManager
import androidx.core.app.NotificationCompat
import com.google.firebase.messaging.FirebaseMessagingService
import com.google.firebase.messaging.RemoteMessage
import java.util.*

class MyFirebaseMessagingService : FirebaseMessagingService() {
    override fun onMessageReceived(remoteMessage: RemoteMessage) {
        super.onMessageReceived(remoteMessage)

        val intent = Intent(this, MainActivity::class.java)
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP or Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK)
        intent.flags = Intent.FLAG_ACTIVITY_CLEAR_TOP
        val pendingIntent = PendingIntent.getActivity(
            this,
            Random().nextInt(),
            intent,
            PendingIntent.FLAG_UPDATE_CURRENT
        )

      var notificationBuilder:  NotificationCompat.Builder  = NotificationCompat.Builder(
            this,
            "channel_id"
        ).setContentTitle(remoteMessage.getNotification()!!.getTitle())
            .setContentText(remoteMessage.getNotification()!!.getBody())
          .setContentIntent(pendingIntent)
            .setPriority(NotificationCompat.PRIORITY_DEFAULT)
            .setStyle(object : NotificationCompat.BigTextStyle(){})
            .setSound(RingtoneManager.getDefaultUri(RingtoneManager.TYPE_NOTIFICATION))
          .setSmallIcon(R.drawable.logo)
            .setAutoCancel(true);

        var  notificationManager : NotificationManager =
        getSystemService (Context.NOTIFICATION_SERVICE) as NotificationManager

        notificationManager.notify(0, notificationBuilder.build());
    }
}

