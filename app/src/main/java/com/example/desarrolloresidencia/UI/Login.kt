package com.example.desarrolloresidencia.UI

import android.app.AlertDialog
import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.View.GONE
import android.view.View.VISIBLE
import android.widget.EditText
import android.widget.ImageView
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProviders
import com.example.desarrolloresidencia.Network.model.Login.User
import com.example.desarrolloresidencia.Network.model.MessageError.Error
import com.example.desarrolloresidencia.R
import com.example.desarrolloresidencia.ViewModel.LoginViewModel
import com.example.desarrolloresidencia.databinding.ActivityLoginBinding
import com.example.desarrolloresidencia.utils.Auth.AuthListener
import com.example.desarrolloresidencia.utils.ValidarR
import com.facebook.*
import com.facebook.login.LoginResult
import com.facebook.login.widget.LoginButton
import com.google.gson.Gson
import com.squareup.picasso.Picasso


class Login : AppCompatActivity(), AuthListener {
    lateinit var loginViewModel: LoginViewModel
    private lateinit var binding: ActivityLoginBinding

    //esto es de facebook
    private var callbackManager:CallbackManager ?= null
    private var profileTracker:ProfileTracker ?= null
    private var accessToken:AccessToken ?= null
    //private var foto:ImageView ?= null


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityLoginBinding.inflate(layoutInflater)
        setContentView(binding.root)

        loginViewModel= ViewModelProviders.of(this).get(LoginViewModel::class.java)

        loginViewModel.authListener = this
        //Coroutines.authListener= this

        callbackManager = CallbackManager.Factory.create();

        binding.BTLogin.setOnClickListener {
            if (ValidarR.hayRed(this)){
                ValidationE()
            } else {
                //Toast.makeText(this, "No hay red", Toast.LENGTH_SHORT).show()
                val builder = AlertDialog.Builder(this)
                builder.setTitle("Error Login").setIcon(R.drawable.logo)
                builder.setMessage("No hay red")
                builder.setPositiveButton("ok"){ dialog, id ->}
                builder.show()
            }
        }


        binding.BTRegistro.setOnClickListener {
            val intent: Intent = Intent(applicationContext, RegistroUsuario::class.java)
            startActivity(intent)
        }


        binding.BTSaltar.setOnClickListener {
            val intent: Intent = Intent(applicationContext, Trazabilidad::class.java)
            startActivity(intent)
        }

        binding.BTRecuperar.setOnClickListener {
            val intent: Intent = Intent(applicationContext, RecuperacionContrasena::class.java)
            startActivity(intent)
        }

        //aquí comienza lo de facebook
        val loginButton = findViewById<LoginButton>(R.id.login_button)
        loginButton.setReadPermissions("email")

        //inicializo la imagen de perfil
        //foto = binding.fotoPerfil

        //verifica si ya había iniciado sesión
        if (AccessToken.getCurrentAccessToken() != null && Profile.getCurrentProfile() != null){
            Log.d("LOGIN INICIAL", "${Profile.getCurrentProfile().firstName}")
            accessToken = AccessToken.getCurrentAccessToken()
            cargarData()
        }

        // Callback registration
        loginButton.registerCallback(callbackManager, object : FacebookCallback<LoginResult?> {
            override fun onSuccess(loginResult: LoginResult?) {
                //este es el accessToken
                val accessToken = AccessToken.getCurrentAccessToken()
                //esto me marca si estoy logeado o no
                val isLoggedIn = accessToken != null && !accessToken.isExpired
                Log.d("LOGIN, ACCESS-TOKEN,user", "${accessToken.userId}")
                if (Profile.getCurrentProfile() == null) {
                    profileTracker = object : ProfileTracker() {
                        override fun onCurrentProfileChanged(
                            oldProfile: Profile?,
                            currentProfile: Profile?
                        ) {
                            Log.d(
                                "NOMBRE",
                                currentProfile?.firstName.toString() + currentProfile?.lastName
                            )
                            profileTracker?.stopTracking()
                        }
                    }
                } else {
                    val profile = Profile.getCurrentProfile()
                    Log.d("Login, Nombre", profile.firstName)

                }
            }

            override fun onCancel() {
                Log.e("LOGIN FACEBOOK", "SE CANCELÓ EL LOGIN")
            }

            override fun onError(exception: FacebookException) {

            }
        })
    }

    fun cargarData(){
        val request = GraphRequest.newMeRequest(this.accessToken) {objeto, response ->
            Log.d("GRAPH", response.toString())
            val url = response.jsonObject.getJSONObject("picture").getJSONObject("data").getString("url")
            Log.d("GRAPH", url)
            //Picasso.get().load(url).into(foto)
        }

        val parameters = Bundle()
        parameters.putString("fields", "id,name,link,email,picture.height(500)")
        request.parameters = parameters
        request.executeAsync()
    }

