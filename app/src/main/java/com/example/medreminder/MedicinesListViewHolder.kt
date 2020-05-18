package com.example.medreminder

import android.view.View
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class MedicinesListViewHolder(itemView : View) : RecyclerView.ViewHolder(itemView){
    val listItemNumber = itemView.findViewById(R.id.medicine_number) as TextView
    val listMedicationName = itemView.findViewById(R.id.type_of_medicine) as TextView
}