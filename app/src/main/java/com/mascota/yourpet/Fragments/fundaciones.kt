package com.mascota.yourpet.Fragments


import android.content.Context
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.StaggeredGridLayoutManager
import com.mascota.yourpet.Fundacion
import com.mascota.yourpet.R
import com.mascota.yourpet.Adapters.RecyclerFundacionAdapter
import com.google.firebase.database.*
import kotlinx.android.synthetic.main.cardview_fundacion.view.*


// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 *
 */
class fundaciones : Fragment() {

    interface ItemFundacion {
        fun nombreItem(nombre: String)
    }

    lateinit var recyclerFundaciones: RecyclerView
    var fundacionList: ArrayList<Fundacion> = ArrayList()
    var itemclick: ItemFundacion? = null

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        var view = inflater.inflate(R.layout.fragment_fundaciones, container, false)
        recyclerFundaciones = view.findViewById(R.id.rv_fundaciones)

        var adapterF = RecyclerFundacionAdapter(
            fundacionList,
            object : RecyclerFundacionAdapter.OnItemClickListener {
                override fun onItemClickListener(view: View) {
                    itemclick?.nombreItem(view.tv_nombre.text.toString())
                }

            })
        recyclerFundaciones.apply {
            layoutManager = StaggeredGridLayoutManager(2, LinearLayoutManager.VERTICAL)
            adapter = adapterF
            setHasFixedSize(true)
        }

        var database: FirebaseDatabase = FirebaseDatabase.getInstance()
        var myRef: DatabaseReference = database.getReference("fundaciones")
        myRef.addValueEventListener(object : ValueEventListener {
            override fun onCancelled(p0: DatabaseError) {
            }

            override fun onDataChange(p0: DataSnapshot) {
                fundacionList.removeAll(fundacionList)
                for (snapshot: DataSnapshot in p0.children) {
                    var fundacion1: Fundacion = snapshot.getValue(Fundacion::class.java)!!
                    fundacionList.add(fundacion1)
                }
                adapterF.notifyDataSetChanged()
            }

        })

        return view
    }

    override fun onAttach(context: Context) {
        itemclick = context as ItemFundacion
        super.onAttach(context)
    }

}
