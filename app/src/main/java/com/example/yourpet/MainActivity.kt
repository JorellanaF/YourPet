package com.example.yourpet

import android.content.res.Configuration
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
        var transactionH: FragmentTransaction = supportFragmentManager.beginTransaction()
        var transactionV: FragmentTransaction = supportFragmentManager.beginTransaction()
        when (item.itemId) {
            R.id.navigation_home -> {
                if(resources.configuration.orientation == Configuration.ORIENTATION_PORTRAIT){
                    transactionH.replace(R.id.all_container, fragmentHome)
                    transactionH.addToBackStack(null).commit()
                }
                else if(resources.configuration.orientation == Configuration.ORIENTATION_LANDSCAPE){
                    transactionV.replace(R.id.all_container, fragmentHome)
                    transactionV.addToBackStack(null).commit()
                }
                return@OnNavigationItemSelectedListener true
            }
            R.id.navigation_dashboard -> {
                if(resources.configuration.orientation == Configuration.ORIENTATION_PORTRAIT){
                    transactionH.replace(R.id.all_container, fragmentFundacion)
                    transactionH.addToBackStack(null).commit()
                }
                else if(resources.configuration.orientation == Configuration.ORIENTATION_LANDSCAPE){
                    transactionH.remove(fragmentHome)
                    transactionV.replace(R.id.container, fragmentFundacion)
                    transactionV.addToBackStack(null).commit()
                }
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


        var transaction: FragmentTransaction = supportFragmentManager.beginTransaction()
        transaction.replace(R.id.all_container, fragmentHome).addToBackStack(null).commit()

        navView.setOnNavigationItemSelectedListener(onNavigationItemSelectedListener)
    }

    override fun enviar(numero: Int) {
        var transaction1: FragmentTransaction = supportFragmentManager.beginTransaction()
        if (numero == 1) {
            if(resources.configuration.orientation == Configuration.ORIENTATION_PORTRAIT){
                transaction1.replace(R.id.all_container, fragmentFundacion).addToBackStack(null).commit()
            }
            else if(resources.configuration.orientation == Configuration.ORIENTATION_LANDSCAPE){
                transaction1.replace(R.id.container, fragmentFundacion).addToBackStack(null).commit()
            }
        }
    }

    override fun nombreItem(nombre: String) {
        var transitionH2: FragmentTransaction = supportFragmentManager.beginTransaction()
        var transitionV2: FragmentTransaction = supportFragmentManager.beginTransaction()
        var arg = Bundle()
        arg.putString("nombre", nombre)
        fragmentDetalles.setArguments(arg)
        if (resources.configuration.orientation == Configuration.ORIENTATION_PORTRAIT) {
            transitionH2.replace(R.id.all_container, fragmentDetalles)
            transitionH2.addToBackStack(null)
            transitionH2.commit()
        } else if (resources.configuration.orientation == Configuration.ORIENTATION_LANDSCAPE) {
            transitionH2.remove(fragmentDetalles).commit()
            transitionV2.replace(R.id.land_container, fragmentDetalles)
            transitionV2.addToBackStack(null)
            transitionV2.commit()
        }
    }

}
