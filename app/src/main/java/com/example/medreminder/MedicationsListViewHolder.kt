package com.example.medreminder

import android.view.View
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class MedicationsListViewHolder (itemView : View) : RecyclerView.ViewHolder(itemView){
    val listItemNumber = itemView.findViewById(R.id.item_number) as TextView
    val listMedicationName = itemView.findViewById(R.id.medication_name) as TextView
}