//esto igual es de facebook
    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        callbackManager?.onActivityResult(requestCode, resultCode, data)
        super.onActivityResult(requestCode, resultCode, data)
        Log.d("LOGIN, DATA", "$data")
    }

    private fun ValidationE(){
        try {
            var email = findViewById<EditText>(R.id.ETEmail)
            var contraseña = findViewById<EditText>(R.id.password)

            if (email.text.toString() == "") {
                email.error = "Ingresa el correo"
                email.requestFocus()
                return
            }

            if (contraseña.text.toString() == "") {
                contraseña.error = "Ingresa la contraseña"
                contraseña.requestFocus()
                return
            }
            loginViewModel.email = email.text.toString()
            loginViewModel.password = contraseña.text.toString()

            loginViewModel.onLoginButtonClick()
        } catch (e: Exception){
            Log.e("Error UI", "$e")
        }
    }

    override fun onStarted() {
        binding.PB.visibility = VISIBLE
        binding.BTRegistro.isEnabled= false
        binding.BTSaltar.isEnabled= false
        binding.BTLogin.isEnabled= false
        binding.ETEmail.isEnabled= false
        binding.password.isEnabled= false
        binding.BTRecuperar.isEnabled = false
    }

    override fun onSuccess(message: Boolean, token: String, user: User) {
        //Toast.makeText(this, "$message $token $user", Toast.LENGTH_SHORT).show()
        binding.PB.visibility = GONE
        binding.BTRegistro.isEnabled= true
        binding.BTSaltar.isEnabled= true
        binding.ETEmail.isEnabled= true
        binding.password.isEnabled= true
        binding.BTLogin.isEnabled= true
        binding.BTRecuperar.isEnabled = true
        binding.ETEmail.setText("")
        binding.password.setText("")
        validarStatus(user.status)
    }

    override fun onFailure(message: String) {
        binding.PB.visibility = GONE
        binding.BTRegistro.isEnabled= true
        binding.BTSaltar.isEnabled= true
        binding.ETEmail.isEnabled= true
        binding.password.isEnabled= true
        binding.BTLogin.isEnabled= true
        binding.BTRecuperar.isEnabled = true
        //Toast.makeText(this, message, Toast.LENGTH_SHORT).show()
        mensajeE(message)
    }

    fun validarStatus(status: String){
        
        if (status=="false"){
            //Toast.makeText(applicationContext, "Verifica tu correo electrónico", Toast.LENGTH_SHORT).show()
            val builder = AlertDialog.Builder(this)
            builder.setTitle("Error Login").setIcon(R.drawable.logo)
            builder.setMessage("Verifica tu correo electrónico")
            builder.setPositiveButton("ok"){ dialog, id ->}
            builder.show()
        }else{
            //Toast.makeText(applicationContext, "Tu correo electrónico está verificado", Toast.LENGTH_SHORT).show()
            var pasar:Intent = Intent(applicationContext, Trazabilidad::class.java)
            startActivity(pasar)
        }
    }

    fun mensajeE(mensaje: String){
        var testModel = Gson().fromJson(mensaje, Error::class.java)

        val builder = AlertDialog.Builder(this)
        builder.setTitle("Error Login").setIcon(R.drawable.logo)
        builder.setMessage("${testModel.message}")
        builder.setPositiveButton("ok"){ dialog, id ->}
        builder.show()
    }
}