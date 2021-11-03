package com.example.notificationsapp

import android.app.Notification
import android.app.NotificationChannel
import android.app.NotificationManager
import android.app.PendingIntent
import android.content.Intent
import android.media.MediaPlayer
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val et = findViewById<EditText>(R.id.et)
        val btn = findViewById<Button>(R.id.btn)
        btn.setOnClickListener {
            if(et.text.toString() != ""){
            val channelId = "myapp.notifications"
            val description = "Notification App Example"
            val notificationManager = getSystemService(NOTIFICATION_SERVICE) as NotificationManager
            val intent = Intent(this, MainActivity::class.java)
            val pendingIntent = PendingIntent.getActivity(this, 0, intent, PendingIntent.FLAG_UPDATE_CURRENT)
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
                var notificationChannel =
                    NotificationChannel(channelId, description, NotificationManager.IMPORTANCE_HIGH)
                notificationManager.createNotificationChannel(notificationChannel)
                var builder = Notification.Builder(this, channelId)
                          .setSmallIcon(R.drawable.ic_notifications)
                          .setContentIntent(pendingIntent)
                          .setContentTitle("My Notification")
                          .setContentText(et.text.toString())
                val mp1 = MediaPlayer.create(this, R.raw.pop)
                mp1.start()
                notificationManager.notify(1234, builder.build())
            }
            else {
                var builder = Notification.Builder(this)
                    .setSmallIcon(R.drawable.ic_notifications)
                    .setContentIntent(pendingIntent)
                    .setContentTitle("My Notification")
                    .setContentText(et.text.toString())
                val mp1 = MediaPlayer.create(this, R.raw.pop)
                mp1.start()
                notificationManager.notify(1234, builder.build())
            }

            }
        }

    }
}