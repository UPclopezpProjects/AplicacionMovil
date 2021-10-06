package com.example.desarrolloresidencia.UI

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.lifecycle.ViewModelProviders
import com.example.desarrolloresidencia.Network.model.GetLog.Message
import com.example.desarrolloresidencia.R
import com.example.desarrolloresidencia.ViewModel.ActualizarViewModel
import com.example.desarrolloresidencia.ViewModel.getLogViewModel
import com.example.desarrolloresidencia.databinding.ActivityGetLogBinding
import com.example.desarrolloresidencia.databinding.ActivityModificacionUsuarioBinding
import com.example.desarrolloresidencia.utils.Auth.AuthGetLog

class GetLog : AppCompatActivity(), AuthGetLog {

    lateinit var getLogVM: getLogViewModel
    private lateinit var binding: ActivityGetLogBinding


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityGetLogBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val extras = intent.extras
        val transaction = extras?.getString("transaction")?:""
        val contract = extras?.getString("contract")?:""
        val token = extras?.getString("token")?:""

        getLogVM = ViewModelProviders.of(this).get(getLogViewModel::class.java)
        getLogVM.authListener = this

        getLogVM.addressTransaction=transaction
        getLogVM.addressContract=contract
        getLogVM.token=token

        getLogVM.getLog()

        binding.BTVolver.setOnClickListener {
            finish()
        }

    }

    override fun onStarted() {
        Log.d("GetLog activity/onStarted", "inició")
        binding.PBCargando.visibility = View.VISIBLE
    }

    override fun onSuccess(message: Message) {
        binding.PBCargando.visibility = View.INVISIBLE
        Log.d("GetLog activity/onSuccess", "$message")
        binding.TVToken.setText(message.token)
        binding.TVLogIndex.setText(message.log.logIndex.toString())
        binding.TVTransactionIndex.setText(message.log.transactionIndex.toString())
        binding.TVTransactionHash.setText(message.log.transactionHash)
        binding.TVBlockHash.setText(message.log.blockHash)
        binding.TVBlockNumber.setText(message.log.blockNumber.toString())
        binding.TVAddress.setText(message.log.address)
        binding.TVType.setText(message.log.type)
        binding.TVRemoved.setText(message.log.removed.toString())
        binding.TVId.setText(message.log.id)
        binding.TV0.setText(message.log.returnValues.`0`)
        binding.TV1.setText(message.log.returnValues.`1`)
        binding.TV2.setText(message.log.returnValues.`2`)
        binding.TV3.setText(message.log.returnValues.`3`)
        binding.TV4.setText(message.log.returnValues.`4`)
        binding.TV5.setText(message.log.returnValues.`5`)
        binding.TV6.setText(message.log.returnValues.`6`)
        binding.TVIdEvents.setText(message.log.returnValues.idEvents)
        binding.TVWhen.setText(message.log.returnValues.`when`)
        binding.TVTypeEvent.setText(message.log.returnValues.typeEvent)
        binding.TVWhere.setText(message.log.returnValues.where)
        binding.TVToken2.setText(message.log.returnValues.token)
        binding.TVDescription.setText(message.log.returnValues.description)
        binding.TVEvent.setText(message.log.event)
        binding.TVSignature.setText(message.log.signature)
    }

    override fun onFailure(message: String) {
        Log.d("GetLog activity/onFailure", "$message")
    }
}