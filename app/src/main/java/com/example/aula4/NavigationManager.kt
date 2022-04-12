package com.example.aula4

import android.os.Bundle
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import com.example.aula4.databinding.ActivityMainBinding

object NavigationManager {


    private fun placeFragment(fm: FragmentManager, fragment: Fragment){
        val transition=fm.beginTransaction()
        transition.replace(R.id.frame,fragment)
        transition.addToBackStack(null)
        transition.commit()
    }

    fun goToCalculatorFragment(fm: FragmentManager){
        placeFragment(fm,CalculatorFragment())
    }



}