package com.example.desarrolloresidencia.UI.DatosTrazabilidad

import android.app.AlertDialog
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
import com.example.desarrolloresidencia.utils.*
import java.lang.ClassCastException

class ListaPuntos : Fragment(), RecyclerV {

    var lista:RecyclerView ?= null
    var puntos = ArrayList<Ubicacion>()
    var adaptador:AdaptadorCustom ?= null

    var layoutManager:RecyclerView.LayoutManager ?= null
    var listener: MoverCamara ?= null


    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        mapearpuntos()
        adaptador?.navegacion = this
        adaptador?.context = context
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle? ): View? {
        // Inflate the layout for this fragment
        val vista = inflater.inflate(R.layout.fragment_lista_puntos, container, false)
        return vista
    }

    private fun mapearpuntos() {
        try {
            var contexto = requireContext().applicationContext
            //val puntos = ArrayList<Ubicacion>()
            if (responseUser.message != null) {
                for (i in 0..(consulta.consulta!!.size - 1)) {
                    Log.d("antigua foto", consulta.consulta!!.get(i).image)
                    var nuevaI = consulta.consulta!!.get(i).image.replace("http://0.0.0.0", "http://52.202.214.13")
                    Log.e("nueva imagen", nuevaI)
                    if (consulta.consulta!!.get(i).code != null) {
                        puntos.add(
                                Ubicacion(
                                        consulta.consulta!!.get(i).__v,
                                        consulta.consulta!!.get(i)._id,
                                        consulta.consulta!!.get(i).code,
                                        consulta.consulta!!.get(i).currentStage,
                                        consulta.consulta!!.get(i).description,
                                        consulta.consulta!!.get(i).fid,
                                        consulta.consulta!!.get(i).id,
                                        //consulta.consulta!!.get(i).image,
                                        nuevaI,
                                        consulta.consulta!!.get(i).name,
                                        consulta.consulta!!.get(i).previousStage,
                                        consulta.consulta!!.get(i).ubication
                                )
                        )
                    } else {
                        puntos.add(
                                Ubicacion(
                                        consulta.consulta!!.get(i).__v,
                                        consulta.consulta!!.get(i)._id,
                                        "",
                                        consulta.consulta!!.get(i).currentStage,
                                        consulta.consulta!!.get(i).description,
                                        consulta.consulta!!.get(i).fid,
                                        consulta.consulta!!.get(i).id,
                                        //consulta.consulta!!.get(i).image,
                                        nuevaI,
                                        consulta.consulta!!.get(i).name,
                                        consulta.consulta!!.get(i).previousStage,
                                        consulta.consulta!!.get(i).ubication
                                )
                        )
                    }
                }
                //Log.d("nueva matriz", "${puntos.get(1).image}")
                lista = activity?.findViewById(R.id.lista)
                lista?.setHasFixedSize(true)
                //lista?.layoutManager = LinearLayoutManager(ListaPuntos().context, LinearLayoutManager.HORIZONTAL, false)

                layoutManager = LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL, false)
                lista?.layoutManager = layoutManager

                //adaptador = AdaptadorCustom(contexto, puntos)
                adaptador = AdaptadorCustom(puntos, object : ClickListener {
                    override fun onClick(vista: View, index: Int) {
                        Log.e("EL CLICK", puntos.get(index).ubication)
                        listener?.obtenerPosicion("${puntos.get(index).ubication}")
                    }
                })
                lista?.adapter = adaptador

            } else {
                var nuevaI = consulta.consulta!!.get(0).image.replace("http://0.0.0.0", "http://52.202.214.13")
                if (consulta.consulta!!.get(0).code != null) {
                    puntos.add(
                            Ubicacion(
                                    consulta.consulta!!.get(0).__v,
                                    consulta.consulta!!.get(0)._id,
                                    consulta.consulta!!.get(0).code,
                                    consulta.consulta!!.get(0).currentStage,
                                    consulta.consulta!!.get(0).description,
                                    consulta.consulta!!.get(0).fid,
                                    consulta.consulta!!.get(0).id,
                                    //consulta.consulta!!.get(i).image,
                                    nuevaI,
                                    consulta.consulta!!.get(0).name,
                                    consulta.consulta!!.get(0).previousStage,
                                    consulta.consulta!!.get(0).ubication
                            )
                    )
                } else {
                    puntos.add(
                            Ubicacion(
                                    consulta.consulta!!.get(0).__v,
                                    consulta.consulta!!.get(0)._id,
                                    "",
                                    consulta.consulta!!.get(0).currentStage,
                                    consulta.consulta!!.get(0).description,
                                    consulta.consulta!!.get(0).fid,
                                    consulta.consulta!!.get(0).id,
                                    //consulta.consulta!!.get(i).image,
                                    nuevaI,
                                    consulta.consulta!!.get(0).name,
                                    consulta.consulta!!.get(0).previousStage,
                                    consulta.consulta!!.get(0).ubication
                            )
                    )
                }

                //Log.d("nueva matriz", "${puntos.get(1).image}")
                lista = activity?.findViewById(R.id.lista)
                lista?.setHasFixedSize(true)
                //lista?.layoutManager = LinearLayoutManager(ListaPuntos().context, LinearLayoutManager.HORIZONTAL, false)

                layoutManager = LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL, false)
                lista?.layoutManager = layoutManager

                //adaptador = AdaptadorCustom(contexto, puntos)
                adaptador = AdaptadorCustom(puntos, object : ClickListener {
                    override fun onClick(vista: View, index: Int) {
                        Log.e("EL CLICK", puntos.get(index).ubication)
                        listener?.obtenerPosicion("${puntos.get(index).ubication}")
                    }
                })
                lista?.adapter = adaptador
            }
        }catch (e : java.lang.NullPointerException){
            val builder = AlertDialog.Builder(context)
            builder.setTitle("Mensaje del Servidor").setIcon(R.drawable.logo)
            builder.setMessage("Error en la consulta, registro demasiado antiguo ")
            builder.setPositiveButton("ok"){dialog, id ->}
            builder.show()

        }

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

    override fun anterior(posicion: Int, ubication: String) {
        layoutManager?.scrollToPosition(posicion)
        advertenciaLogin()
        listener?.obtenerPosicion(ubication)
    }

    override fun siguiente(posicion: Int, ubication: String) {
        layoutManager?.scrollToPosition(posicion)
        advertenciaLogin()
        listener?.obtenerPosicion(ubication)
    }

    override fun descripcion(descripcion: String) {
        val builder = AlertDialog.Builder(context)

        builder.setTitle("Descripción").setIcon(R.drawable.logo)
        builder.setMessage("$descripcion")
        builder.setPositiveButton("ok"){ dialog, id ->}
        builder.show()
    }

    override fun mensaje(mensaje: String) {
        val builder = AlertDialog.Builder(context)
        builder.setTitle("Atención").setIcon(R.drawable.logo)
        builder.setMessage("Para conocer todas las ubicaciones del aguacate debes de iniciar sesión")
        builder.setPositiveButton("ok"){ dialog, id ->}
        builder.show()
    }

    fun advertenciaLogin(){
        if (responseUser.message == null){
            val builder = AlertDialog.Builder(context)
            builder.setTitle("Atención").setIcon(R.drawable.logo)
            builder.setMessage("Para conocer todas las ubicaciones del aguacate debes de iniciar sesión")
            builder.setPositiveButton("ok"){ dialog, id ->}
            builder.show()
        }

    }

}
