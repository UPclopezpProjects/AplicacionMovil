package com.example.desarrolloresidencia.UI

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.lifecycle.ViewModelProviders
import com.example.desarrolloresidencia.Network.model.GetLog.Message
import com.example.desarrolloresidencia.R
import com.example.desarrolloresidencia.ViewModel.ActualizarViewModel
import com.example.desarrolloresidencia.ViewModel.getLogViewModel
import com.example.desarrolloresidencia.databinding.ActivityGetLogBinding
import com.example.desarrolloresidencia.databinding.ActivityModificacionUsuarioBinding
import com.example.desarrolloresidencia.utils.Auth.AuthGetLog

class GetLog : AppCompatActivity(), AuthGetLog {

    lateinit var getLogViewModel: getLogViewModel
    private lateinit var binding: ActivityGetLogBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityGetLogBinding.inflate(layoutInflater)
        setContentView(binding.root)

        getLogViewModel = ViewModelProviders.of(this).get(getLogViewModel::class.java)
        getLogViewModel.authListener = this


    }

    override fun onStarted() {
        TODO("Not yet implemented")
    }

    override fun onSuccess(message: Message) {
        TODO("Not yet implemented")
    }

    override fun onFailure(message: String) {
        TODO("Not yet implemented")
    }
}