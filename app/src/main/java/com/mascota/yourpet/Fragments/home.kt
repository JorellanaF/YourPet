package com.mascota.yourpet.Fragments


import android.content.Context
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ImageView
import com.mascota.yourpet.R
import com.mascota.yourpet.Fragments.home.Cambio as YourpetHomeCambio


// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 *
 */
class home: Fragment(){
    lateinit var btn1: ImageView
    lateinit var btn2: ImageView
    lateinit var btn3: ImageView
    lateinit var btn4: ImageView
    var cambio: Cambio? = null

    interface Cambio{
        fun enviar(numero: Int)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        var view = inflater.inflate(R.layout.fragment_home, container, false)

        // Inflate the layout for this fragment
        btn1 = view.findViewById(R.id.btn_fundaciones)
        btn2 = view.findViewById(R.id.btn_adopciones)
        btn3 = view.findViewById(R.id.btn_leyes)
        btn4 = view.findViewById(R.id.btn_consejos)
        btn1.setOnClickListener { item ->
            cambio?.enviar(1)
        }
        btn2.setOnClickListener { item ->
            cambio?.enviar(2)
        }
        btn3.setOnClickListener { item ->
            cambio?.enviar(3)
        }
        btn4.setOnClickListener { item ->
            cambio?.enviar(4)
        }
        return view
    }

    override fun onAttach(context: Context) {
        cambio = context as Cambio
        super.onAttach(context)
    }

}
