package com.example.medreminder

import android.app.Notification
import android.app.NotificationManager
import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.os.Build
import android.os.Vibrator
import androidx.annotation.RequiresApi
import androidx.core.app.NotificationCompat


class AlarmReceiver : BroadcastReceiver() {

    lateinit var notificationManager : NotificationManager

    @RequiresApi(Build.VERSION_CODES.O)
    override fun onReceive(context: Context?, intent: Intent?) {
        //get passed data
        val notificationID = intent!!.getIntExtra("notificationID",0)
        val medicineName = intent.getStringExtra("medicineName")

        notificationManager = context?.getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager
        val builder = NotificationCompat.Builder(context,"medReminder_channel")
            .setSmallIcon(R.drawable.local_hospital_black)
            .setContentTitle(medicineName)
            .setContentText("Take a dose of medicine")
            .setPriority(NotificationCompat.PRIORITY_DEFAULT)

        //set vibrations
        val v = context.getSystemService(Context.VIBRATOR_SERVICE) as Vibrator
        v.vibrate(500L)

        notificationManager.notify(notificationID,builder.build())
    }

}