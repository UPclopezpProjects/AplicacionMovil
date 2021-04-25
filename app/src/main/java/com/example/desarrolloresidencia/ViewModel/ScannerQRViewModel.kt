package com.example.desarrolloresidencia.ViewModel

import android.util.Log
import androidx.lifecycle.ViewModel
import com.example.desarrolloresidencia.Network.model.Trazabilidad.Message
import com.example.desarrolloresidencia.Network.model.Trazabilidad.Trazabilidad
import com.example.desarrolloresidencia.Repository.TrazabilidadRepository
import com.example.desarrolloresidencia.utils.Auth.AuthQr
import com.example.desarrolloresidencia.utils.Corutinas.Coroutines
import com.google.gson.Gson

class ScannerQRViewModel : ViewModel(){
    var QR:String ?= null
    var authListener: AuthQr ?= null

    fun consulta(qr:String, id:String){
        authListener?.onStarted()

        Coroutines.main {
            Log.d("QR", "$QR")
            val response = TrazabilidadRepository().trazabilidadConsulta("$qr", "$id")
            Log.d("consulta", response.body()!!.message.toString())
            com.example.desarrolloresidencia.Network.model.Trazabilidad.consulta.consulta =response.body()!!.message
            Log.d("el id", com.example.desarrolloresidencia.Network.model.Trazabilidad.consulta.consulta!!.get(0).id)
            if (response.isSuccessful){
                val message : List<Message> = response.body()!!.message
                authListener?.onSuccess(message)
                Log.d("success", "si lo mandó")
            } else{
                authListener?.onFailure("${response.errorBody()?.string()}")
            }
        }
    }

    fun mapeoJS(){
        var testModel = Gson().fromJson(QR, com.example.desarrolloresidencia.Network.model.Trazabilidad.QR::class.java)
        consulta(testModel.QR, testModel.ID)
    }
}