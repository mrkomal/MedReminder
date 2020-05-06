package com.example.medreminder

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import kotlinx.android.synthetic.*

class AddMedicationFragment : Fragment() {

    public var ID = 1
    lateinit var message : TextView

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_add_medication, container, false)
//        message = view.findViewById(R.id.add_new_medication_button) -> BUG
        return view
    }

}