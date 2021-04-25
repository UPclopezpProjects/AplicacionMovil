package com.example.desarrolloresidencia.UI.DatosTrazabilidad

import android.content.Context
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.desarrolloresidencia.Network.model.Trazabilidad.Message
import com.example.desarrolloresidencia.Network.model.Trazabilidad.consulta
import com.example.desarrolloresidencia.R
import com.example.desarrolloresidencia.utils.AdaptadorCustom
import com.example.desarrolloresidencia.utils.Ubicacion

class ListaPuntos : Fragment() {

    var lista:RecyclerView ?= null
    var puntos = ArrayList<Ubicacion>()
    var adaptador:AdaptadorCustom ?= null

    var layoutManager:RecyclerView.LayoutManager ?= null

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        /* var contexto = requireContext().applicationContext

        val puntos = ArrayList<Ubicacion>()
        puntos.add(Ubicacion(consulta.consulta!!.A.id, consulta.consulta!!.A.date, consulta.consulta!!.A.stage, consulta.consulta!!.A.description))
        puntos.add(Ubicacion(consulta.consulta!!.B.id, consulta.consulta!!.B.date, consulta.consulta!!.B.stage, consulta.consulta!!.B.description))
        puntos.add(Ubicacion(consulta.consulta!!.C.id, consulta.consulta!!.C.date, consulta.consulta!!.C.stage, consulta.consulta!!.C.description))
        puntos.add(Ubicacion(consulta.consulta!!.D.id, consulta.consulta!!.D.date, consulta.consulta!!.D.stage, consulta.consulta!!.D.description))

        lista = activity?.findViewById(R.id.lista)
        lista?.setHasFixedSize(true)

        layoutManager = LinearLayoutManager(context)
        lista?.layoutManager = layoutManager

        adaptador = AdaptadorCustom(contexto, puntos)
        lista?.adapter = adaptador

         */
        mapearpuntos()
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle? ): View? {
        // Inflate the layout for this fragment
        val vista = inflater.inflate(R.layout.fragment_lista_puntos, container, false)
        return vista

    }

    private fun mapearpuntos(){
        var contexto = requireContext().applicationContext
        val puntos = ArrayList<Ubicacion>()
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

        layoutManager = LinearLayoutManager(context)
        lista?.layoutManager = layoutManager

        adaptador = AdaptadorCustom(contexto, puntos)
        lista?.adapter = adaptador

    }


    }
