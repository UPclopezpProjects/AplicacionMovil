package com.example.desarrolloresidencia.UI

import android.os.Bundle
import android.util.Log
import android.util.Patterns
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.desarrolloresidencia.R
import com.example.desarrolloresidencia.databinding.ActivityLoginBinding
import com.example.desarrolloresidencia.databinding.ActivityRecuperacionContrasenaBinding
import com.example.desarrolloresidencia.utils.ValidarR
import java.util.regex.Pattern


class RecuperacionContrasena : AppCompatActivity() {

    private lateinit var binding: ActivityRecuperacionContrasenaBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        //setContentView(R.layout.activity_recuperacion_contrasena)
        binding = ActivityRecuperacionContrasenaBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.BTEnviar.setOnClickListener {
            if (ValidarR.hayRed(this)){
                //Toast.makeText(this, "Hay red", Toast.LENGTH_SHORT).show()
                ValidationE()
            } else {
                Toast.makeText(this, "No hay red", Toast.LENGTH_SHORT).show()
            }
        }

    }

    private fun ValidationE(){
        try {
            var email = findViewById<EditText>(R.id.ETCorreo)

            if (!validarEmail("${email.text.toString()}")){
                email.error ="Email no válido"
                email.requestFocus()
                return
            }
        } catch (e: Exception){
            Log.e("Error UI", "$e")
        }
    }

    private fun validarEmail(email: String): Boolean {
        val pattern: Pattern = Patterns.EMAIL_ADDRESS
        return pattern.matcher(email).matches()
    }
}