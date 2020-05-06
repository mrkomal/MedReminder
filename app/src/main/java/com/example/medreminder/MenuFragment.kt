package com.example.medreminder

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentTransaction

class MenuFragment : Fragment(), IMainActivity {

    private lateinit var addNewMedicationButton: Button
    private lateinit var iMainActivity: IMainActivity

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_menu, container, false)
        addNewMedicationButton = view.findViewById(R.id.add_new_medication_button)
        addNewMedicationButton.setOnClickListener(){
            iMainActivity.produceFragment()
        }
        return view
    }

}