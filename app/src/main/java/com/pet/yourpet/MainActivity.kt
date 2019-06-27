package com.pet.yourpet

import android.content.Intent
import android.content.res.Configuration
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.FrameLayout
import com.google.android.material.bottomnavigation.BottomNavigationView
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.isInvisible
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentTransaction
import com.pet.yourpet.Fragments.details_fundacion
import com.pet.yourpet.Fragments.fundaciones
import com.pet.yourpet.Fragments.home
import com.pet.yourpet.Fragments.home.Cambio
import com.pet.yourpet.activities.AdopActivity

class MainActivity : AppCompatActivity(), Cambio, fundaciones.ItemFundacion {

    var fragmentHome: Fragment = home()
    var fragmentFundacion: Fragment = fundaciones()
    var fragmentDetalles: Fragment = details_fundacion()

    //Se cambia el fragmento segun el nav
    private val onNavigationItemSelectedListener = BottomNavigationView.OnNavigationItemSelectedListener { item ->
        var transactionH: FragmentTransaction = supportFragmentManager.beginTransaction()
        var transactionV: FragmentTransaction = supportFragmentManager.beginTransaction()

        var fragmentManager: FragmentManager = supportFragmentManager
        var fragmentActualC: Fragment? = fragmentManager.findFragmentById(R.id.container)
        var fragmentActualD: Fragment? = fragmentManager.findFragmentById(R.id.det_container)

        when (item.itemId) {
            R.id.navigation_home -> {
                if (resources.configuration.orientation == Configuration.ORIENTATION_PORTRAIT) {
                    if (fragmentActualC != null) {
                        transactionV.remove(fragmentActualC).replace(R.id.container, fragmentHome)
                    }
                    if (fragmentActualD != null) {
                        transactionV.remove(fragmentActualD)
                    } else {
                        transactionV.replace(R.id.container, fragmentHome)
                    }
                    transactionV.addToBackStack(null).commit()
                } else if (resources.configuration.orientation == Configuration.ORIENTATION_LANDSCAPE) {
                    if (fragmentActualC != null) {
                        transactionH.remove(fragmentActualC).replace(R.id.container, fragmentHome)
                    }
                    if (fragmentActualD != null) {
                        transactionH.remove(fragmentActualD)
                    } else {
                        transactionH.replace(R.id.container, fragmentHome)
                    }
                    transactionH.addToBackStack(null).commit()
                }
                return@OnNavigationItemSelectedListener true
            }
            R.id.navigation_dashboard -> {
                if (resources.configuration.orientation == Configuration.ORIENTATION_PORTRAIT) {
                    if (fragmentActualC != null) {
                        transactionV.remove(fragmentActualC).replace(R.id.container, fragmentFundacion)
                    }
                    if (fragmentActualD != null) {
                        transactionV.remove(fragmentActualD)
                    } else {
                        transactionV.replace(R.id.container, fragmentFundacion)
                    }
                    transactionV.addToBackStack(null).commit()
                } else if (resources.configuration.orientation == Configuration.ORIENTATION_LANDSCAPE) {
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
                transactionV.replace(R.id.container, fragmentHome)
            } else if (resources.configuration.orientation == Configuration.ORIENTATION_LANDSCAPE) {
                transactionH.replace(R.id.container, fragmentHome)
            }
            transactionV.commit()
            transactionH.commit()
        }
    }

    //Envio el numero de que boton se presiono
    override fun enviar(numero: Int) {
        var transaction1: FragmentTransaction = supportFragmentManager.beginTransaction()
        var fragmentManager: FragmentManager = supportFragmentManager
        var fragmentActualA: Fragment? = fragmentManager.findFragmentById(R.id.container)
        if (numero == 1) {
            if (resources.configuration.orientation == Configuration.ORIENTATION_PORTRAIT) {
                if (fragmentActualA != null) {
                    transaction1.remove(fragmentActualA).replace(R.id.container, fragmentFundacion)
                }
            } else if (resources.configuration.orientation == Configuration.ORIENTATION_LANDSCAPE) {
                if (fragmentActualA != null) {
                    transaction1.remove(fragmentActualA).replace(R.id.container, fragmentFundacion)
                }
            }
            transaction1.addToBackStack(null).commit()
        }
        if (numero == 2) {
            startActivity(Intent(this, AdopActivity::class.java))
        }
    }

    //Envio el nombre de la fundacion que se mostrara para los detalles
    override fun nombreItem(nombre: String) {

        var arg = Bundle()
        arg.putString("nombre", nombre)
        fragmentDetalles.setArguments(arg)

        var fragM: FragmentManager = supportFragmentManager
        var fragA: Fragment? = fragM.findFragmentById(R.id.container)
        var fragB: Fragment? = fragM.findFragmentById(R.id.det_container)
        var transitionH2: FragmentTransaction = supportFragmentManager.beginTransaction()
        var transitionV2: FragmentTransaction = supportFragmentManager.beginTransaction()

        if (resources.configuration.orientation == Configuration.ORIENTATION_PORTRAIT) {
            if (fragA != null) {
                transitionV2.replace(R.id.det_container, fragmentDetalles)
            }
            if (fragB != null) {
                transitionV2.remove(fragmentDetalles).replace(R.id.det_container, fragmentDetalles)
            } else {
                transitionV2.replace(R.id.det_container, fragmentDetalles)
            }
        }
        if (resources.configuration.orientation == Configuration.ORIENTATION_LANDSCAPE) {
            if (fragA != null) {
                if (fragA == fragmentDetalles) {
                    transitionV2.replace(R.id.det_container, fragmentDetalles)
                }
            }
            if (fragB != null) {
                transitionV2.remove(fragB).replace(R.id.det_container, fragmentDetalles)
            } else {
                transitionV2.replace(R.id.det_container, fragmentDetalles)
            }
        }
        transitionV2.addToBackStack(null)
        transitionV2.commit()
        transitionH2.addToBackStack(null)
        transitionH2.commit()

    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val navView: BottomNavigationView = findViewById(R.id.nav_view)

        transicion(savedInstanceState)

        navView.setOnNavigationItemSelectedListener(onNavigationItemSelectedListener)
    }


}
