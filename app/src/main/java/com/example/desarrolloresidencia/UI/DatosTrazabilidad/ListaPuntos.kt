package com.example.desarrolloresidencia.UI.DatosTrazabilidad

import android.content.Context
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.desarrolloresidencia.Network.model.Trazabilidad.consulta
import com.example.desarrolloresidencia.R
import com.example.desarrolloresidencia.utils.AdaptadorCustom
import com.example.desarrolloresidencia.utils.ClickListener
import com.example.desarrolloresidencia.utils.Ubicacion
import java.lang.ClassCastException

class ListaPuntos : Fragment() {

    var lista:RecyclerView ?= null
    var puntos = ArrayList<Ubicacion>()
    var adaptador:AdaptadorCustom ?= null

    var layoutManager:RecyclerView.LayoutManager ?= null
    var listener: MoverCamara ?= null


    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        mapearpuntos()
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle? ): View? {
        // Inflate the layout for this fragment
        val vista = inflater.inflate(R.layout.fragment_lista_puntos, container, false)
        return vista
    }

    private fun mapearpuntos(){
        var contexto = requireContext().applicationContext
        //val puntos = ArrayList<Ubicacion>()
        for (i in 0..(consulta.consulta!!.size - 1)) {
                    if (consulta.consulta!!.get(i).code != null){
                        puntos.add(
                                Ubicacion(
                                        consulta.consulta!!.get(i).__v,
                                        consulta.consulta!!.get(i)._id,
                                        consulta.consulta!!.get(i).code,
                                        consulta.consulta!!.get(i).currentStage,
                                        consulta.consulta!!.get(i).fid,
                                        consulta.consulta!!.get(i).id,
                                        consulta.consulta!!.get(i).name,
                                        consulta.consulta!!.get(i).previousStage,
                                        consulta.consulta!!.get(i).ubication
                                )
                        )
                    }else{
                        puntos.add(
                                Ubicacion(
                                        consulta.consulta!!.get(i).__v,
                                        consulta.consulta!!.get(i)._id,
                                        "",
                                        consulta.consulta!!.get(i).currentStage,
                                        consulta.consulta!!.get(i).fid,
                                        consulta.consulta!!.get(i).id,
                                        consulta.consulta!!.get(i).name,
                                        consulta.consulta!!.get(i).previousStage,
                                        consulta.consulta!!.get(i).ubication
                                )
                        )
                    }


        }

        lista = activity?.findViewById(R.id.lista)

        lista?.setHasFixedSize(true)
        //lista?.layoutManager = LinearLayoutManager(ListaPuntos().context, LinearLayoutManager.HORIZONTAL, false)

        layoutManager = LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL, false)
        lista?.layoutManager = layoutManager

        //adaptador = AdaptadorCustom(contexto, puntos)
        adaptador = AdaptadorCustom(puntos, object:ClickListener{
            override fun onClick(vista: View, index: Int) {
                Log.e("EL CLICK", puntos.get(index).ubication)
                listener?.obtenerPosicion("${puntos.get(index).ubication}")
            }
        })
        lista?.adapter = adaptador

    }
    interface MoverCamara {
        fun obtenerPosicion(latlong:String){

        }
    }

    override fun onAttach(context: Context) {
        super.onAttach(context)
        try {
            listener = context as MoverCamara
        } catch (e: ClassCastException){
            throw ClassCastException(context.toString() + "debes implementar la interfaz")
        }
    }


    }
