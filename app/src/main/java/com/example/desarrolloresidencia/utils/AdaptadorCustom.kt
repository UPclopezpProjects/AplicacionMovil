package com.example.desarrolloresidencia.utils

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.desarrolloresidencia.R

class AdaptadorCustom(items:ArrayList<Ubicacion>, var listener: ClickListener): RecyclerView.Adapter<AdaptadorCustom.ViewHolder>() {

    var items: ArrayList<Ubicacion> ?= null

    init {
        this.items = items
    }

    //crea el view holder y mete el archivo xml a la vista
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): AdaptadorCustom.ViewHolder {
        val vista = LayoutInflater.from(parent.context).inflate(R.layout.template_puntos, parent, false)
        val viewHolder = ViewHolder(vista, listener)
        //Log.d("onCreateViewHolder ","pasó")
        return viewHolder
    }

    override fun onBindViewHolder(holder: AdaptadorCustom.ViewHolder, position: Int) {
        val item = items?.get(position)
        //holder._id?.text = item?._id
        //holder.id?.text = item?.id
        //holder.fid?.text = item?.fid
        //holder.ubicacion?.text = item?.ubication
        holder.nombre?.text = item?.name
        holder.escenarioP?.text = item?.previousStage
        holder.escenario?.text = item?.currentStage
        //holder.__V?.text = item?.__v.toString()

        /*
        if (item?.code !=""){
            holder.codigo?.text = item?.code
        }else{
            holder.codigo?.visibility = View.GONE
        }*/

        if (item?.previousStage !="null"){
            holder.escenarioP?.text = item?.previousStage
        }else{
            holder.escenarioP?.visibility = View.GONE
            holder.escenarioPE?.visibility = View.GONE
        }

    }

    override fun getItemCount(): Int {
        return items?.count()!!
        //Log.d("getItemCount","pasó")
    }

    class ViewHolder(vista: View, listener: ClickListener): RecyclerView.ViewHolder(vista), View.OnClickListener{
        var vista = vista
        //var _id: TextView ?= null
        //var id: TextView ?= null
        //var fid: TextView ?= null
        //var codigo: TextView ?= null
        //var ubicacion: TextView ?= null
        var nombre: TextView ?= null
        var escenarioP: TextView ?= null
        var escenarioPE: TextView ?= null
        var escenario: TextView ?= null
        //var __V: TextView ?= null
        var listener: ClickListener ?= null


        init {
            //_id= vista.findViewById(R.id.TV_Id)
            //id = vista.findViewById(R.id.TVID)
            //fid= vista.findViewById(R.id.TVFId)
            //codigo= vista.findViewById(R.id.TVCodigo)
            //ubicacion= vista.findViewById(R.id.TVUbicacion)
            nombre= vista.findViewById(R.id.TVNombreTP)
            escenarioP= vista.findViewById(R.id.TVEscenarioPrevio)
            escenario= vista.findViewById(R.id.TVEscenario)
            escenarioPE= vista.findViewById(R.id.textView18)
            //__V= vista.findViewById(R.id.TV__V)
            this.listener = listener
            vista.setOnClickListener(this)
        }

        override fun onClick(v: View?) {
            this.listener?.onClick(v!!, adapterPosition)
        }
    }


}