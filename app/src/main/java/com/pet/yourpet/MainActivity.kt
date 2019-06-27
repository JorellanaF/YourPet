package com.pet.yourpet

import android.app.DownloadManager
import android.content.Intent
import android.content.res.Configuration
import android.net.Uri
import android.os.Bundle
import android.os.Environment.DIRECTORY_DOWNLOADS
import android.view.View
import android.widget.Button
import com.google.android.material.bottomnavigation.BottomNavigationView
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentTransaction
import com.google.firebase.storage.FirebaseStorage
import com.google.firebase.storage.StorageReference
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
        if (numero == 2){
            startActivity(Intent(this,AdopActivity::class.java))
        }
    }

    //Envio el nombre de la fundacion que se mostrara para los detalles
    override fun nombreItem(nombre: String) {
        var fragM: FragmentManager = supportFragmentManager
        var fragA: Fragment? = fragM.findFragmentById(R.id.container)
        var fragB: Fragment? = fragM.findFragmentById(R.id.det_container)
        var transitionH2: FragmentTransaction = supportFragmentManager.beginTransaction()
        var transitionV2: FragmentTransaction = supportFragmentManager.beginTransaction()
        var arg = Bundle()
        arg.putString("nombre", nombre)
        fragmentDetalles.setArguments(arg)
        if (resources.configuration.orientation == Configuration.ORIENTATION_PORTRAIT) {
            if(fragA != null){
                transitionV2.remove(fragmentDetalles).replace(R.id.container, fragmentDetalles)
            }
            if(fragB != null){
                transitionV2.remove(fragmentDetalles)
            }
            else{
                transitionV2.replace(R.id.container, fragmentDetalles)
            }
            transitionV2.addToBackStack(null)
            transitionV2.commit()
        } else if (resources.configuration.orientation == Configuration.ORIENTATION_LANDSCAPE) {
            if(fragA != null){
                transitionH2.remove(fragmentDetalles)
            }
            if(fragB != null){
                transitionH2.remove(fragmentDetalles).replace(R.id.det_container, fragmentDetalles)
            }
            else{
                transitionH2.replace(R.id.det_container, fragmentDetalles)
            }
            transitionH2.addToBackStack(null)
            transitionH2.commit()
        }
    }

    lateinit var firebaseStorage: FirebaseStorage
    lateinit var storageReference: StorageReference

    lateinit var botonLeyes: Button

    lateinit var ref: StorageReference

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        botonLeyes = findViewById(R.id.btn_leyes)

        botonLeyes.setOnClickListener {
            download()
        }

        fun download(){

            storageReference = firebaseStorage.getReference()
            ref = storageReference.child("Leyes-Contra-Maltrato-Animal-SV.pdf")

            ref.downloadUrl.addOnSuccessListener {

                var url = Uri.parse(toString())
                downloadFiles(this, "Leyes-Contra-Maltrato-Animal_sv", ".pdf", DIRECTORY_DOWNLOADS,url.toString())

            }.addOnFailureListener {

            }

        }

        fun downloadFiles(context: MainActivity, fileName:String, fileExtenion:String, destinationDirectory:String, url:String){
            val downloadManager = context as DownloadManager
            val uri = Uri.parse(url)
            val request = DownloadManager.Request(uri)
            request.setNotificationVisibility(DownloadManager.Request.VISIBILITY_VISIBLE_NOTIFY_COMPLETED)
            request.setDestinationInExternalFilesDir(this, destinationDirectory, fileName+fileExtenion)
        }

        val navView: BottomNavigationView = findViewById(R.id.nav_view)
        /*if (savedInstanceState != null) {
            fragmentHome = supportFragmentManager.getFragment(savedInstanceState, "home")!!
           // fragmentFundacion = supportFragmentManager.getFragment(savedInstanceState, "Nombre")!!
        }*/

        transicion(savedInstanceState)

        navView.setOnNavigationItemSelectedListener(onNavigationItemSelectedListener)
    }

    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)
        // supportFragmentManager.putFragment(outState, "home", fragmentHome)
        //supportFragmentManager.putFragment(outState,"Nombre", fragmentFundacion)
    }

}
