package com.pet.yourpet.Fragments


import android.content.Context
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import com.bumptech.glide.Glide
import com.google.firebase.database.*
import com.pet.yourpet.R
import kotlinx.android.synthetic.main.fragment_details_consejos.*


// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 *
 */
class details_consejos : Fragment() {

    fun recibo(nombre: String) {
        var database: FirebaseDatabase = FirebaseDatabase.getInstance()
        var myRef: DatabaseReference = database.getReference("consejos").child(nombre)
        myRef.addValueEventListener( object: ValueEventListener {
            override fun onCancelled(p0: DatabaseError) {
                TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
            }

            override fun onDataChange(p0: DataSnapshot) {
                var nombre: String = p0.getValue().toString()
                tv_detailsC_titulo.text = nombre
                var descripcion: String = p0.getValue().toString()
                tv_detailsC_contenido.text = descripcion


            }

        })
    }

    private var context = this
    var textTitulo: TextView? = null
    var textContenido: TextView? = null
    var nombre: String? = ""

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        var view = inflater.inflate(R.layout.fragment_details_consejos, container, false)

        nombre = arguments?.getString("nombreC")
        var database: FirebaseDatabase = FirebaseDatabase.getInstance()
        var myRef: DatabaseReference = database.getReference("consejos").child(nombre!!)
        myRef.addValueEventListener(object : ValueEventListener {
            override fun onCancelled(p0: DatabaseError) {
                TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
            }

            override fun onDataChange(p0: DataSnapshot) {
                var imagen: String = p0.child("imagen").getValue().toString()
                Glide.with(context)
                    .load(p0.child("imagen").getValue().toString())
                    .placeholder(R.drawable.ic_launcher_background)
                    .into(iv_detailsC_logo)
                var nombre: String = p0.child("nombre_fundacion").getValue().toString()
                context.apply {
                    tv_detailsC_titulo?.text = nombre
                }
                var descripcion: String = p0.child("descripcion").getValue().toString()
                tv_detailsC_contenido?.text = descripcion
            }

        })

        textTitulo = view.findViewById(R.id.tv_detailsC_titulo)
        textContenido = view.findViewById(R.id.tv_detailsC_contenido)

        return  view
    }

    override fun onAttach(context: Context) {
        super.onAttach(context)
    }

}
