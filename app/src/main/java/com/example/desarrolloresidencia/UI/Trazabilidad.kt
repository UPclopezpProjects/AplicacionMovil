package com.example.desarrolloresidencia.UI

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import com.example.desarrolloresidencia.R
import com.example.desarrolloresidencia.UI.DatosTrazabilidad.DatosTrazabilidad

class Trazabilidad : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_trazabilidad)

        var boton = findViewById<Button>(R.id.BTVolver)
        boton.setOnClickListener {
            finish()
        }

        var trazabilidad = findViewById<Button>(R.id.BTTrazabilidad)
        trazabilidad.setOnClickListener {
            val pasar: Intent = Intent(applicationContext, DatosTrazabilidad::class.java)
            startActivity(pasar)
        }
<<<<<<< Updated upstream
    }


=======

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
>>>>>>> Stashed changes
}