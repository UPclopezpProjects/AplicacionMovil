package com.example.desarrolloresidencia.UI

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProviders
import com.example.desarrolloresidencia.Network.model.Trazabilidad.consulta
import com.example.desarrolloresidencia.UI.AlertDialog.BlankFragment
import com.example.desarrolloresidencia.R
import com.example.desarrolloresidencia.UI.DatosTrazabilidad.DatosTrazabilidad
import com.example.desarrolloresidencia.ViewModel.ScannerQRViewModel
import com.example.desarrolloresidencia.databinding.ActivityTrazabilidadBinding
import com.example.desarrolloresidencia.utils.Auth.AuthQr
import com.example.desarrolloresidencia.utils.responseUser
import com.google.zxing.integration.android.IntentIntegrator

class Trazabilidad : AppCompatActivity(), AuthQr {

    lateinit var viewModel: ScannerQRViewModel
    private lateinit var binding: ActivityTrazabilidadBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        //setContentView(R.layout.activity_trazabilidad)
        binding = ActivityTrazabilidadBinding.inflate(layoutInflater)
        setContentView(binding.root)

        viewModel = ViewModelProviders.of(this).get(ScannerQRViewModel::class.java)
        viewModel.authListener = this

        ocultarajustes()

        binding.BTVolver.setOnClickListener {
            if(responseUser.user != null){

                val builder = AlertDialog.Builder(this)

                builder.setView(R.layout.fragment_logout)

                //set positive button
                builder.setNegativeButton(
                    "No"
                ) { dialog, id ->
                    // User cancelled the dialog
                }

                builder.setPositiveButton("Si") { dialog, id ->
                    // User clicked Update Now button
                    consulta.consulta = null
                    responseUser.message  = null
                    responseUser.user = null
                    responseUser.token = null
                    finish()
                    startActivity(
                        Intent(baseContext, SplashScreen::class.java)
                            .addFlags(Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK)
                    )
                }

                builder.show()
            }else{
                consulta.consulta = null
                finish()
            }
        }

        binding.BTTrazabilidad.setOnClickListener {
            if (consulta.consulta != null){
                val pasar: Intent = Intent(applicationContext, DatosTrazabilidad::class.java)
                startActivity(pasar)
            }else{
                val pasar: Intent = Intent(applicationContext, ScannerQR::class.java)
                startActivity(pasar)
            }

        }

        binding.BTAjustes.setOnClickListener {
            if(responseUser.user != null){
                val pasar: Intent = Intent(applicationContext, ModificacionUsuario::class.java)
                startActivity(pasar)
            }else{
                //Toast.makeText(this, "Debes iniciar sesión para poder usar esta función", Toast.LENGTH_LONG).show()
                val builder = android.app.AlertDialog.Builder(this)
                builder.setTitle("Acceso denegado").setIcon(R.drawable.logo)
                builder.setMessage("Debes iniciar sesión para poder usar esta función")
                builder.setPositiveButton("ok"){dialog, id ->}
                builder.show()
            }

        }

        binding.BTTips.setOnClickListener{
            if(responseUser.user != null){
                val pasar: Intent = Intent(applicationContext, Tips::class.java)
                startActivity(pasar)
            }else{
                //Toast.makeText(this, "Debes iniciar sesión para poder usar esta función", Toast.LENGTH_LONG).show()
                val builder = android.app.AlertDialog.Builder(this)
                builder.setTitle("Acceso denegado").setIcon(R.drawable.logo)
                builder.setMessage("Debes iniciar sesión para poder usar esta función")
                builder.setPositiveButton("ok"){dialog, id ->}
                builder.show()
            }

        }

        binding.BTCalculo.setOnClickListener {
            if(responseUser.user != null){
                val pasar: Intent = Intent(applicationContext, CalculoNutricional::class.java)
                startActivity(pasar)
            }else{
                //Toast.makeText(this, "Debes iniciar sesión para poder usar esta función", Toast.LENGTH_LONG).show()
                val builder = android.app.AlertDialog.Builder(this)
                builder.setTitle("Acceso denegado").setIcon(R.drawable.logo)
                builder.setMessage("Debes iniciar sesión para poder usar esta función")
                builder.setPositiveButton("ok"){dialog, id ->}
                builder.show()
            }

        }

        binding.BTEscanear.setOnClickListener{
            IntentIntegrator(this).initiateScan()
        }

        binding.BTInformacion.setOnClickListener{
            if(responseUser.user != null){
                val pasar: Intent = Intent(applicationContext, Informacion::class.java)
                startActivity(pasar)
            }else{
                //Toast.makeText(this, "Debes iniciar sesión para poder usar esta función", Toast.LENGTH_LONG).show()
                val builder = android.app.AlertDialog.Builder(this)
                builder.setTitle("Acceso denegado").setIcon(R.drawable.logo)
                builder.setMessage("Debes iniciar sesión para poder usar esta función")
                builder.setPositiveButton("ok"){dialog, id ->}
                builder.show()
            }
        }

        binding.BTNInformacion.setOnClickListener {
            BlankFragment().show(supportFragmentManager, "BlankFragment")
        }
    }

    fun ocultarajustes(){
        if(responseUser.message == null){
            binding.BTAjustes.visibility = View.INVISIBLE
            binding.textView14.visibility = View.INVISIBLE
        }else{
            binding.BTAjustes.visibility = View.VISIBLE
            binding.textView14.visibility = View.VISIBLE
        }
    }

    override fun onBackPressed() {

        if(responseUser.user != null){

            val builder = AlertDialog.Builder(this)

            builder.setView(R.layout.fragment_logout)

            //set positive button
            builder.setNegativeButton(
                    "No"
            ) { dialog, id ->
                // User cancelled the dialog
            }

            builder.setPositiveButton("Si") { dialog, id ->
                // User clicked Update Now button
                consulta.consulta = null
                responseUser.message  = null
                responseUser.user = null
                responseUser.token = null
                super.onBackPressed()
                finish()
                startActivity(
                        Intent(baseContext, SplashScreen::class.java)
                                .addFlags(Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK)
                )
            }

            builder.show()
        }else{
            consulta.consulta = null
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
            viewModel.mapeoJS()
        }
    }

    fun TrazabilidadScreen(){
        var intent2 : Intent = Intent(applicationContext, DatosTrazabilidad::class.java)
        startActivity(intent2)
    }

    override fun onStarted() {
        Log.d("ScannerQR", "Comenzó la consulta")

    }

    override fun onSuccess() {
        //Log.d("success", "terminé")
        //Toast.makeText(this, "Se hizo la consulta", Toast.LENGTH_SHORT).show()
        //Log.d("la matriz", "${message.get(1)}")
        TrazabilidadScreen()
    }

    override fun onFailure(message: String) {

        //Toast.makeText(this, "$message", Toast.LENGTH_SHORT).show()
        mensajeE(message)
    }

    fun mensajeE(mensaje : String){
        Log.d("ScannerQR", "El mensaje: $mensaje")
        val builder = android.app.AlertDialog.Builder(this)
        builder.setTitle("Mensaje del servidor").setIcon(R.drawable.ic_twotone_error_24)
        builder.setMessage("$mensaje")
        builder.setPositiveButton("ok"){dialog, id ->}
        builder.show()
    }

}