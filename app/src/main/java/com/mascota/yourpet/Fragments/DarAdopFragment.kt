package com.mascota.yourpet.Fragments

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.provider.MediaStore
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup

import com.mascota.yourpet.R
import kotlinx.android.synthetic.main.fragment_dar_adop.*

class DarAdopFragment : Fragment() {

    var opcion:Formulario? = null

    interface Formulario{
        fun sendInfo(op:Int)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_dar_adop, container, false)
    }

    override fun onStart() {

        btn_upload_pic.setOnClickListener {opcion?.sendInfo(1)}
        btn_acep.setOnClickListener { opcion?.sendInfo(2) }
        btn_cancel.setOnClickListener { opcion?.sendInfo(3) }


        super.onStart()
    }

    override fun onAttach(context: Context) {

        opcion = context as Formulario

        super.onAttach(context)
    }

}
