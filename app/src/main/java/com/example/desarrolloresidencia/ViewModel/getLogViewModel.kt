package com.example.desarrolloresidencia.ViewModel

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.desarrolloresidencia.Repository.AmazonRepository
import com.example.desarrolloresidencia.utils.Auth.AuthGetLog
import kotlinx.coroutines.launch

class getLogViewModel() : ViewModel() {
    var addressTransaction: String? = null
    var addressContract: String? = null
    var token: String? = null

    var authListener: AuthGetLog? = null

    fun getLog() {
        viewModelScope.launch {
            authListener?.onStarted()
            try {
                val response =
                    AmazonRepository().getLog("$addressTransaction", "$addressContract", "$token")
                Log.e("getLogViewModel/getLog", response?.body().toString())

                if (response!!.isSuccessful) {
                    if (response != null) {
                        Log.e(
                            "getLogViewModel/message.log",
                            response.body()!!.message.log.toString()
                        )
                        authListener!!.onSuccess(response?.body()!!.message)
                    } else {
                        Log.e(
                            "getLogViewModel/message.log",
                            response?.body()!!.message.log.toString()
                        )
                        authListener?.onFailure("No se encontró información")
                    }
                } else {
                    authListener?.onFailure("${response.errorBody()?.string()}")
                }
            } catch (e: java.net.SocketTimeoutException) {
                authListener?.onFailure("No se pudo conectar con el servidor")
            } catch (e: java.lang.NullPointerException) {
                authListener?.onFailure("No se encontró información")
            }
        }
    }
}