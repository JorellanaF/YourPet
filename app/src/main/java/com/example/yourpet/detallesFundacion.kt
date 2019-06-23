package com.example.yourpet

import android.annotation.SuppressLint
import android.content.Context
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.ImageView
import android.widget.TextView
import com.bumptech.glide.Glide
import com.google.firebase.database.*
import kotlinx.android.synthetic.main.activity_detalles_fundacion.*
import kotlinx.android.synthetic.main.fragment_details_fundacion.*
import kotlinx.android.synthetic.main.fragment_details_fundacion.tv_detail_contacto
import kotlinx.android.synthetic.main.fragment_details_fundacion.tv_detail_descripcion
import kotlinx.android.synthetic.main.fragment_details_fundacion.tv_detail_nombre

class detallesFundacion : AppCompatActivity() {

    var context: Context = this

    var imagen: String? = null
    var nombre: String? = null
    var descripcion: String? = null
    var contacto: String? = null

    var detalleNombre: TextView? = null
    var detalleDescripcion: TextView? = null
    var detalleContacto: TextView? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detalles_fundacion)

        nombre = intent.getStringExtra("nombre")

        detalleNombre = findViewById(R.id.tv_detalle_nombre)
        detalleDescripcion = findViewById(R.id.tv_detalle_descripcion)
        detalleContacto = findViewById(R.id.tv_detalle_contacto)

        var database: FirebaseDatabase = FirebaseDatabase.getInstance()
        var myRef: DatabaseReference = database.getReference("fundaciones").child(nombre.toString())
        myRef.addValueEventListener(object : ValueEventListener {
            override fun onCancelled(p0: DatabaseError) {
                TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
            }

            override fun onDataChange(p0: DataSnapshot) {
                var imagen: String = p0.child("imagen").getValue().toString()
                Glide.with(context.applicationContext)
                    .load(p0.child("imagen").getValue().toString())
                    .placeholder(R.drawable.ic_launcher_background)
                    .into(iv_detalle_logo)
                var nombre: String = p0.child("nombre_fundacion").getValue().toString()
                context.apply {
                    detalleNombre?.text = nombre
                }
                var descripcion: String = p0.child("descripcion").getValue().toString()
                detalleDescripcion?.text = descripcion
                var contacto: String = p0.child("contacto").getValue().toString()
                detalleContacto?.text = contacto
            }

        })

    }

    fun relleno(fund: String){

        var database: FirebaseDatabase = FirebaseDatabase.getInstance()
        var myRef: DatabaseReference = database.getReference("fundaciones").child(fund)
        myRef.addValueEventListener(object : ValueEventListener {
            override fun onCancelled(p0: DatabaseError) {
                TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
            }

            override fun onDataChange(p0: DataSnapshot) {
                var imagen: String = p0.child("imagen").getValue().toString()
                /*Glide.with(context.applicationContext)
                    .load(p0.child("imagen").getValue().toString())
                    .placeholder(R.drawable.ic_launcher_background)
                    .into(iv_detalle_logo)*/
                var nombre: String = p0.child("nombre_fundacion").getValue().toString()
                context.apply {
                    detalleNombre?.text = nombre
                }
                var descripcion: String = p0.child("descripcion").getValue().toString()
                detalleDescripcion?.text = descripcion
                var contacto: String = p0.child("contacto").getValue().toString()
                detalleContacto?.text = contacto
            }

        })
    }

}
