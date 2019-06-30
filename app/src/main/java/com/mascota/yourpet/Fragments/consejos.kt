package com.mascota.yourpet.Fragments


import android.content.Context
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.StaggeredGridLayoutManager
import com.google.firebase.database.*
import com.mascota.yourpet.Adapters.RecyclerConsejosAdapter
import com.mascota.yourpet.Consejo
import com.mascota.yourpet.R
import kotlinx.android.synthetic.main.cardview_consejos.view.*


// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 *
 */
class consejos : Fragment() {

    interface ItemConsejo {
        fun consejoItem(consejo: String)
    }

    lateinit var recyclerConsejos: RecyclerView
    var consejosList: ArrayList<Consejo> = ArrayList()
    var itemclick: ItemConsejo? = null

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        var view = inflater.inflate(R.layout.fragment_consejos, container, false)

        recyclerConsejos = view.findViewById(R.id.rv_consejos)

        var adapterF = RecyclerConsejosAdapter(
            consejosList,
            object : RecyclerConsejosAdapter.OnItemClickListener {
                override fun onItemClickListener(view: View) {
                    itemclick?.consejoItem(view.tv_titulo.text.toString())
                }

            })
        recyclerConsejos.apply {
            layoutManager = StaggeredGridLayoutManager(2, LinearLayoutManager.VERTICAL)
            adapter = adapterF
            setHasFixedSize(true)
        }

        var database: FirebaseDatabase = FirebaseDatabase.getInstance()
        var myRef: DatabaseReference = database.getReference("consejos")
        myRef.addValueEventListener(object : ValueEventListener {
            override fun onCancelled(p0: DatabaseError) {
            }

            override fun onDataChange(p0: DataSnapshot) {
                consejosList.removeAll(consejosList)
                for (snapshot: DataSnapshot in p0.children) {
                    var consejo1: Consejo = snapshot.getValue(Consejo::class.java)!!
                    consejosList.add(consejo1)
                }
                adapterF.notifyDataSetChanged()
            }

        })

        return view
    }

    override fun onAttach(context: Context) {
        itemclick = context as ItemConsejo
        super.onAttach(context)
    }

}
