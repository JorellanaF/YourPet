package com.pet.yourpet.activities

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.fragment.app.Fragment
import com.pet.yourpet.Fragments.Adopciones
import com.pet.yourpet.Fragments.DarAdopFragment
import com.pet.yourpet.R

class AdopActivity : AppCompatActivity(), Adopciones.Other {

    var adopFragment : Fragment = Adopciones()
    var darAdopFragment : Fragment = DarAdopFragment()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_adop)

        changeFragment(R.id.adop_activity,adopFragment)
    }

    override fun change(op: Int) {
        when(op){
            2 ->{
                changeFragment(R.id.adop_activity,darAdopFragment)
                supportFragmentManager.beginTransaction().addToBackStack(null).commit()
            }
        }

    }

    private fun changeFragment(id: Int, frag: Fragment){
        supportFragmentManager
            .beginTransaction()
            .replace(id, frag)
            .commit()
    }
}
