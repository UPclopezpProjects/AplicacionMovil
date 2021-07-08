package com.example.desarrolloresidencia.ViewModel

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.desarrolloresidencia.Network.model.Trazabilidad.Message
import com.example.desarrolloresidencia.Repository.AmazonRepository
import com.example.desarrolloresidencia.utils.Auth.AuthQr
import com.google.gson.Gson
import kotlinx.coroutines.launch

class ScannerQRViewModel : ViewModel(){
    var QR:String ?= null
    var authListener: AuthQr ?= null

    fun consulta(qr:String, id:String){
        //CoroutinesTraz.main {
            viewModelScope.launch {
                authListener?.onStarted()
                try {
                    Log.d("QR", "$QR")

                    val response = AmazonRepository().trazabilidadConsulta("$qr", "$id")
                    Log.d("consulta", response.body()!!.message.toString())

                    com.example.desarrolloresidencia.Network.model.Trazabilidad.consulta.consulta = response.body()!!.message

                    if (response.isSuccessful) {
                        Log.d("ScannerQRViewModel", "Está dentro del if")
                        //var message: List<Message> = response.body()!!.message
                        authListener?.onSuccess(/*message*/)
                        Log.d("success", "si lo mandó")
                    } else {
                        Log.d("ScannerQRViewModel", "Está dentro del else")
                        authListener?.onFailure("${response.errorBody()?.string()}")
                    }
                }catch (e : java.net.SocketTimeoutException){
                    authListener?.onFailure("No se pudo conectar con el servidor")
                }
                catch (e: Exception){
                    Log.e("ScannerQRViewModel Exception", "El error es: ${e}")
                }
            }
    }

    fun mapeoJS(){
        var testModel = Gson().fromJson(QR, com.example.desarrolloresidencia.Network.model.Trazabilidad.QR::class.java)
        Log.d("EL QR","${testModel.QR}")
        Log.d("EL ID","${testModel.ID}")
        consulta(testModel.QR, testModel.ID)
    }
}