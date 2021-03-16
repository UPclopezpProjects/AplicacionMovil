package com.example.desarrolloresidencia.utils

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.desarrolloresidencia.R
import com.example.desarrolloresidencia.UI.DatosTrazabilidad.ListaPuntos

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
        holder.id?.text = "Id: " + item?.id
        holder.date?.text = "Date: " + item?.date
        holder.stage?.text = "Stage: " + item?.stage
        holder.descripcion?.text = "Description: " + item?.descripcion
        //Log.d("onBindViewHolder ","pasó")
    }

    override fun getItemCount(): Int {
        return items?.count()!!
        //Log.d("getItemCount","pasó")
    }

    class ViewHolder(vista: View): RecyclerView.ViewHolder(vista){
        var vista = vista
        var id: TextView ?= null
        var date: TextView ?= null
        var stage: TextView ?= null
        var descripcion: TextView ?= null

        init {
            id = vista.findViewById(R.id.TVId)
            date = vista.findViewById(R.id.TVFecha)
            stage = vista.findViewById(R.id.TVEscenario)
            descripcion = vista.findViewById(R.id.TVDescripcion)
        }
    }


}