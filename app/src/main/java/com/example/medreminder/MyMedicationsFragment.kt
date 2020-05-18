package com.example.medreminder

import android.app.AlarmManager
import android.app.PendingIntent
import android.content.Context
import android.content.Context.ALARM_SERVICE
import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.core.content.ContextCompat.getSystemService
import androidx.core.os.bundleOf
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView


class MyMedicationsFragment : Fragment() {

    public var ID = 2

    lateinit var medicationsListRecyclerView : RecyclerView
    lateinit var deleteEditText: EditText
    lateinit var deleteButton: Button

    var IDMap = mutableMapOf<String,Int>()
    private var alarmMgr: AlarmManager? = null
    private lateinit var alarmIntent: PendingIntent

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_my_medications,container,false)

        //list
        medicationsListRecyclerView = view.findViewById(R.id.my_medications_list)
        medicationsListRecyclerView.layoutManager = LinearLayoutManager(activity)

        val medicationList = ArrayList<String>()
        var preference = this.activity!!.getSharedPreferences("Medication list", 0)
        val sharedPreferencesContests = preference.all

        for (medicationInfo in sharedPreferencesContests ){
            val itemName = medicationInfo.key
            val itemAttributes = ArrayList(medicationInfo.value as HashSet<String>)
            var hour = "hour: not defined"
            for(item in itemAttributes) {
                if (item.contains("hour")) {
                    hour = item
                }
                if(item.contains("ID:")){
                    val medicine_ID = item.replace("ID: ","").toInt()
                    IDMap.put(itemName,medicine_ID)
                }
            }
            val medicationRow = String.format("%s medication,     %s",itemName,hour)
            medicationList.add(medicationRow)
        }

        medicationsListRecyclerView.adapter = MedicationsListRecyclerViewAdapter(medicationList)

        //delete medication
        deleteEditText = view.findViewById(R.id.delete_medication_edittext)
        deleteButton = view.findViewById(R.id.delete_medication_button)

        deleteButton.setOnClickListener{
            val medicineToDelete = deleteEditText.text.toString()
            var message : String = ""
            if(preference.contains(medicineToDelete)){
                cancelAlarm(medicineToDelete)
                val editor = preference?.edit()
                editor?.remove(medicineToDelete)
                editor?.apply()
                message = String.format("%s medication has been deleted", medicineToDelete)
            } else {
                message = String.format("%s medication does not exist. Try again.",medicineToDelete)
            }
            displayConfirmation(message)
        }

        return view
    }

    private fun cancelAlarm(medicationToDelete:String) {
        val notificationID = IDMap[medicationToDelete]!!.toInt()
        alarmMgr = context?.getSystemService(Context.ALARM_SERVICE) as AlarmManager
        alarmIntent = Intent(context, AlarmReceiver::class.java).let { intent ->
            var extras = bundleOf(
                "notificationID" to notificationID,
                "medicineName" to medicationToDelete
            )
            intent.putExtras(extras)
            PendingIntent.getBroadcast(context, notificationID, intent, 0)
        }
        alarmMgr!!.cancel(alarmIntent)
    }

    private fun displayConfirmation(message : String) {
        val message : String = String.format("%s", message )
        Toast.makeText(activity, message, Toast.LENGTH_SHORT).show()
    }
}