package com.example.desarrolloresidencia.UI

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.EditText
import android.widget.Toast
import androidx.lifecycle.ViewModelProviders
import com.example.desarrolloresidencia.Network.model.Login.User
import com.example.desarrolloresidencia.R
import com.example.desarrolloresidencia.ViewModel.ActualizarViewModel
import com.example.desarrolloresidencia.ViewModel.LoginViewModel
import com.example.desarrolloresidencia.databinding.ActivityLoginBinding
import com.example.desarrolloresidencia.databinding.ActivityModificacionUsuarioBinding
import com.example.desarrolloresidencia.utils.Auth.AuthListener
import com.example.desarrolloresidencia.utils.Corutinas.Coroutines
import com.example.desarrolloresidencia.utils.ValidarR
import com.example.desarrolloresidencia.utils.responseUser

class ModificacionUsuario : AppCompatActivity(), AuthListener {
    lateinit var modificacionViewModel: ActualizarViewModel
    private lateinit var binding: ActivityModificacionUsuarioBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        //setContentView(R.layout.activity_modificacion_usuario)
        binding = ActivityModificacionUsuarioBinding.inflate(layoutInflater)
        setContentView(binding.root)

        modificacionViewModel= ViewModelProviders.of(this).get(ActualizarViewModel::class.java)
        modificacionViewModel.authListener = this

        Coroutines.authListener= this

        Toast.makeText(this, responseUser.user?.email, Toast.LENGTH_SHORT).show()

        binding.BTRegistrar.setOnClickListener {

            if (ValidarR.hayRed(this)){
                ValidationE()
            } else {
                Toast.makeText(this, "No hay red", Toast.LENGTH_SHORT).show()
            }
        }
    }

    fun ValidationE(){
        var nombre = findViewById<EditText>(R.id.ETNombre)
        var apellidoP = findViewById<EditText>(R.id.ETApellidoP)
        var apellidoM = findViewById<EditText>(R.id.ETApellidoM)
        var username = findViewById<EditText>(R.id.ETEmail)
        var contrasena = findViewById<EditText>(R.id.ETContrasena)

        if (nombre.text.toString() ==""){
            nombre.error = "Ingresa el nombre"
            nombre.requestFocus()
            return
        }

        if (apellidoP.text.toString() ==""){
            apellidoP.error = "Ingresa el apellido paterno"
            apellidoP.requestFocus()
            return
        }

        if (apellidoM.text.toString() ==""){
            apellidoM.error = "Ingresa el apellido materno"
            apellidoM.requestFocus()
            return
        }

        if (username.text.toString() ==""){
            username.error = "Ingresa el Email"
            username.requestFocus()
            return
        }

        if (contrasena.text.toString() ==""){
            contrasena.error = "Ingresa la contraseña"
            contrasena.requestFocus()
            return
        }

        modificacionViewModel.email = username.text.toString()
        modificacionViewModel.password = contrasena.text.toString()
        modificacionViewModel.nombre = nombre.text.toString()
        modificacionViewModel.apellidoP = apellidoP.text.toString()
        modificacionViewModel.apellidoM = apellidoM.text.toString()

        modificacionViewModel.actualizar()
    }

    override fun onStarted() {
        binding.PB.visibility = View.VISIBLE
        binding.ETNombre.isEnabled= false
        binding.ETApellidoP.isEnabled= false
        binding.ETApellidoM.isEnabled= false
        binding.ETContrasena.isEnabled= false
        binding.ETEmail.isEnabled= false
    }

    override fun onSuccess(message: Boolean, token: String, user: User) {
        binding.PB.visibility = View.GONE
        binding.ETNombre.isEnabled= true
        binding.ETApellidoP.isEnabled= true
        binding.ETApellidoM.isEnabled= true
        binding.ETContrasena.isEnabled= true
        binding.ETEmail.isEnabled= true
        validarStatus(user.status)
    }

    override fun onFailure(message: String) {
        binding.PB.visibility = View.GONE
        binding.ETNombre.isEnabled= true
        binding.ETApellidoP.isEnabled= true
        binding.ETApellidoM.isEnabled= true
        binding.ETContrasena.isEnabled= true
        binding.ETEmail.isEnabled= true
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show()
    }

    fun validarStatus (status : String){
        if (status=="false"){
            Toast.makeText(applicationContext, "Verifica tu correo electrónico", Toast.LENGTH_SHORT).show()
        }else{
            Toast.makeText(applicationContext, "Tu correo electrónico está verificado", Toast.LENGTH_SHORT).show()
            var pasar: Intent = Intent(applicationContext, ScannerQR::class.java)
            startActivity(pasar)
        }
    }

}