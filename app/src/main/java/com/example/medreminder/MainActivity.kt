package com.example.medreminder

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentTransaction

class MainActivity : AppCompatActivity(), IMainActivity {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        init()
    }

    private fun init() {
        val menu : MenuFragment = MenuFragment()
        changeFragment(menu)
    }

    private fun changeFragment(fragment: Fragment) {
        val transaction : FragmentTransaction = supportFragmentManager.beginTransaction()
        transaction.replace(R.id.main_container, fragment)
        transaction.commit()
    }

    @Override
    override fun produceFragment(){
        val addMedicationFragment = AddMedicationFragment()
        changeFragment(addMedicationFragment)
    }
}
