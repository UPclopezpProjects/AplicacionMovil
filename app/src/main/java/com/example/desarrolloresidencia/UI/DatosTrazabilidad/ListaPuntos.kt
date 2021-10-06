package com.example.desarrolloresidencia.UI.DatosTrazabilidad

import android.app.AlertDialog
import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.os.bundleOf
import androidx.fragment.app.FragmentManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.desarrolloresidencia.Network.model.Trazabilidad.consulta
import com.example.desarrolloresidencia.R
import com.example.desarrolloresidencia.UI.AlertDialog.detallesAddress
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
                    var _v =""
                    var _id =""
                    var code =""
                    var currentStage =""
                    var description =""
                    var fid =""
                    var id =""
                    var image = ""
                    var name =""
                    var previousStage =""
                    var ubication =""
                    var origin =""
                    var destination =""
                    var addressT = ""
                    var addressC = ""
                    var hash = ""

                    if(consulta.consulta!!.get(i).__v != null) _v = consulta.consulta!!.get(i).__v.toString() else _v ="null"
                    if(consulta.consulta!!.get(i)._id != null) _id = consulta.consulta!!.get(i)._id else _id ="null"
                    if(consulta.consulta!!.get(i).code != null) code = consulta.consulta!!.get(i).code else code ="null"
                    if(consulta.consulta!!.get(i).currentStage != null) currentStage = consulta.consulta!!.get(i).currentStage else currentStage ="null"
                    if(consulta.consulta!!.get(i).description != null) description = consulta.consulta!!.get(i).description else description ="null"
                    if(consulta.consulta!!.get(i).fid != null) fid = consulta.consulta!!.get(i).fid else fid ="null"
                    if(consulta.consulta!!.get(i).id != null) id = consulta.consulta!!.get(i).id else id ="null"
                    if(consulta.consulta!!.get(i).image != null) image = consulta.consulta!!.get(i).image else image ="null"
                    if(consulta.consulta!!.get(i).name != null) name = consulta.consulta!!.get(i).name else name ="null"
                    if(consulta.consulta!!.get(i).previousStage != null) previousStage = consulta.consulta!!.get(i).previousStage else previousStage ="null"
                    if(consulta.consulta!!.get(i).ubication != null) ubication = consulta.consulta!!.get(i).ubication else ubication ="null"
                    if(consulta.consulta!!.get(i).origin != null) origin = consulta.consulta!!.get(i).origin else origin ="null"
                    if(consulta.consulta!!.get(i).destination != null) destination = consulta.consulta!!.get(i).destination else destination ="null"
                    if(consulta.consulta!!.get(i).addressTransaction != null) addressT = consulta.consulta!!.get(i).addressTransaction else addressT = "null"
                    if(consulta.consulta!!.get(i).addressContract != null) addressC = consulta.consulta!!.get(i).addressContract else addressC = "null"
                    if(consulta.consulta!!.get(i).hash != null) hash = consulta.consulta!!.get(i).hash else hash = "null"

                    puntos.add(Ubicacion(_v.toInt(), _id,code,currentStage,description,fid,id,image,name,previousStage,ubication,destination, origin, addressT, addressC, hash))
                    Log.e("ListaPuntos/puntos", "${puntos.get(i)}")

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
                        if(puntos.get(index).currentStage != "Carrier"){
                            Log.e("ListaPuntos/position", index.toString())
                            listener?.obtenerPosicion("${puntos.get(index).ubication}")
                        } else {
                            Log.e("ListaPuntos/position", index.toString())
                            listener?.obtenerPosicion("${puntos.get(index).origin}")
                        }

                    }
                })
                lista?.adapter = adaptador

            } else {

                var _v =""
                var _id =""
                var code =""
                var currentStage =""
                var description =""
                var fid =""
                var id =""
                var image = ""
                var name =""
                var previousStage =""
                var ubication =""
                var origin =""
                var destination =""
                var addressT =""
                var addressC = ""
                var hash = ""

                if(consulta.consulta!!.get(0).__v != null) _v = consulta.consulta!!.get(0).__v.toString() else _v ="null"
                if(consulta.consulta!!.get(0)._id != null) _id = consulta.consulta!!.get(0)._id else _id ="null"
                if(consulta.consulta!!.get(0).code != null) code = consulta.consulta!!.get(0).code else code ="null"
                if(consulta.consulta!!.get(0).currentStage != null) currentStage = consulta.consulta!!.get(0).currentStage else currentStage ="null"
                if(consulta.consulta!!.get(0).description != null) description = consulta.consulta!!.get(0).description else description ="null"
                if(consulta.consulta!!.get(0).fid != null) fid = consulta.consulta!!.get(0).fid else fid ="null"
                if(consulta.consulta!!.get(0).id != null) id = consulta.consulta!!.get(0).id else id ="null"
                if(consulta.consulta!!.get(0).image != null) image = consulta.consulta!!.get(0).image else image ="null"
                if(consulta.consulta!!.get(0).name != null) name = consulta.consulta!!.get(0).name else name ="null"
                if(consulta.consulta!!.get(0).previousStage != null) previousStage = consulta.consulta!!.get(0).previousStage else previousStage ="null"
                if(consulta.consulta!!.get(0).ubication != null) ubication = consulta.consulta!!.get(0).ubication else ubication ="null"
                if(consulta.consulta!!.get(0).origin != null) origin = consulta.consulta!!.get(0).origin else origin ="null"
                if(consulta.consulta!!.get(0).destination != null) destination = consulta.consulta!!.get(0).destination else destination ="null"
                if(consulta.consulta!!.get(0).addressTransaction != null) addressT = consulta.consulta!!.get(0).addressTransaction else addressT = "null"
                if(consulta.consulta!!.get(0).addressContract != null) addressC = consulta.consulta!!.get(0).addressContract else addressC = "null"
                if(consulta.consulta!!.get(0).hash != null) hash = consulta.consulta!!.get(0).hash else hash = "null"

                puntos.add(Ubicacion(_v.toInt(), _id,code,currentStage,description,fid,id,image,name,previousStage,ubication,origin, destination, addressT, addressC, hash))
                Log.e("ListaPuntos/puntos2", "${puntos}")

                //Log.d("nueva matriz", "${puntos.get(1).image}")
                lista = activity?.findViewById(R.id.lista)
                lista?.setHasFixedSize(true)
                //lista?.layoutManager = LinearLayoutManager(ListaPuntos().context, LinearLayoutManager.HORIZONTAL, false)

                layoutManager = LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL, false)
                lista?.layoutManager = layoutManager

                //adaptador = AdaptadorCustom(contexto, puntos)
                adaptador = AdaptadorCustom(puntos, object : ClickListener {
                    override fun onClick(vista: View, index: Int) {
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

        builder.setTitle("Detalles").setIcon(R.drawable.logo)
        builder.setMessage("Descripción: $descripcion")
        builder.setPositiveButton("ok"){ dialog, id ->}
        builder.show()
    }

    override fun transaccion(transaccion: String, contract: String, hash: String) {
        /*val builder = AlertDialog.Builder(context, AlertDialog.THEME_DEVICE_DEFAULT_DARK)

        builder.setTitle("Address Transaction").setIcon(R.drawable.logo)
        builder.setMessage("$transaccion")
        builder.setPositiveButton("ok"){ dialog, id ->}
        builder.show()*/

        //detallesAddress().show(childFragmentManager, "detallesAddress")
        val ventanaFlotante = detallesAddress()

        var args =  Bundle()
        args.putString("transaction", transaccion)
        args.putString("contract", contract)
        args.putString("hash", hash)
        ventanaFlotante.setArguments(args)
        ventanaFlotante.show(childFragmentManager, "detallesAddress")

    }

    override fun GetLog(transaccion: String, contract: String) {
        /*val builder = AlertDialog.Builder(context, AlertDialog.THEME_DEVICE_DEFAULT_DARK)

        builder.setTitle("Address Contract").setIcon(R.drawable.logo)
        builder.setMessage("$transaccion, $contract")
        builder.setPositiveButton("ok"){ dialog, id ->}
        builder.show()*/

        val intent = Intent(getActivity(), com.example.desarrolloresidencia.UI.GetLog::class.java)
        intent.putExtra("transaction", "$transaccion")
        intent.putExtra("contract", "$contract")
        intent.putExtra("token", "${responseUser.token}")
        startActivity(intent)

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
