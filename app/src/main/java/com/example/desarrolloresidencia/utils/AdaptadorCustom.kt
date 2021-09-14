package com.example.desarrolloresidencia.utils

import android.app.AlertDialog
import android.content.Context
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
import com.example.desarrolloresidencia.UI.DatosTrazabilidad.ListaPuntos
import com.squareup.picasso.Picasso

class AdaptadorCustom(items:ArrayList<Ubicacion>, var listener: ClickListener): RecyclerView.Adapter<AdaptadorCustom.ViewHolder>() {

    var items: ArrayList<Ubicacion> ?= null
    var navegacion: RecyclerV ?= null
    var context:Context ?= null
    var camara: ListaPuntos.MoverCamara?= null

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
        //var tamano = items?.size
        var ultimo = items?.size?.minus(1)




        if (position == ultimo){
            holder.anterior?.visibility = View.INVISIBLE
            holder.escenarioP?.visibility = View.INVISIBLE
        } else {
            holder.anterior?.visibility = View.VISIBLE
            holder.escenarioP?.visibility = View.VISIBLE

            when(items?.get(position+1)!!.currentStage){
                "Productor" -> holder.escenarioP?.setImageResource(R.drawable.productor_round)
                "Acopio" -> holder.escenarioP?.setImageResource(R.drawable.acopio_round)
                "Carrier" -> holder.escenarioP?.setImageResource(R.drawable.transportista_round)
                "Merchant" -> holder.escenarioP?.setImageResource(R.drawable.comerciante_round)
            }

            holder.anterior!!.setOnClickListener{
                if (items!!.get(position+1).currentStage != "Carrier") {
                    navegacion!!.anterior(position+1, items!!.get(position+1).ubication)
                } else {
                    navegacion!!.anterior(position+1, items!!.get(position+1).origin)
                }
            }

        }




        if (position == 0){
            holder.siguiente?.visibility = View.INVISIBLE
            holder.escenario?.visibility = View.INVISIBLE
        } else {
            holder.siguiente?.visibility = View.VISIBLE
            holder.escenario?.visibility = View.VISIBLE

            when(items?.get(position-1)!!.currentStage){
                "Productor" -> holder.escenario?.setImageResource(R.drawable.productor_round)
                "Acopio" -> holder.escenario?.setImageResource(R.drawable.acopio_round)
                "Carrier" -> holder.escenario?.setImageResource(R.drawable.transportista_round)
                "Merchant" -> holder.escenario?.setImageResource(R.drawable.comerciante_round)
            }

            holder.siguiente!!.setOnClickListener {
                if (items!!.get(position-1).currentStage != "Carrier") {
                    navegacion!!.anterior(position-1, items!!.get(position-1).ubication)
                } else {
                    navegacion!!.anterior(position-1, items!!.get(position-1).origin)
                }
            }
        }






        //el_chupelamigo@hotmail.com

        //holder.ubicacion = item?.ubication
        holder.nombre?.text = item?.name+"-"+position


            Picasso.get()
                .load(item?.image)
                .placeholder(R.drawable.logo)
                .error(R.drawable.ic_twotone_error_24)
                .into(holder.imagen)

            holder.descripcion?.setOnClickListener {
                navegacion?.descripcion(item!!.description)
                //camara?.obtenerPosicion(items?.get(position-1)!!.ubication)
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
        //var ubicacion: String ?= null
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
            //ubicacion= ""
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