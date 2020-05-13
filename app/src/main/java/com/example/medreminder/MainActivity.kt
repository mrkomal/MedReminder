package com.example.medreminder

import android.app.NotificationChannel
import android.app.NotificationManager
import android.content.Context
import android.content.SharedPreferences
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.core.app.NotificationCompat
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentTransaction
import java.util.ArrayList

class MainActivity : AppCompatActivity(), SelectFragment{

    lateinit var preference : SharedPreferences
    private val channelID = "medReminder_channel"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        //wlasne
        init()
        createNotificationChannel()
        preference = this.getSharedPreferences("Medication list", 0)
    }

    private fun init() {
        val menu : MenuFragment = MenuFragment()
        changeFragment(menu, false)
    }

    override fun onFragmentSelected(fragmentID: FragmentID) {

        if (fragmentID == FragmentID.AddMedication) { //AddMedication
            val addMedicationFragment = AddMedicationFragment()
            changeFragment(addMedicationFragment, true)
        }

        if(fragmentID == FragmentID.MyMedications) { //MyMedications
            val myMedicationsFragment = MyMedicationsFragment()
            changeFragment(myMedicationsFragment, true)
        }

        if(fragmentID == FragmentID.MyKit) { //MyKit
            val myKitFragment = MyKitFragment()
            changeFragment(myKitFragment, true)
        }

        if(fragmentID == FragmentID.More) { //MyKit
            val moreFragment = MoreFragment()
            changeFragment(moreFragment, true)
        }

    }

    private fun changeFragment(fragment: Fragment, addToBackStack : Boolean) {
        val transaction : FragmentTransaction = supportFragmentManager.beginTransaction()
        transaction.replace(R.id.main_container, fragment)
        if(addToBackStack){
            transaction.addToBackStack(null)
        }
        transaction.commit()
    }

    private fun createNotificationChannel() {
        // Create the NotificationChannel, but only on API 26+ because
        // the NotificationChannel class is new and not in the support library
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            val name = "MedReminder"
            val descriptionText = "Notifications from MedReminder"
            val importance = NotificationManager.IMPORTANCE_DEFAULT
            val channel = NotificationChannel(channelID, name, importance).apply {
                description = descriptionText
            }
            // Register the channel with the system
            val notificationManager: NotificationManager =
                getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager
            notificationManager.createNotificationChannel(channel)
        }
    }


}

