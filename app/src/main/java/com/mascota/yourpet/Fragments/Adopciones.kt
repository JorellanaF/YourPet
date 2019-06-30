package com.mascota.yourpet.Fragments

import android.content.Context
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup

import com.mascota.yourpet.R
import kotlinx.android.synthetic.main.fragment_adopciones.*



class Adopciones : Fragment() {

    var opcion:Other? = null

    interface Other{
        fun change(op:Int)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_adopciones, container, false)

        return view
    }

    override fun onStart() {

        btn_adoptar.setOnClickListener{opcion?.change(1)}
        btn_dar_adopcion.setOnClickListener{opcion?.change(2)}
        btn_mis_adopciones.setOnClickListener{opcion?.change(3)}

        super.onStart()
    }

    override fun onAttach(context: Context) {
        opcion = context as Other
        super.onAttach(context)
    }

}
