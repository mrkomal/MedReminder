package com.example.medreminder

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

class MyMedicationsFragment : Fragment() {

    public var ID = 2

    lateinit var medicationsListRecyclerView : RecyclerView

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_my_medications,container,false)

        medicationsListRecyclerView = view.findViewById(R.id.my_medications_list)
        medicationsListRecyclerView.layoutManager = LinearLayoutManager(activity)

        val medicationList = ArrayList<String>()
        var preference = this.activity!!.getSharedPreferences("Medication list", 0)
        val sharedPreferencesContests = preference.all

        for (medicationInfo in sharedPreferencesContests ){
            val itemAttributes = ArrayList(medicationInfo.value as HashSet<String>)
            val itemName = medicationInfo.key
            medicationList.add(itemName)
        }

        medicationsListRecyclerView.adapter = MedicationsListRecyclerViewAdapter(medicationList)

        return view
    }
}