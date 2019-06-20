package com.example.yourpet.Fragments


import android.content.Context
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Adapter
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.yourpet.Fundacion
import com.example.yourpet.R
import com.example.yourpet.RecyclerFundacionAdapter
import com.google.firebase.database.*
import kotlinx.android.synthetic.main.cardview_fundacion.*
import kotlinx.android.synthetic.main.cardview_fundacion.view.*
import kotlinx.android.synthetic.main.fragment_fundaciones.*


// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 *
 */
class fundaciones : Fragment(){

    lateinit var recyclerFundaciones: RecyclerView
    val fundacionList: ArrayList<Fundacion> = ArrayList()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        var view = inflater.inflate(R.layout.fragment_fundaciones, container, false)

        recyclerFundaciones = view.findViewById(R.id.rv_fundaciones)

        var adapterF = RecyclerFundacionAdapter(fundacionList)
        recyclerFundaciones.apply {
            layoutManager = LinearLayoutManager(context)
            adapter = adapterF
            setHasFixedSize(true)
        }

        var database: FirebaseDatabase = FirebaseDatabase.getInstance()
        var myRef: DatabaseReference = database.getReference("fundaciones")
        myRef.addValueEventListener( object : ValueEventListener {
            override fun onCancelled(p0: DatabaseError) {
            }

            override fun onDataChange(p0: DataSnapshot) {
                fundacionList.removeAll(fundacionList)
                for (snapshot: DataSnapshot in p0.children){
                    Log.d("entrar","Entro al for")
                    var fundacion1: Fundacion = snapshot.getValue(Fundacion::class.java)!!
                    fundacionList.add(fundacion1)
                }
                Log.d("entrar","Salio del for")
                adapterF.notifyDataSetChanged()
            }

        })

        return view
    }

    override fun onAttach(context: Context) {
        super.onAttach(context)
    }

}
