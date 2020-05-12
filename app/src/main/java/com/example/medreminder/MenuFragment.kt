package com.example.medreminder

import android.content.Context
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentTransaction

class MenuFragment : Fragment() {

    private lateinit var addNewMedicationButton: Button
    private lateinit var showMyMedicationsButton : Button
    private lateinit var showMyKitButton : Button
    private lateinit var showMoreButton: Button
    private lateinit var listener: SelectFragment

    private fun presentAddMedicationFragment() {
        Log.d("MenuFragment", "presentAddMedicationFragment")
        listener.onFragmentSelected(FragmentID.AddMedication) //ID AddMedicationFragment
    }

    private fun presentMyMedicationsFragment() {
        Log.d("MenuFragment", "presentMyMedicationsFragment")
        listener.onFragmentSelected(FragmentID.MyMedications) //ID MyMedicationFragment
    }

    private fun presentMyKitFragment() {
        Log.d("MenuFragment", "presentMyKitFragment")
        listener.onFragmentSelected(FragmentID.MyKit) //ID MyKitFragment
    }

    private fun presentMoreFragment() {
        Log.d("MenuFragment", "presentMoreFragment")
        listener.onFragmentSelected(FragmentID.More) //ID MoreFragment
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        val view = inflater.inflate(R.layout.fragment_menu, container, false)

        //add new medication
        addNewMedicationButton = view.findViewById(R.id.add_new_medication_button)
        addNewMedicationButton.setOnClickListener(){
            presentAddMedicationFragment()
        }

        //my medications
        showMyMedicationsButton = view.findViewById(R.id.show_medications_button)
        showMyMedicationsButton.setOnClickListener() {
            presentMyMedicationsFragment()
        }

        //my kit
        showMyKitButton = view.findViewById(R.id.show_kit_button)
        showMyKitButton.setOnClickListener(){
            presentMyKitFragment()
        }

        //more
        showMoreButton = view.findViewById(R.id.show_more_button)
        showMoreButton.setOnClickListener() {
            presentMoreFragment()
        }

        return view
    }

    override fun onAttach(context: Context) {
        super.onAttach(context)
        if (context is SelectFragment) {
            listener = context
        } else {
            throw ClassCastException(
                context.toString() + " must implement SelectFragment Interface.")
        }
    }

}