package com.example.desarrolloresidencia.ViewModel

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.desarrolloresidencia.Repository.AmazonRepository
import com.example.desarrolloresidencia.utils.Auth.AuthGetLog
import kotlinx.coroutines.launch

class getLogViewModel(): ViewModel() {
    var addressTransaction: String ?= null
    var addressContract: String ?= null
    var token:String ?= null

    var authListener:AuthGetLog ?= null

    fun getLog(){
        viewModelScope.launch {
            authListener?.onStarted()
            try{
                val response = AmazonRepository().getLog("$addressTransaction", "$addressContract", "$token")
                Log.e("getLogViewModel/getLog",response.body().toString())

                if(response.isSuccessful){
                    authListener!!.onSuccess(response.body()!!.message)
                } else {
                    authListener?.onFailure("${response.errorBody()?.string()}")
                }
            } catch (e: java.net.SocketTimeoutException){
                authListener?.onFailure("No se pudo conectar con el servidor")
            }
        }
    }
}