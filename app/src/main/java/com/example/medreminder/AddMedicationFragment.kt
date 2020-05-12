package com.example.medreminder

import android.app.AlarmManager
import android.app.PendingIntent
import android.content.Context
import android.content.Intent
import android.os.Build
import android.os.Bundle
import android.text.TextUtils
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import androidx.annotation.RequiresApi
import androidx.fragment.app.Fragment
import org.w3c.dom.Text
import java.util.*
import kotlin.collections.ArrayList

class AddMedicationFragment : Fragment() {

    public var ID = 1

    lateinit var medicineNameText : TextView
    lateinit var medicineName: EditText
    lateinit var confirmButton: Button

    private var alarmMgr: AlarmManager? = null
    private lateinit var alarmIntent: PendingIntent

    @RequiresApi(Build.VERSION_CODES.M)
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_add_medication, container, false)

        medicineNameText = view.findViewById(R.id.medicine_name_text)
        medicineName = view.findViewById(R.id.medicine_name)

        confirmButton = view.findViewById(R.id.confirm_button)
        confirmButton.setOnClickListener(){

            //collectUserData(medicineName)
            //zastepstwo
            val medicineNameString : String = medicineName.text.toString()
            val medicationNameString : String = String.format("%s medication", medicineNameString)
            val medicationAttributes = ArrayList<String>()
            //ustawienie shared
            var preference = this.activity?.getSharedPreferences("Medication list", 0)
            val editor = preference?.edit()
            editor?.putStringSet(medicationNameString, medicationAttributes.toHashSet())
            editor?.apply()
            //ustawienie przypomnienia
            val calendar: Calendar = Calendar.getInstance().apply {
                timeInMillis = System.currentTimeMillis()
                set(Calendar.HOUR_OF_DAY, 18)
                set(Calendar.MINUTE, 4)
                set(Calendar.SECOND, 0)
            }

            alarmMgr = context?.getSystemService(Context.ALARM_SERVICE) as AlarmManager
            alarmIntent = Intent(context, AlarmReceiver::class.java).let { intent ->
                PendingIntent.getBroadcast(context, 0, intent, 0)
            }

            alarmMgr?.setInexactRepeating(
                AlarmManager.RTC_WAKEUP,
                calendar.timeInMillis,
                AlarmManager.INTERVAL_DAY,
                alarmIntent
            )

            //toast
            val message : String = String.format("%s has been added", medicationNameString)
            Toast.makeText(activity, message, Toast.LENGTH_SHORT).show()
        }

        return view
    }

    private fun collectUserData(medicineName : TextView) : ArrayList<String> {
        val medicineNameString : String = medicineName.text.toString()
        val medicationNameString : String = String.format("%s medication", medicineNameString)
        val medicationAttributes = ArrayList<String>()
        return ArrayList<String>()
    }

}