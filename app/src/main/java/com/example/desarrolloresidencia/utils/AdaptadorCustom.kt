package com.example.desarrolloresidencia.utils

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.desarrolloresidencia.R

class AdaptadorCustom(var contexto: Context, items:ArrayList<Ubicacion>): RecyclerView.Adapter<AdaptadorCustom.ViewHolder>() {

    var items: ArrayList<Ubicacion> ?= null

    init {
        this.items = items
    }

    //crea el view holder y mete el archivo xml a la vista
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): AdaptadorCustom.ViewHolder {
        val vista = LayoutInflater.from(contexto).inflate(R.layout.template_puntos, parent, false)
        val viewHolder = ViewHolder(vista)
        //Log.d("onCreateViewHolder ","pasó")
        return viewHolder
    }

    override fun onBindViewHolder(holder: AdaptadorCustom.ViewHolder, position: Int) {
        val item = items?.get(position)
        holder._id?.text = "_Id: " + item?._id
        holder.id?.text = "Id: " + item?.id
        holder.fid?.text = "FId: " + item?.fid
        holder.ubicacion?.text = "Ubicación: " + item?.ubication
        holder.nombre?.text = "Nombre: " + item?.name
        holder.escenarioP?.text = "Escenario previo: " + item?.previousStage
        holder.escenario?.text = "Escenario: " + item?.currentStage
        holder.__V?.text = "__V: " + item?.__v

        if (item?.code !=""){
            holder.codigo?.text = "Código: " + item?.code
        }else{
            holder.codigo?.visibility = View.GONE
        }

    //Log.d("onBindViewHolder ","pasó")
    }

    override fun getItemCount(): Int {
        return items?.count()!!
        //Log.d("getItemCount","pasó")
    }

    class ViewHolder(vista: View): RecyclerView.ViewHolder(vista){
        var vista = vista
        var _id: TextView ?= null
        var id: TextView ?= null
        var fid: TextView ?= null
        var codigo: TextView ?= null
        var ubicacion: TextView ?= null
        var nombre: TextView ?= null
        var escenarioP: TextView ?= null
        var escenario: TextView ?= null
        var __V: TextView ?= null


        init {
            _id= vista.findViewById(R.id.TV_Id)
            id = vista.findViewById(R.id.TVID)
            fid= vista.findViewById(R.id.TVFId)
            codigo= vista.findViewById(R.id.TVCodigo)
            ubicacion= vista.findViewById(R.id.TVUbicacion)
            nombre= vista.findViewById(R.id.TVNombreTP)
            escenarioP= vista.findViewById(R.id.TVEscenarioPrevio)
            escenario= vista.findViewById(R.id.TVEscenario)
            __V= vista.findViewById(R.id.TV__V)
        }
    }


}