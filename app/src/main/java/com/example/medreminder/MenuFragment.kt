package com.example.medreminder

import android.content.Context
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentTransaction

class MenuFragment : Fragment() {

    private lateinit var addNewMedicationButton: Button
    private lateinit var listener: SelectFragment // zadeklarowane ale nie zainicjalizowane!

    fun presentAddMedicationFragment() {
        Log.d("MenuFragment", "presentAddMedicationFragment")
        listener.onFragmentSelected(FragmentID.AddMedication) //ID AddMedicationFragment

        //Metoda brutalna:

//        val fragment = AddMedicationFragment()
//        var parentActivity = activity
//        if (parentActivity != null) {
//            parentActivity.supportFragmentManager.beginTransaction()
//                .replace(R.id.main_container, fragment)
//                .addToBackStack(null)
//                .commit()
//        }
    }
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_menu, container, false)
        addNewMedicationButton = view.findViewById(R.id.add_new_medication_button)
        addNewMedicationButton.setOnClickListener(){
            presentAddMedicationFragment()
        }
        return view
    }

    //Metoda uruchamiana w chwili gdy fragment zostanie dolaczony do jego kontekstu,
    // a jego kontekstem w tym przypadku jest zaladowanie go do layoutu i wyswietlenie w MainActivity
    override fun onAttach(context: Context) {
        super.onAttach(context)
        // context -> proszę doczytac co to takiego https://medium.com/@banmarkovic/what-is-context-in-android-and-which-one-should-you-use-e1a8c6529652
        if (context is SelectFragment) {
            listener = context
        } else {
            throw ClassCastException(
                context.toString() + " must implement SelectFragment Interface.")
        }
    }

}