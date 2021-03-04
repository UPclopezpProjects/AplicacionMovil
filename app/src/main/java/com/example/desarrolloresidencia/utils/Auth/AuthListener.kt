package com.example.desarrolloresidencia.utils.Auth

import android.os.Message
import androidx.lifecycle.LiveData
import com.example.borradoraplicacin.API.data.model.User

interface AuthListener {
    fun onStarted()
    fun onSuccess(message: Boolean, token: String, user: User)
    fun onFailure(message: String)
}