package com.pet.yourpet.Fragments

import android.content.Context
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button

import com.pet.yourpet.R
import com.pet.yourpet.Fragments.Adopciones.Other as YourPetAdopOther


class Adopciones : Fragment() {

    lateinit var btn_adoptar: Button
    lateinit var btn_dar_adop: Button
    lateinit var btn_mi_adop: Button
    lateinit var btn_land_adoptar: Button
    lateinit var btn_land_dar_adop: Button
    lateinit var btn_land_mi_adop: Button
    var opcion:Other? = null

    interface Other{
        fun change(op:Int)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_adopciones, container, false)

        btn_adoptar = view.findViewById(R.id.btn_adoptar)
        btn_dar_adop = view.findViewById(R.id.btn_dar_adopcion)
        btn_mi_adop = view.findViewById(R.id.btn_mis_adopciones)

        btn_land_adoptar = view.findViewById(R.id.btn_land_adoptar)
        btn_land_dar_adop = view.findViewById(R.id.btn_land_dar_adopcion)
        btn_land_mi_adop = view.findViewById(R.id.btn_land_mis_adopciones)

        btn_adoptar.setOnClickListener { item -> opcion?.change(1) }
        btn_land_adoptar.setOnClickListener { item -> opcion?.change(1) }
        btn_dar_adop.setOnClickListener { item -> opcion?.change(2) }
        btn_land_dar_adop.setOnClickListener { item -> opcion?.change(2) }
        btn_mi_adop.setOnClickListener { item -> opcion?.change(3) }
        btn_land_mi_adop.setOnClickListener { item -> opcion?.change(3) }

        return view
    }

    override fun onAttach(context: Context) {
        opcion = context as Other
        super.onAttach(context)
    }

}
