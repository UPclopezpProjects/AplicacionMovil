package com.example.desarrolloresidencia.UI

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import com.example.desarrolloresidencia.R
import com.example.desarrolloresidencia.UI.DatosTrazabilidad.DatosTrazabilidad
import com.example.desarrolloresidencia.databinding.ActivityLoginBinding
import com.example.desarrolloresidencia.databinding.ActivityTrazabilidadBinding

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
            val pasar: Intent = Intent(applicationContext, ModificacionUsuario::class.java)
            startActivity(pasar)
        }

        binding.BTTips.setOnClickListener{
            val pasar: Intent = Intent(applicationContext, Tips::class.java)
            startActivity(pasar)
        }

        binding.BTCalculo.setOnClickListener {
            val pasar: Intent = Intent(applicationContext, CalculoNutricional::class.java)
            startActivity(pasar)
        }
    }


}