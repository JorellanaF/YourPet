package com.pet.yourpet.Adapters

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.pet.yourpet.Consejo
import com.pet.yourpet.R
import kotlinx.android.synthetic.main.cardview_consejos.view.*

class RecyclerConsejosAdapter (var consejos: List<Consejo>, listener: OnItemClickListener):
    RecyclerView.Adapter<RecyclerConsejosAdapter.ViewHolder>() {

    init{
        listener1 = listener
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.cardview_consejos, parent, false)
        return ViewHolder(view)
    }

    override fun getItemCount(): Int = consejos.size

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(consejos[position])
        holder.setOnClickListener()
    }

    interface OnItemClickListener {
        fun onItemClickListener(view: View)
    }

    companion object {
        private var listener1: OnItemClickListener? = null
    }

    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView), View.OnClickListener{
        override fun onClick(v: View?) {
            if (v != null) {
                listener1?.onItemClickListener(v)
            }
        }

        var context: Context = itemView.context

        fun setOnClickListener() {
            itemView.setOnClickListener(this)
        }

        fun bind(item: Consejo) = with(itemView) {

            Glide.with(itemView.context)
                .load(item.imagen)
                .placeholder(R.drawable.ic_launcher_background)
                .into(iv_consejo)

            tv_titulo.text = item.titulo
        }
    }
}