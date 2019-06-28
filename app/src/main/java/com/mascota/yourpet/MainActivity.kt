package com.mascota.yourpet

import android.app.DownloadManager
import android.content.Intent
import android.content.res.Configuration
import android.net.Uri
import android.os.Bundle
import android.os.Environment.DIRECTORY_DOWNLOADS
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentTransaction
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.google.firebase.storage.FirebaseStorage
import com.google.firebase.storage.StorageReference
import com.mascota.yourpet.Fragments.*
import com.mascota.yourpet.Fragments.home.Cambio
import com.mascota.yourpet.activities.AdopActivity
import com.mascota.yourpet.activities.leyes

class MainActivity : AppCompatActivity(), Cambio, fundaciones.ItemFundacion, consejos.ItemConsejo {

    var fragmentHome: Fragment = home()
    var fragmentFundacion: Fragment = fundaciones()
    var fragmentDetalles: Fragment = details_fundacion()
    var fragmentConsejos: Fragment = consejos()
    var fragmentDetallesC: Fragment = details_consejos()

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
                if (resources.configuration.orientation == Configuration.ORIENTATION_PORTRAIT) {
                    if (fragmentActualC != null) {
                        transactionV.remove(fragmentActualC).replace(R.id.container, fragmentConsejos)
                    }
                    if (fragmentActualD != null) {
                        transactionV.remove(fragmentActualD)
                    } else {
                        transactionV.replace(R.id.container, fragmentConsejos)
                    }
                    transactionV.addToBackStack(null).commit()
                } else if (resources.configuration.orientation == Configuration.ORIENTATION_LANDSCAPE) {
                    if (fragmentActualC != null) {
                        transactionH.remove(fragmentActualC).replace(R.id.container, fragmentConsejos)
                    }
                    if (fragmentActualD != null) {
                        transactionH.remove(fragmentActualD)
                    } else {
                        transactionH.replace(R.id.container, fragmentConsejos)
                    }
                    transactionH.addToBackStack(null).commit()
                }
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
        var transaction4: FragmentTransaction = supportFragmentManager.beginTransaction()
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
        if(numero == 3){
           //download()
           startActivity(Intent(this, leyes::class.java))
        }
        if (numero == 4) {
            if (resources.configuration.orientation == Configuration.ORIENTATION_PORTRAIT) {
                if (fragmentActualA != null) {
                    transaction4.remove(fragmentActualA).replace(R.id.container, fragmentConsejos)
                }
            } else if (resources.configuration.orientation == Configuration.ORIENTATION_LANDSCAPE) {
                if (fragmentActualA != null) {
                    transaction4.remove(fragmentActualA).replace(R.id.container, fragmentConsejos)
                }
            }
            transaction4.addToBackStack(null).commit()
        }
    }

    override fun consejoItem(consejo: String) {
        var arg = Bundle()
        arg.putString("nombreC", consejo)
        fragmentDetallesC.setArguments(arg)

        var fragM: FragmentManager = supportFragmentManager
        var fragA: Fragment? = fragM.findFragmentById(R.id.container)
        var fragB: Fragment? = fragM.findFragmentById(R.id.det_container)
        var transitionH2: FragmentTransaction = supportFragmentManager.beginTransaction()
        var transitionV2: FragmentTransaction = supportFragmentManager.beginTransaction()

        if (resources.configuration.orientation == Configuration.ORIENTATION_PORTRAIT) {
            if (fragA != null) {
                transitionV2.replace(R.id.container, fragmentDetallesC)
            }
            if (fragB != null) {
                transitionV2.remove(fragmentDetallesC).replace(R.id.container, fragmentDetallesC)
            } else {
                transitionV2.replace(R.id.container, fragmentDetallesC)
            }
        }
        if (resources.configuration.orientation == Configuration.ORIENTATION_LANDSCAPE) {
            if (fragA != null) {
                if (fragA == fragmentDetallesC) {
                    transitionV2.replace(R.id.det_container, fragmentDetallesC)
                }
            }
            if (fragB != null) {
                transitionV2.remove(fragB).replace(R.id.det_container, fragmentDetallesC)
            } else {
                transitionV2.replace(R.id.det_container, fragmentDetallesC)
            }
        }
        transitionV2.addToBackStack(null)
        transitionV2.commit()
        transitionH2.addToBackStack(null)
        transitionH2.commit()

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
                transitionV2.replace(R.id.container, fragmentDetalles)
            }
            if (fragB != null) {
                transitionV2.remove(fragmentDetalles).replace(R.id.container, fragmentDetalles)
            } else {
                transitionV2.replace(R.id.container, fragmentDetalles)
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

    lateinit var firebaseStorage: FirebaseStorage
    lateinit var storageReference: StorageReference

    lateinit var botonLeyes: Button

    lateinit var ref: StorageReference

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        /*botonLeyes = findViewById(R.id.btn_leyes)

        botonLeyes.setOnClickListener {
            download()
        }*/



        val navView: BottomNavigationView = findViewById(R.id.nav_view)

        transicion(savedInstanceState)

        navView.setOnNavigationItemSelectedListener(onNavigationItemSelectedListener)
    }

   fun download(){

        storageReference = firebaseStorage?.getReference()
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

}
