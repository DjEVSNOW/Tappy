package com.example.tapp.notif

import android.app.*
import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import android.os.SystemClock
import android.util.Log
import androidx.core.app.NotificationCompat
import com.example.tapp.R
import com.example.tapp.data.Consts
import com.example.tapp.model.Trip
import com.example.tapp.utils.formatDateToUser
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
                SystemClock.elapsedRealtime() + 1000 * 10, (1000 * 10).toLong(), alarmIntent
            )
        }
    }
    fun send(context: Context, text : String) {
        val notification: Notification = NotificationCompat.Builder(context, "MAIN")
            .setContentTitle(context.getString(R.string.app_name))
            .setContentText(text)
            .setSmallIcon(R.mipmap.ic_launcher)
            .build()


        val notificationManager = context.getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager
        notificationManager.notify(0, notification)
    }
    private val preferences by KoinJavaComponent.inject(SharedPreferences::class.java)
    override fun onReceive(context: Context?, intent: Intent) {
        Log.i("onReceive",preferences.trips.size.toString())
        val now = Date()
        preferences.trips.forEach { trip: Trip ->
            trip.transfers.forEach { transfer ->

                val diffDays =  transfer.departure.getTimeDiff(now, TimeUnit.DAYS)
                if (diffDays in 0..1) {
                    send(context!!,"Путешествие ${trip.name} уже завтра! Не забудьте лечь пораньше, выезд в ${transfer.departure.formatTo(
                        Consts.Formats.DateTime.TIME)}")
                }
                if (diffDays == 0L) {
                    send(context!!,"Вы едете на машине уже более 3 часов. Самое время остановиться, чтобы размяться и подышать свежим воздухом.")
                }
            }
        }

    }


}