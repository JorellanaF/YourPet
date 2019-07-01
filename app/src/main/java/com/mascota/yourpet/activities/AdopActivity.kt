package com.mascota.yourpet.activities

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.fragment.app.Fragment
import com.mascota.yourpet.Fragments.Adopciones
import com.mascota.yourpet.Fragments.DarAdopFragment
import com.mascota.yourpet.R

class AdopActivity : AppCompatActivity(), Adopciones.Other, DarAdopFragment.Formulario {

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
            }
        }
    }

    override fun sendInfo(op: Int) {
        when(op){
            2->{
                changeFragment(R.id.adop_activity,adopFragment)
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
