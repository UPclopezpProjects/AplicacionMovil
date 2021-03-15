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
    }


}