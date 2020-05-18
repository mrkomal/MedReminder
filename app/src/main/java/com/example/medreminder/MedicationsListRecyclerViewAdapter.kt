package com.example.medreminder

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView

class MedicationsListRecyclerViewAdapter(var listNames : ArrayList<String>)
    : RecyclerView.Adapter<MedicationsListViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MedicationsListViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.medication_list_view_holder,parent,false)
        return MedicationsListViewHolder(view)
    }

    override fun getItemCount(): Int {
        return listNames.size
    }

    override fun onBindViewHolder(holder: MedicationsListViewHolder, position: Int) {
        holder.listItemNumber.text = (position + 1).toString()
        holder.listMedicationName.text = listNames[position]
    }

}