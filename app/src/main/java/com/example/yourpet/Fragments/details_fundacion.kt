package com.example.yourpet.Fragments


import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.yourpet.R
import com.google.firebase.database.*
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
        Log.d("recibido", "Si recibi siiiiiii")
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


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_details_fundacion, container, false)
        return view
    }


}
