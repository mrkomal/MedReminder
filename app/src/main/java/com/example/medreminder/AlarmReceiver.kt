package com.example.medreminder

import android.app.NotificationChannel
import android.app.NotificationManager
import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.os.Build
import androidx.annotation.RequiresApi
import androidx.core.app.NotificationCompat


class AlarmReceiver : BroadcastReceiver() {

    lateinit var notificationManager : NotificationManager
    lateinit var notificationID : Integer

    /*
    fun AlarmReceiver(notificationID : Integer){
        this.notificationID = notificationID
    }
    */
    @RequiresApi(Build.VERSION_CODES.O)
    override fun onReceive(context: Context?, intent: Intent?) {
        var notificationManager = context?.getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager
        var builder = NotificationCompat.Builder(context,"medReminder_channel")
            .setSmallIcon(R.drawable.local_hospital_black)
            .setContentTitle("MedManager")
            .setContentText("Take a dose of medicine")
            .setPriority(NotificationCompat.PRIORITY_DEFAULT)
        notificationManager.notify(1,builder.build())
    }

}