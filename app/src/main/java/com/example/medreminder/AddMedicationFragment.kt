package com.example.medreminder

import android.annotation.SuppressLint
import android.app.AlarmManager
import android.app.Notification.FLAG_AUTO_CANCEL
import android.app.PendingIntent
import android.content.Context
import android.content.Intent
import android.os.Build
import android.os.Bundle
import android.text.TextUtils
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.*
import androidx.annotation.RequiresApi
import androidx.core.os.bundleOf
import androidx.fragment.app.Fragment
import kotlinx.android.synthetic.main.fragment_add_medication.view.*
import org.w3c.dom.Text
import java.util.*
import kotlin.collections.ArrayList

class AddMedicationFragment : Fragment() {

    public var ID = 1

    lateinit var medicineNameText : TextView
    lateinit var medicineName: EditText
    lateinit var medicineHourText: TextView
    lateinit var medicineHour: TimePicker
    lateinit var medicineTypeText: TextView
    lateinit var medicineType: RadioButton
    lateinit var medicinetypeString: String
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

        //Name of medicine part
        medicineNameText = view.findViewById(R.id.medicine_name_text)
        medicineName = view.findViewById(R.id.medicine_name)

        //Time of dose part
        medicineHourText = view.findViewById(R.id.medicine_hour_text)
        medicineHour = view.findViewById(R.id.time_picker)

        //Type of medicine part
        medicineTypeText = view.findViewById(R.id.choose_medicine_type_text)

        //Confirm button
        confirmButton = view.findViewById(R.id.confirm_button)
        confirmButton.setOnClickListener(){
            val userInput : String = medicineName.text.toString().trim()
            val nameExists : Boolean = checkIfNameAlreadyExists(userInput)
            if(nameExists){
                displayError()
            } else {
                //set values
                val medicineNameString : String = userInput
                val medicationNameString : String = String.format("%s medication", medicineNameString)
                val medicineHourInt = medicineHour.hour
                val medicineMinuteInt = medicineHour.minute
                checkMedicineType(view)
                val medicineTypeString = medicineType.text.toString()
                val notificationID = generateNotificationID()

                //set medication attributes
                val medicationAttributes = createAttributesArray(notificationID,medicineTypeString,medicineHourInt,medicineMinuteInt)

                //set shared preferences
                setSharedPreferences(medicineNameString, medicationAttributes)

                //set alarm
                setAlarm(medicineNameString,notificationID,medicineHourInt,medicineMinuteInt)

                //toast (confirmation)
                displayConfirmation(medicationNameString)
            }
        }

        return view
    }

    private fun checkIfNameAlreadyExists(medicineName: String): Boolean {
        val preference = this.activity!!.getSharedPreferences("Medication list", 0)
        var nameExists = false
        if(preference.contains(medicineName)){
            nameExists = true
        }
        return nameExists
    }

    private fun checkMedicineType(view : View){
        var chosenTypeId = view.medicine_types.checkedRadioButtonId
        medicineType = view.findViewById(chosenTypeId)
    }

    private fun createAttributesArray(notificationID:Int, medicineTypeString : String,
                                      medicineHourInt : Int, medicineMinuteInt: Int) : ArrayList<String> {
        val notificationID = String.format("ID: %s",notificationID.toString())
        val type = String.format("type: %s", medicineTypeString)
        val hour = String.format("hour: %d:%d",medicineHourInt,medicineMinuteInt)
        var attributesArrayList = ArrayList<String>()
        attributesArrayList.add(notificationID)
        attributesArrayList.add(type)
        attributesArrayList.add(hour)
        return attributesArrayList
    }

    private fun setSharedPreferences(medicineNameString : String, medicationAttributes : ArrayList<String>){
        var preference = this.activity?.getSharedPreferences("Medication list", 0)
        val editor = preference?.edit()
        editor?.putStringSet(medicineNameString, medicationAttributes.toHashSet())
        editor?.apply()
    }

    private fun generateNotificationID():Int {
        var preference = this.activity?.getSharedPreferences("Notification ID", 0)
        val lastID = preference!!.getInt("last ID",0)
        val currentID = lastID.plus(1)
        val editor = preference.edit()
        editor?.putInt("last ID",currentID)
        editor?.apply()
        return currentID
    }

    private fun setAlarm(medicineName : String, notificationID: Int, medicineHourInt : Int, medicineMinuteInt : Int) {
        val calendar: Calendar = Calendar.getInstance().apply {
            timeInMillis = System.currentTimeMillis()
            set(Calendar.HOUR_OF_DAY, medicineHourInt)
            set(Calendar.MINUTE, medicineMinuteInt)
            set(Calendar.SECOND, 0)
        }

        alarmMgr = context?.getSystemService(Context.ALARM_SERVICE) as AlarmManager
        alarmIntent = Intent(context, AlarmReceiver::class.java).let { intent ->
            var extras = bundleOf(
                "notificationID" to notificationID,
                "medicineName" to medicineName
            )
            intent.putExtras(extras)
            PendingIntent.getBroadcast(context, notificationID, intent, 0)
        }

        alarmMgr?.setInexactRepeating(
            AlarmManager.RTC_WAKEUP,
            calendar.timeInMillis,
            AlarmManager.INTERVAL_DAY,
            alarmIntent
        )
    }

    private fun displayError(){
        val message : String = "Given name already exists. Try to change the name by adding 1,2,...etc at the end of the name."
        Toast.makeText(activity, message, Toast.LENGTH_LONG).show()
    }

    private fun displayConfirmation(medicationNameString : String) {
        val message : String = String.format("%s has been added", medicationNameString )
        Toast.makeText(activity, message, Toast.LENGTH_SHORT).show()
    }
}