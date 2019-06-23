package com.example.yourpet

import android.os.Bundle
import com.google.android.material.bottomnavigation.BottomNavigationView
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentTransaction
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.yourpet.Fragments.details_fundacion
import com.example.yourpet.Fragments.fundaciones
import com.example.yourpet.Fragments.home
import com.example.yourpet.Fragments.home.Cambio

class MainActivity : AppCompatActivity(), Cambio, fundaciones.ItemFundacion {

    var fragmentHome: Fragment = home()
    var fragmentFundacion: Fragment = fundaciones()
    val fragmentDetalles = details_fundacion()

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

    override fun nombreItem(nombre: String) {
        var arg = Bundle()
        arg.putString("nombre", nombre)
        fragmentDetalles.setArguments(arg)
        var transition2: FragmentTransaction = supportFragmentManager.beginTransaction()
        transition2.replace(R.id.container, fragmentDetalles).addToBackStack(null).commit()
    }

}
