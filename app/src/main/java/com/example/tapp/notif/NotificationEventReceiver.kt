package com.example.tapp.notif

import android.app.*
import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import android.os.Build
import android.os.SystemClock
import android.util.Log
import com.example.tapp.R
import com.example.tapp.data.Consts
import com.example.tapp.model.Trip
import com.example.tapp.utils.formatTo
import com.example.tapp.utils.getTimeDiff
import com.example.tapp.utils.trips
import org.koin.java.KoinJavaComponent
import java.util.*
import java.util.concurrent.TimeUnit


class NotificationEventReceiver : BroadcastReceiver() {
    private val ACTION_START_NOTIFICATION_SERVICE = "ACTION_START_NOTIFICATION_SERVICE"
    private val ACTION_DELETE_NOTIFICATION = "ACTION_DELETE_NOTIFICATION"
    private val NOTIFICATIONS_INTERVAL_IN_HOURS = 2

    companion object {
        fun setupAlarm(context: Context) {
            val alarmMgr = context.getSystemService(Context.ALARM_SERVICE) as AlarmManager
            val intent = Intent(context, NotificationEventReceiver::class.java)
            val alarmIntent = PendingIntent.getBroadcast(context, 0, intent, 0)
            alarmMgr.setInexactRepeating(
                AlarmManager.ELAPSED_REALTIME_WAKEUP,
                SystemClock.elapsedRealtime() + 1000 * 60, (1000 * 10).toLong(), alarmIntent
            )
        }
    }
    fun send(context: Context, text: String) {
        val CHANNEL_ID = "main" // The id of the channel.
        val mNotificationManager =
            context.getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            val importance = NotificationManager.IMPORTANCE_HIGH
            val mChannel =
                NotificationChannel(CHANNEL_ID, "Notify", importance)
            mNotificationManager.createNotificationChannel(mChannel)
        }
        val notificationBuilder: Notification.Builder = Notification.Builder(context)
            .setContentTitle("Уведомление от TAPPY")
            .setStyle(Notification.BigTextStyle().bigText(text))
            .setContentText(text)
            .setAutoCancel(true)
            .setPriority(Notification.PRIORITY_HIGH)
            .setDefaults(Notification.FLAG_AUTO_CANCEL or Notification.DEFAULT_LIGHTS or Notification.DEFAULT_VIBRATE or Notification.DEFAULT_SOUND)
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            notificationBuilder.setSmallIcon(R.mipmap.ic_launcher)
            notificationBuilder.setColor(context.resources.getColor(R.color.primaryDark))
        } else {
            notificationBuilder.setSmallIcon(R.mipmap.ic_launcher)
        }
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            notificationBuilder.setChannelId(CHANNEL_ID)
        }
        val notificatioId = System.currentTimeMillis()

        mNotificationManager.notify(notificatioId.toInt(), notificationBuilder.build())
    }
    private val preferences by KoinJavaComponent.inject(SharedPreferences::class.java)
    override fun onReceive(context: Context, intent: Intent) {
        Log.i("onReceive", preferences.trips.size.toString())
        val now = Date()
        preferences.trips.forEach { trip: Trip ->
            trip.transfers.forEach { transfer ->

                val diffDays =  transfer.departure.getTimeDiff(now, TimeUnit.DAYS)
                if (diffDays in 0..1) {
                    send(
                        context,
                        "Путешествие ${trip.name} уже завтра! Не забудьте лечь пораньше, выезд в ${
                            transfer.departure.formatTo(
                                Consts.Formats.DateTime.TIME
                            )
                        }"
                    )
                }
                if (diffDays == 0L) {
                    send(
                        context,
                        "Вы едете на машине уже более 3 часов. Самое время остановиться, чтобы размяться и подышать свежим воздухом."
                    )
                }
            }
        }

    }


}