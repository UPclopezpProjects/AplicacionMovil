package com.example.desarrolloresidencia.UI

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.desarrolloresidencia.R
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
    }
}