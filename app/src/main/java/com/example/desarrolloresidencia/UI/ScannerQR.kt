package com.example.desarrolloresidencia.UI

import android.Manifest
import android.content.Intent
import android.content.pm.PackageManager
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import androidx.lifecycle.ViewModelProviders
import com.example.desarrolloresidencia.Network.model.Trazabilidad.Message
import com.example.desarrolloresidencia.R
import com.example.desarrolloresidencia.ViewModel.ScannerQRViewModel
import com.example.desarrolloresidencia.utils.Auth.AuthQr
import com.google.zxing.integration.android.IntentIntegrator
import java.io.BufferedReader
import java.io.File
import java.io.FileInputStream
import java.io.InputStreamReader
import java.lang.Exception


class ScannerQR : AppCompatActivity(), AuthQr {

    var Nota: TextView? = null
    lateinit var viewModel: ScannerQRViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_scanner_q_r)
        viewModel = ViewModelProviders.of(this).get(ScannerQRViewModel::class.java)
        viewModel.authListener = this

        Nota = findViewById<EditText>(R.id.textView5)
        Nota?.setText(viewModel.QR)

        //permisos()
        viewModel.solicitudP(this, this)
        val escanear = findViewById<Button>(R.id.BTEscanear)
        escanear.setOnClickListener {
            IntentIntegrator(this).initiateScan()
        }

        val volver = findViewById<Button>(R.id.BTVolver)
        volver.setOnClickListener {
            val intent: Intent = Intent(applicationContext, Login::class.java)
            startActivity(intent)

        }
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        val result = IntentIntegrator.parseActivityResult(requestCode, resultCode, data)
        val datos = result.contents
        viewModel.QR=datos
        Nota?.setText(viewModel.QR)
        //viewModel.sobrescribir(datos, baseContext)
        viewModel.consulta()
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