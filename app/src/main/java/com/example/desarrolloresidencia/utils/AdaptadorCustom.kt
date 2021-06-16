package com.example.desarrolloresidencia.utils

import android.app.AlertDialog
import android.content.Context
import android.media.Image
import android.opengl.Visibility
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.desarrolloresidencia.R
import com.squareup.picasso.Picasso
import org.w3c.dom.Text

class AdaptadorCustom(items:ArrayList<Ubicacion>, var listener: ClickListener): RecyclerView.Adapter<AdaptadorCustom.ViewHolder>() {

    var items: ArrayList<Ubicacion> ?= null
    var navegacion: RecyclerV ?= null
    var context:Context ?= null


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
        //holder.codigo?.text = item?.code
        //Log.e("codigoQR", "${item?.code.toString()}")
        holder.ubicacion = item?.ubication
        holder.nombre?.text = item?.name

        //holder.escenario?.text = item?.currentStage
        when (item?.currentStage) {
            "Productor" -> holder.escenario?.setImageResource(R.drawable.productor_round)
            "Acopio" -> holder.escenario?.setImageResource(R.drawable.acopio_round)
            "Carrier"-> holder.escenario?.setImageResource(R.drawable.transportista_round)
            "Merchant"->holder.escenario?.setImageResource(R.drawable.comerciante_round)
            "null"->holder.escenario?.visibility = View.GONE
        }


        Picasso.get()
            .load(item?.image)
            .placeholder(R.drawable.logo)
            .error(R.drawable.ic_twotone_error_24)
            .into(holder.imagen);
        //holder.__V?.text = item?.__v.toString()

        if(item?.previousStage != "null"){
            //holder.escenarioP?.text = item?.previousStage
            when (item?.previousStage) {
                "Productor" -> holder.escenarioP?.setImageResource(R.drawable.productor_round)
                "Acopio" -> holder.escenarioP?.setImageResource(R.drawable.acopio_round)
                "Carrier"-> holder.escenarioP?.setImageResource(R.drawable.transportista_round)
                "Merchant"->holder.escenarioP?.setImageResource(R.drawable.comerciante_round)
                "null"->holder.escenarioP?.visibility = View.GONE
            }
        }else{
            holder.anterior?.visibility = View.GONE
            holder.escenarioP?.visibility = View.GONE
        }

        if (position==0){
            holder.siguiente?.visibility = View.GONE
            holder.escenario?.visibility = View.GONE
        }

        holder.anterior?.setOnClickListener {
            //holder.lista?.scrollToPosition(position-1)
            Log.e("Adaptador custom, anterior", "${position+1}")
            navegacion?.anterior(position+1)
        }

        holder.siguiente?.setOnClickListener {
            //holder.lista?.scrollToPosition(position+1)
            Log.e("Adaptador custom, siguiente", "${position-1}")
            navegacion?.siguiente(position-1)
        }

        holder.descripcion?.setOnClickListener {
            navegacion?.descripcion(item!!.description)
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
        var fidE: TextView ?= null
        //var codigo: TextView ?= null
        //var codigoE:TextView ?= null
        var ubicacion: String ?= null
        var nombre: TextView ?= null
        var escenarioP: ImageView ?= null
        var escenario: ImageView ?= null
        var imagen : ImageView ?= null
        //var __V: TextView ?= null
        var listener: ClickListener ?= null
        var siguiente:Button ?= null
        var anterior:Button ?= null
        var lista:RecyclerView ?= null
        var descripcion: Button ?= null


        init {
            //_id= vista.findViewById(R.id.TV_Id)
            //id = vista.findViewById(R.id.TVID)
            //fid= vista.findViewById(R.id.TVFId)
            //fidE= vista.findViewById(R.id.textView10)
            //codigo= vista.findViewById(R.id.TVCodigo)
            //codigoE= vista.findViewById(R.id.textView15)
            ubicacion= ""
            nombre= vista.findViewById(R.id.TVNombreTP)
            escenarioP= vista.findViewById(R.id.imageView17)
            escenario= vista.findViewById(R.id.imageView18)
            imagen= vista.findViewById(R.id.imageView10)
            //__V= vista.findViewById(R.id.TV__V)
            this.listener = listener
            vista.setOnClickListener(this)
            siguiente= vista.findViewById(R.id.BTNSiguienteE)
            anterior = vista.findViewById(R.id.BTNEscenarioP)
            lista= vista.findViewById(R.id.lista)
            descripcion= vista.findViewById(R.id.BTNDescripcion)
        }

        override fun onClick(v: View?) {
            this.listener?.onClick(v!!, adapterPosition)
        }


    }

}