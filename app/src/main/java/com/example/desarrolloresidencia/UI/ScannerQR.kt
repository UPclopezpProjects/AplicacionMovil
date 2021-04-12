package com.example.desarrolloresidencia.UI

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProviders
import com.example.desarrolloresidencia.Network.model.Trazabilidad.Message
import com.example.desarrolloresidencia.R
import com.example.desarrolloresidencia.ViewModel.ScannerQRViewModel
import com.example.desarrolloresidencia.databinding.ActivityRegistroUsuarioBinding
import com.example.desarrolloresidencia.databinding.ActivityScannerQRBinding
import com.example.desarrolloresidencia.utils.Auth.AuthQr
import com.example.desarrolloresidencia.utils.ValidarR
import com.google.zxing.integration.android.IntentIntegrator


class ScannerQR : AppCompatActivity(), AuthQr {

    var Nota: TextView? = null
    lateinit var viewModel: ScannerQRViewModel
    private lateinit var binding:ActivityScannerQRBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        //setContentView(R.layout.activity_scanner_q_r)
        binding = ActivityScannerQRBinding.inflate(layoutInflater)
        setContentView(binding.root)

        viewModel = ViewModelProviders.of(this).get(ScannerQRViewModel::class.java)
        viewModel.authListener = this
        //Coroutines.contexto = this



        //val escanear = findViewById<Button>(R.id.BTEscanear)
        //escanear.setOnClickListener {
        binding.BTEscanear.setOnClickListener {
            if (ValidarR.hayRed(this)){
                //Toast.makeText(this, "Hay red", Toast.LENGTH_SHORT).show()
                IntentIntegrator(this).initiateScan()
            } else {
                Toast.makeText(this, "No hay red", Toast.LENGTH_SHORT).show()
            }
        }

        //}

        val volver = findViewById<Button>(R.id.BTVolver)
        volver.setOnClickListener {
            finish()
        }
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        val result = IntentIntegrator.parseActivityResult(requestCode, resultCode, data)
        val datos = result.contents
        Log.d("datos", "$datos")
        if (datos != null){
            viewModel.QR=datos
            //viewModel.sobrescribir(datos, baseContext)
            viewModel.consulta()
            TrazabilidadScreen()
        }
    }

    fun TrazabilidadScreen(){
        var intent : Intent = Intent(applicationContext, Trazabilidad::class.java)
        startActivity(intent)
    }

    override fun onStarted() {

    }

    override fun onSuccess(message: Message) {
        //Log.d("success", "terminé")
        Toast.makeText(this, "Se hizo la consulta", Toast.LENGTH_SHORT).show()
    }

    override fun onFailure(message: String) {
        Toast.makeText(this, "$message", Toast.LENGTH_SHORT).show()
    }
}