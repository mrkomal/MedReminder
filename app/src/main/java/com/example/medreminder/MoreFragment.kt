package com.example.medreminder

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment

class MoreFragment : Fragment() {

    public var ID = 4

    lateinit var appDescriptiontitle: TextView
    lateinit var appDescription: TextView
    lateinit var instructionTitle : TextView
    lateinit var instruction : TextView

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_more,container,false)

        appDescriptiontitle = view.findViewById(R.id.title_app_description)
        appDescription = view.findViewById(R.id.app_description)

        instructionTitle = view.findViewById(R.id.title_instruction)
        instruction  = view.findViewById(R.id.instruction)

        return view
    }
}