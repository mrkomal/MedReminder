package com.example.medreminder

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView

class MedicinesListRecyclerViewAdapter (var listMedicineNames : ArrayList<String>)
    : RecyclerView.Adapter<MedicinesListViewHolder>() {

        override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MedicinesListViewHolder{
            val view = LayoutInflater.from(parent.context).inflate(R.layout.medicine_list_view_holder,parent,false)
            return MedicinesListViewHolder(view)
        }

        override fun getItemCount(): Int {
            return listMedicineNames.size
        }

        override fun onBindViewHolder(holder: MedicinesListViewHolder, position: Int) {
            holder.listItemNumber.text = (position + 1).toString()
            holder.listMedicationName.text = listMedicineNames[position]
        }

    }