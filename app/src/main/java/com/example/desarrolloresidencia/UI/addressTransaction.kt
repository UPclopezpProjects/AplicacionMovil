package com.example.desarrolloresidencia.UI

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.desarrolloresidencia.R
import com.example.desarrolloresidencia.databinding.ActivityAddressTransactionBinding

class addressTransaction : AppCompatActivity() {

    private lateinit var binding:ActivityAddressTransactionBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        //setContentView(R.layout.activity_address_transaction)
        binding= ActivityAddressTransactionBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val transaccion = intent.getStringExtra("transaccion")
        val contrato = intent.getStringExtra("contrato")
        val hash = intent.getStringExtra("hash")

        binding.TVTransaction.setText(transaccion)
        binding.TVContract.setText(contrato)
        binding.TVHash.setText(hash)

        binding.BTVolver.setOnClickListener {
            finish()
        }
    }
}