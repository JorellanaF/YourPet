package com.example.yourpet.Fragments


import android.content.Context
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import com.bumptech.glide.Glide
import com.example.yourpet.R
import com.google.firebase.database.*
import kotlinx.android.synthetic.main.activity_detalles_fundacion.*
import kotlinx.android.synthetic.main.cardview_fundacion.*
import kotlinx.android.synthetic.main.fragment_details_fundacion.*


// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 *
 */
class details_fundacion : Fragment(){

    fun recibir(nombre: String) {
        var database: FirebaseDatabase = FirebaseDatabase.getInstance()
        var myRef: DatabaseReference = database.getReference("fundaciones").child(nombre)
        myRef.addValueEventListener( object: ValueEventListener {
            override fun onCancelled(p0: DatabaseError) {
                TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
            }

            override fun onDataChange(p0: DataSnapshot) {
                var nombre: String = p0.getValue().toString()
                tv_detail_nombre.text = nombre
                var descripcion: String = p0.getValue().toString()
                tv_detail_descripcion.text = descripcion
                var contacto: String = p0.getValue().toString()
                tv_detail_contacto.text = contacto

            }

        })
    }

    private var context = this
    var textNombre: TextView? = null
    var textDescripcion: TextView? = null
    var textContacto: TextView? = null
    var nombre: String? = ""

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_details_fundacion, container, false)

        nombre = arguments?.getString("nombre")
        var database: FirebaseDatabase = FirebaseDatabase.getInstance()
        var myRef: DatabaseReference = database.getReference("fundaciones").child(nombre!!)
        myRef.addValueEventListener(object : ValueEventListener {
            override fun onCancelled(p0: DatabaseError) {
                TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
            }

            override fun onDataChange(p0: DataSnapshot) {
                var imagen: String = p0.child("imagen").getValue().toString()
                Glide.with(context)
                    .load(p0.child("imagen").getValue().toString())
                    .placeholder(R.drawable.ic_launcher_background)
                    .into(iv_detail_logo)
                var nombre: String = p0.child("nombre_fundacion").getValue().toString()
                context.apply {
                    tv_detail_nombre?.text = nombre
                }
                var descripcion: String = p0.child("descripcion").getValue().toString()
                tv_detail_descripcion?.text = descripcion
                var contacto: String = p0.child("contacto").getValue().toString()
                tv_detail_contacto?.text = contacto
            }

        })

        textNombre = view.findViewById(R.id.tv_detail_nombre)
        textDescripcion = view.findViewById(R.id.tv_detail_descripcion)
        textContacto = view.findViewById(R.id.tv_detail_contacto)

        return view
    }

    fun prueba(nombre: String){
        textNombre?.text = nombre
    }

}
