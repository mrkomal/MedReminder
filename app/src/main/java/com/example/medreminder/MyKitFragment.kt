package com.example.medreminder

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

class MyKitFragment : Fragment() {

    public var ID = 3

    lateinit var medicineListRecyclerView : RecyclerView

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_my_kit,container,false)

        medicineListRecyclerView = view.findViewById(R.id.my_medicines_list)
        medicineListRecyclerView.layoutManager = LinearLayoutManager(activity)

        val medicineList = ArrayList<String>()
        var preference = this.activity!!.getSharedPreferences("Medication list", 0)
        val sharedPreferencesContests = preference.all

        for (medicationInfo in sharedPreferencesContests ){
            val itemAttributes = ArrayList(medicationInfo.value as HashSet<String>)
            var type = "type: not defined"
            for(item in itemAttributes){
                if(item.contains("type")){
                    type = item
                }
            }
            val itemName = medicationInfo.key
            val medicineRow = String.format("%s,     %s",itemName,type)
            medicineList.add(medicineRow)
        }


        medicineListRecyclerView.adapter = MedicinesListRecyclerViewAdapter(medicineList)

        return view
    }
}