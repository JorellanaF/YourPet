package com.example.yourpet

import android.content.res.Configuration
import android.os.Bundle
import android.util.Log
import android.view.View
import com.google.android.material.bottomnavigation.BottomNavigationView
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentTransaction
import com.example.yourpet.Fragments.details_fundacion
import com.example.yourpet.Fragments.fundaciones
import com.example.yourpet.Fragments.home
import com.example.yourpet.Fragments.home.Cambio
import kotlinx.android.synthetic.main.cardview_fundacion.view.*

class MainActivity : AppCompatActivity(), Cambio {

    var fragmentHome: Fragment = home()
    var fragmentFundacion: Fragment = fundaciones()
    var fragmentDetalles: Fragment = details_fundacion()

    private val onNavigationItemSelectedListener = BottomNavigationView.OnNavigationItemSelectedListener { item ->
        var transaction: FragmentTransaction = supportFragmentManager.beginTransaction()
        when (item.itemId) {
            R.id.navigation_home -> {
                transaction.replace(R.id.container, fragmentHome)
                transaction.addToBackStack(null).commit()
                return@OnNavigationItemSelectedListener true
            }
            R.id.navigation_dashboard -> {
                transaction.replace(R.id.container, fragmentFundacion).addToBackStack(null).commit()
                return@OnNavigationItemSelectedListener true
            }
            R.id.navigation_notifications -> {
                return@OnNavigationItemSelectedListener true
            }
        }
        false
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val navView: BottomNavigationView = findViewById(R.id.nav_view)

        navView.setOnNavigationItemSelectedListener(onNavigationItemSelectedListener)
    }

    override fun enviar(numero: Int) {
        var transaction1: FragmentTransaction = supportFragmentManager.beginTransaction()
        if (numero == 1) {
            transaction1.replace(R.id.container, fragmentFundacion).addToBackStack(null).commit()
        }
    }


}
