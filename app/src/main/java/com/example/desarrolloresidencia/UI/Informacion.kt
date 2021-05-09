package com.example.desarrolloresidencia.UI

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.desarrolloresidencia.R
import com.example.desarrolloresidencia.databinding.ActivityInformacionBinding
import com.example.desarrolloresidencia.databinding.ActivityTrazabilidadBinding

class Informacion : AppCompatActivity() {

    private lateinit var binding: ActivityInformacionBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        //setContentView(R.layout.activity_informacion)
        binding = ActivityInformacionBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.BTVolver.setOnClickListener{
            finish()
        }
    }
}