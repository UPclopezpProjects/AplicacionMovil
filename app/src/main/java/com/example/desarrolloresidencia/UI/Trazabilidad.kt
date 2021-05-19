package com.example.desarrolloresidencia.UI

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import com.example.desarrolloresidencia.Network.model.Trazabilidad.consulta
import com.example.desarrolloresidencia.R
import com.example.desarrolloresidencia.UI.DatosTrazabilidad.DatosTrazabilidad
import com.example.desarrolloresidencia.databinding.ActivityTrazabilidadBinding
import com.example.desarrolloresidencia.utils.responseUser

class Trazabilidad : AppCompatActivity() {
    private lateinit var binding: ActivityTrazabilidadBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        //setContentView(R.layout.activity_trazabilidad)
        binding = ActivityTrazabilidadBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.BTVolver.setOnClickListener {
            finish()
        }

        binding.BTTrazabilidad.setOnClickListener {
            val pasar: Intent = Intent(applicationContext, DatosTrazabilidad::class.java)
            startActivity(pasar)
        }

        binding.BTAjustes.setOnClickListener {
            if(responseUser.user != null){
                val pasar: Intent = Intent(applicationContext, ModificacionUsuario::class.java)
                startActivity(pasar)
            }else{
                Toast.makeText(this, "Debes iniciar sesión para poder usar esta función", Toast.LENGTH_LONG).show()
            }

        }

        binding.BTTips.setOnClickListener{
            if(responseUser.user != null){
                val pasar: Intent = Intent(applicationContext, Tips::class.java)
                startActivity(pasar)
            }else{
                Toast.makeText(this, "Debes iniciar sesión para poder usar esta función", Toast.LENGTH_LONG).show()
            }

        }

        binding.BTCalculo.setOnClickListener {
            if(responseUser.user != null){
                val pasar: Intent = Intent(applicationContext, CalculoNutricional::class.java)
                startActivity(pasar)
            }else{
                Toast.makeText(this, "Debes iniciar sesión para poder usar esta función", Toast.LENGTH_LONG).show()
            }

        }

        binding.BTSalir.setOnClickListener{
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
        }

        binding.BTInformacion.setOnClickListener{
            if(responseUser.user != null){
                val pasar: Intent = Intent(applicationContext, Informacion::class.java)
                startActivity(pasar)
            }else{
                Toast.makeText(this, "Debes iniciar sesión para poder usar esta función", Toast.LENGTH_LONG).show()
            }
        }
    }
}