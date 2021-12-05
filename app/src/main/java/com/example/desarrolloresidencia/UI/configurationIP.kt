package com.example.desarrolloresidencia.UI

import android.content.Context
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Patterns
import android.widget.EditText
import com.example.desarrolloresidencia.R
import com.example.desarrolloresidencia.databinding.ActivityConfigurationIpBinding
import com.example.desarrolloresidencia.utils.LEArchivos
import java.util.regex.Pattern


class configurationIP : AppCompatActivity() {

    private lateinit var binding:ActivityConfigurationIpBinding
    lateinit var ip:EditText
    var contexto:Context =this

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        //setContentView(R.layout.activity_configuration_ip)
        binding = ActivityConfigurationIpBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)

        ip= findViewById<EditText>(R.id.ETIp)
        binding.TVIp.setText(LEArchivos.CargarIP(contexto))

        binding.BTVolver.setOnClickListener {
            finish()
        }

        binding.BTModificar.setOnClickListener {
            validarE()
        }
    }

    fun validarE(){
        if (!validarIP("${ip.text.toString()}")){
            ip.error ="IP no válido"
            ip.requestFocus()
            return
        }

        LEArchivos.sobrescribirIP(ip.text.toString(), contexto)
        binding.TVIp.setText(LEArchivos.CargarIP(contexto))
    }

    fun validarIP(IP: String): Boolean {
        val pattern: Pattern = Patterns.IP_ADDRESS
        return pattern.matcher(IP).matches()
    }
}