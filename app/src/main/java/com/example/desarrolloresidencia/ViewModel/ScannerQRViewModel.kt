package com.example.desarrolloresidencia.ViewModel

import android.app.Activity
import android.content.Context
import android.util.Log
import androidx.lifecycle.ViewModel
import com.example.desarrolloresidencia.Network.model.Trazabilidad.Message
import com.example.desarrolloresidencia.Repository.TrazabilidadRepository
import com.example.desarrolloresidencia.Repository.UserRepository
import com.example.desarrolloresidencia.utils.Auth.AuthListener
import com.example.desarrolloresidencia.utils.Auth.AuthQr
import com.example.desarrolloresidencia.utils.Coroutines
import com.example.desarrolloresidencia.utils.LEArchivos

class ScannerQRViewModel : ViewModel(){
    var QR:String ?= null
    var authListener: AuthQr ?= null

    fun sobrescribir(texto: String, baseContext: Context){
            LEArchivos.sobrescribir(texto, baseContext)
    }

    fun solicitudP(contexto:Context, actividad:Activity){

    }

    fun consulta(){
        authListener?.onStarted()

        Coroutines.main {
            Log.d("QR", "$QR")
            val response = TrazabilidadRepository().trazabilidadConsulta(QR!!)
            Log.d("consulta", response.body()!!.message.toString())
            com.example.desarrolloresidencia.Network.model.Trazabilidad.consulta.consulta =response.body()!!.message
            Log.d("el objeto", com.example.desarrolloresidencia.Network.model.Trazabilidad.consulta.consulta.toString())
            if (response.isSuccessful){
                val message : Message = response.body()!!.message
                authListener?.onSuccess(message)
                Log.d("success", "si lo mandó")
            } else{
                authListener?.onFailure("Error Code: ${response.code()}")
            }
        }
    }
<<<<<<< Updated upstream
=======

    fun mapeoJS(){
        var testModel = Gson().fromJson(QR, com.example.desarrolloresidencia.Network.model.Trazabilidad.QR::class.java)
        Log.d("EL QR","${testModel.QR}")
        Log.d("EL ID","${testModel.ID}")
        consulta(testModel.QR, testModel.ID)
    }
>>>>>>> Stashed changes
}