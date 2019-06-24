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

    //Se cambia el fragmento segun el nav
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
                    transactionH.remove(fragmentHome).commit()
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

    var transactionV: FragmentTransaction = supportFragmentManager.beginTransaction()
    var transactionH: FragmentTransaction = supportFragmentManager.beginTransaction()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val navView: BottomNavigationView = findViewById(R.id.nav_view)

        if(resources.configuration.orientation == Configuration.ORIENTATION_PORTRAIT){
            transactionV.replace(R.id.all_container, fragmentHome).addToBackStack(null).commit()
        }
        else if(resources.configuration.orientation == Configuration.ORIENTATION_LANDSCAPE){
            transactionH.replace(R.id.all_container, fragmentHome).addToBackStack(null).commit()
        }

        navView.setOnNavigationItemSelectedListener(onNavigationItemSelectedListener)
    }

    //Envio el numero de que boton se presiono
    override fun enviar(numero: Int) {
        var transaction1: FragmentTransaction = supportFragmentManager.beginTransaction()
        if (numero == 1) {
            if(resources.configuration.orientation == Configuration.ORIENTATION_PORTRAIT){
                transaction1.remove(fragmentHome).replace(R.id.all_container, fragmentFundacion).addToBackStack(null).commit()
            }
            else if(resources.configuration.orientation == Configuration.ORIENTATION_LANDSCAPE){
                transaction1.remove(fragmentHome).replace(R.id.container, fragmentFundacion).addToBackStack(null).commit()
            }
        }
    }

    //Envio el nombre de la fundacion que se mostrara para los detalles
    override fun nombreItem(nombre: String) {
        var transitionH2: FragmentTransaction = supportFragmentManager.beginTransaction()
        var transitionV2: FragmentTransaction = supportFragmentManager.beginTransaction()
        var arg = Bundle()
        arg.putString("nombre", nombre)
        fragmentDetalles.setArguments(arg)
        if (resources.configuration.orientation == Configuration.ORIENTATION_PORTRAIT) {
            transitionH2.remove(fragmentDetalles).replace(R.id.all_container, fragmentDetalles)
            transitionH2.addToBackStack(null)
            transitionH2.commit()
        } else if (resources.configuration.orientation == Configuration.ORIENTATION_LANDSCAPE) {
            transitionV2.remove(fragmentDetalles).replace(R.id.land_container, fragmentDetalles)
            transitionV2.addToBackStack(null)
            transitionV2.commit()
        }
    }

}
