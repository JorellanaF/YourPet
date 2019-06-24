package com.example.yourpet

import android.content.res.Configuration
import android.os.Bundle
import android.view.View
import com.google.android.material.bottomnavigation.BottomNavigationView
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
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
    var fragmentDetalles: Fragment = details_fundacion()

    //Se cambia el fragmento segun el nav
    private val onNavigationItemSelectedListener = BottomNavigationView.OnNavigationItemSelectedListener { item ->
        var transactionH: FragmentTransaction = supportFragmentManager.beginTransaction()
        var transactionV: FragmentTransaction = supportFragmentManager.beginTransaction()

        var fragmentManager: FragmentManager = supportFragmentManager
        var fragmentActualA: Fragment? = fragmentManager.findFragmentById(R.id.all_container)
        var fragmentActualC: Fragment? = fragmentManager.findFragmentById(R.id.container)
        var fragmentActualD: Fragment? = fragmentManager.findFragmentById(R.id.det_container)

        when (item.itemId) {
            R.id.navigation_home -> {
                if (resources.configuration.orientation == Configuration.ORIENTATION_PORTRAIT) {
                    if (fragmentActualA != null) {
                        transactionV.remove(fragmentActualA).replace(R.id.all_container, fragmentHome)
                    }
                    if (fragmentActualC != null) {
                        transactionV.remove(fragmentActualC)
                    }
                    if (fragmentActualD != null) {
                        transactionV.remove(fragmentActualD)
                    } else {
                        transactionV.replace(R.id.all_container, fragmentHome)
                    }
                    transactionV.addToBackStack(null).commit()
                } else if (resources.configuration.orientation == Configuration.ORIENTATION_LANDSCAPE) {
                    if (fragmentActualA != null) {
                        transactionH.remove(fragmentActualA).replace(R.id.all_container, fragmentHome)
                    }
                    if (fragmentActualC != null) {
                        transactionH.remove(fragmentActualC)
                    }
                    if (fragmentActualD != null) {
                        transactionH.remove(fragmentActualD)
                    } else {
                        transactionH.replace(R.id.all_container, fragmentHome)
                    }
                    transactionH.addToBackStack(null).commit()
                }
                return@OnNavigationItemSelectedListener true
            }
            R.id.navigation_dashboard -> {
                if (resources.configuration.orientation == Configuration.ORIENTATION_PORTRAIT) {
                    if (fragmentActualA != null) {
                        transactionV.remove(fragmentActualA).replace(R.id.all_container, fragmentFundacion)
                    }
                    if (fragmentActualC != null) {
                        transactionV.remove(fragmentActualC)
                    }
                    if (fragmentActualD != null) {
                        transactionV.remove(fragmentActualD)
                    } else {
                        transactionV.replace(R.id.all_container, fragmentFundacion)
                    }
                    transactionV.addToBackStack(null).commit()
                } else if (resources.configuration.orientation == Configuration.ORIENTATION_LANDSCAPE) {
                    if (fragmentActualA != null) {
                        transactionH.remove(fragmentActualA)
                    }
                    if (fragmentActualC != null) {
                        transactionH.remove(fragmentActualC).replace(R.id.container, fragmentFundacion)
                    }
                    if (fragmentActualD != null) {
                        transactionH.remove(fragmentActualD)
                    } else {
                        transactionH.replace(R.id.container, fragmentFundacion)
                    }
                    transactionH.addToBackStack(null).commit()
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

    fun transicion(savedInstanceState: Bundle?) {
        if (savedInstanceState == null) {
            if (resources.configuration.orientation == Configuration.ORIENTATION_PORTRAIT) {
                transactionV.replace(R.id.all_container, fragmentHome)
            } else if (resources.configuration.orientation == Configuration.ORIENTATION_LANDSCAPE) {
                transactionH.replace(R.id.all_container, fragmentHome)
            }
            transactionV.addToBackStack(null).commit()
            transactionH.addToBackStack(null).commit()
        }

    }

    //Envio el numero de que boton se presiono
    override fun enviar(numero: Int) {
        var transaction1: FragmentTransaction = supportFragmentManager.beginTransaction()
        var fragmentManager: FragmentManager = supportFragmentManager
        var fragmentActualA: Fragment? = fragmentManager.findFragmentById(R.id.all_container)
        if (numero == 1) {
            if (resources.configuration.orientation == Configuration.ORIENTATION_PORTRAIT) {
                if (fragmentActualA != null) {
                    transaction1.remove(fragmentActualA).replace(R.id.all_container, fragmentFundacion)
                }
            } else if (resources.configuration.orientation == Configuration.ORIENTATION_LANDSCAPE) {
                if (fragmentActualA != null) {
                    transaction1.remove(fragmentActualA).replace(R.id.container, fragmentFundacion)
                }
            }
            transaction1.addToBackStack(null).commit()
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
            transitionV2.remove(fragmentDetalles).replace(R.id.all_container, fragmentDetalles)
            /*if(resources.configuration.orientation == Configuration.ORIENTATION_LANDSCAPE){
                transitionH2.remove(fragmentDetalles).replace(R.id.det_container, fragmentDetalles)
            }*/
        } else if (resources.configuration.orientation == Configuration.ORIENTATION_LANDSCAPE) {
            transitionH2.remove(fragmentDetalles).replace(R.id.det_container, fragmentDetalles)
        }
        transitionH2.addToBackStack(null)
        transitionH2.commit()
        transitionV2.addToBackStack(null)
        transitionV2.commit()
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val navView: BottomNavigationView = findViewById(R.id.nav_view)
        if(savedInstanceState != null) {
            fragmentHome = supportFragmentManager.getFragment(savedInstanceState,"home")!!

        }

        if(findViewById<View>(R.id.all_container) != null){
            var fragManager: FragmentManager = supportFragmentManager
            var fragActual: Fragment? = fragManager.findFragmentById(R.id.all_container)
            if(fragActual == fragmentHome){
                var transactionV: FragmentTransaction = supportFragmentManager.beginTransaction()
                var transactionH: FragmentTransaction = supportFragmentManager.beginTransaction()
                if(resources.configuration.orientation == Configuration.ORIENTATION_PORTRAIT){
                    transactionV.remove(fragActual).replace(R.id.all_container, fragmentHome)
                }
                if(resources.configuration.orientation == Configuration.ORIENTATION_LANDSCAPE){
                    transactionH.remove(fragActual).replace(R.id.all_container, fragmentHome)
                }
                transactionV.addToBackStack(null).commit()
                transactionH.addToBackStack(null).commit()
            }

            if(fragActual == fragmentFundacion){
                var transactionV: FragmentTransaction = supportFragmentManager.beginTransaction()
                var transactionH: FragmentTransaction = supportFragmentManager.beginTransaction()
                var orientacion: Int = resources.configuration.orientation
                when(orientacion){
                    Configuration.ORIENTATION_PORTRAIT ->{
                        transactionV.remove(fragActual).replace(R.id.all_container, fragmentFundacion)
                    }
                    Configuration.ORIENTATION_LANDSCAPE ->{
                        transactionH.remove(fragActual).replace(R.id.container, fragmentFundacion)
                    }
                }
                transactionV.addToBackStack(null).commit()
                transactionH.addToBackStack(null).commit()
            }

        }

        transicion(savedInstanceState)

        navView.setOnNavigationItemSelectedListener(onNavigationItemSelectedListener)
    }

    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)
        supportFragmentManager.putFragment(outState, "home", fragmentHome)
    }

}
