package com.example.desarrolloresidencia.UI

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import com.example.desarrolloresidencia.R
import com.example.desarrolloresidencia.UI.AlertDialog.Aceite
import com.example.desarrolloresidencia.UI.AlertDialog.Belleza
import com.example.desarrolloresidencia.UI.AlertDialog.Guacamole
import com.example.desarrolloresidencia.UI.AlertDialog.Recetas
import com.example.desarrolloresidencia.databinding.ActivityTipsBinding
import com.example.desarrolloresidencia.databinding.ActivityTrazabilidadBinding

class Tips : AppCompatActivity() {

    private lateinit var binding: ActivityTipsBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        //setContentView(R.layout.activity_tips)
        binding = ActivityTipsBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.BTVolver.setOnClickListener{
            finish()
        }

        binding.BTGuacamole.setOnClickListener {
            Guacamole().show(supportFragmentManager, "Guacamole")
        }

        binding.BTRecetas.setOnClickListener {
            Recetas().show(supportFragmentManager, "Recetas")
        }

        binding.BTBelleza.setOnClickListener {
            Belleza().show(supportFragmentManager, "Belleza")
        }

        binding.BTAceite.setOnClickListener {
            Aceite().show(supportFragmentManager, "Belleza")
        }
    }



}