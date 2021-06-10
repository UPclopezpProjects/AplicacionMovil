package com.example.desarrolloresidencia.UI

import android.app.AlertDialog
import android.content.Intent
import android.content.pm.PackageInfo
import android.content.pm.PackageManager
import android.os.Bundle
import android.util.Base64
import android.util.Log
import android.view.View.GONE
import android.view.View.VISIBLE
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProviders
import com.example.desarrolloresidencia.Network.model.Login.User
import com.example.desarrolloresidencia.Network.model.MessageError.Error
import com.example.desarrolloresidencia.Network.model.MessageError.ErrorFacebook
import com.example.desarrolloresidencia.R
import com.example.desarrolloresidencia.ViewModel.LoginViewModel
import com.example.desarrolloresidencia.databinding.ActivityLoginBinding
import com.example.desarrolloresidencia.utils.Auth.AuthListener
import com.example.desarrolloresidencia.utils.JsonFacebook
import com.example.desarrolloresidencia.utils.ValidarR
import com.facebook.*
import com.facebook.login.LoginResult
import com.facebook.login.widget.LoginButton
import com.google.gson.Gson
import java.security.MessageDigest
import java.security.NoSuchAlgorithmException


class Login : AppCompatActivity(), AuthListener {
    lateinit var loginViewModel: LoginViewModel
    private lateinit var binding: ActivityLoginBinding

    //esto es de facebook
    //gestor de callbacks
    private var callbackManager:CallbackManager ?= null
    private var profileTracker:ProfileTracker ?= null
    private var accessToken:AccessToken ?= null
    private var loginF: Boolean ?= null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityLoginBinding.inflate(layoutInflater)
        setContentView(binding.root)

        hash()
        loginViewModel= ViewModelProviders.of(this).get(LoginViewModel::class.java)
        loginViewModel.authListener = this
        loginViewModel.contexto = this


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
            binding.ETEmail.setText("")
            binding.password.setText("")
            val intent: Intent = Intent(applicationContext, Trazabilidad::class.java)
            startActivity(intent)
        }

        binding.BTRecuperar.setOnClickListener {
            val intent: Intent = Intent(applicationContext, RecuperacionContrasena::class.java)
            startActivity(intent)
        }

        callbackManager = CallbackManager.Factory.create()

        val loginButton = findViewById<LoginButton>(R.id.loginbuttonF)
        loginButton.setReadPermissions("email")

        //verifica si ya había iniciado sesión
        if (AccessToken.getCurrentAccessToken() != null && Profile.getCurrentProfile() != null){
            Log.e("Verifica si ya hay una sesión iniciada", "${Profile.getCurrentProfile().firstName}")
            accessToken = AccessToken.getCurrentAccessToken()
            cargarData()
        }

        // Callback registration

        // Callback registration

        loginButton.registerCallback(callbackManager, object : FacebookCallback<LoginResult?> {

            override fun onSuccess(loginResult: LoginResult?) {
                accessToken = AccessToken.getCurrentAccessToken()
                Log.e("Le diste click al login con facebook", "onSuccess")
                loginF= true
                // App code
                //este es el accessToken
                val accessToken = AccessToken.getCurrentAccessToken()
                //esto me marca si estoy logeado o no
                val isLoggedIn = accessToken != null && !accessToken.isExpired
                Log.e("Login AccessToken", "${loginResult?.accessToken}")
                //verifica si ya se logeo previamente
                if (Profile.getCurrentProfile() == null) {

                    Log.e("Le diste click al login con facebook", "verifica el logeo previo")

                    profileTracker = object : ProfileTracker() {
                        //permite rastrear cuando un perfil cambia
                        override fun onCurrentProfileChanged(oldProfile: Profile?, currentProfile: Profile?) {

                            Log.e("Le diste click al login con facebook", "rastrea el cambio de inicio de sesión")

                            Log.e("Le diste click al login con facebook", "onSuccess")
                            Log.d("FIRST NAME", currentProfile?.firstName.toString())
                            Log.d("LAST NAME", currentProfile?.lastName.toString())
                            Log.d("MIDDLE NAME", currentProfile?.middleName.toString())
                            Log.d("NAME", currentProfile?.name.toString())
                            //deja de hacer el rastreo del perfil
                            profileTracker?.stopTracking()
                            loginViewModel.firstname = currentProfile?.firstName.toString()
                            loginViewModel.lastname = currentProfile?.lastName.toString()
                            loginViewModel.firstname = currentProfile?.firstName.toString()
                            loginViewModel.middlename = currentProfile?.middleName.toString()
                            //Toast.makeText(applicationContext, "dentro del if", Toast.LENGTH_LONG).show()
                            cargarData()
                        }
                    }
                } else {
                    val profile = Profile.getCurrentProfile()
                    Log.e("Le diste click al login con facebook", "lo que pasa cuando das login por primera vez")
                    loginViewModel.firstname = profile?.firstName.toString()
                    loginViewModel.lastname = profile?.lastName.toString()
                    loginViewModel.firstname = profile?.firstName.toString()
                    loginViewModel.middlename = profile?.middleName.toString()
                    //Toast.makeText(applicationContext, "lo que pasa cuando das login por primera vez", Toast.LENGTH_LONG).show()
                    cargarData()
                }
            }

            override fun onCancel() {
                Log.e("Login Cancelar", "Canceló")
            }

            override fun onError(exception: FacebookException) {
                // App code
                Log.e("Login Error", exception.toString())
            }
        })

    }

    fun cargarData(){
        Log.e("Login cargarData", "carga los datos ")
        val request = GraphRequest.newMeRequest(this.accessToken) {objeto, response ->
            Log.d("GRAPH1", response.toString())
            Log.d("el json", response.rawResponse.toString())
            //val url = response.jsonObject.getJSONObject("picture").getJSONObject("data").getString("url")
            val url = response.jsonObject.getJSONObject("picture").getJSONObject("data").getString("url")
            Log.d("GRAPH", url)
            var formato = Gson().fromJson(response.rawResponse, JsonFacebook::class.java)
            Log.e("EMAIL", formato.email)

            if (loginF ==true){
                Log.e("login if cargardata", "si se va entra al if")
                loginViewModel.email = formato.email
                loginViewModel.password = formato.id
                loginViewModel.LoginFacebook()

            }

            //Picasso.get().load(url).into(foto)
        }

        val parameters = Bundle()
        parameters.putString("fields", "id,name,link,email,picture.height(500)")
        request.parameters = parameters
        request.executeAsync()
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        callbackManager?.onActivityResult(requestCode, resultCode, data)
        super.onActivityResult(requestCode, resultCode, data)

        Log.e("requestCode", requestCode.toString())
        Log.e("resultCode", resultCode.toString())
        Log.e("data", data.toString())
    }

    fun hash(){
        try {
            val info: PackageInfo? = packageManager.getPackageInfo(
                    "com.example.desarrolloresidencia",  //Insert your own package name.
                    PackageManager.GET_SIGNATURES
            )
            if (info != null) {
                for (signature in info.signatures) {
                    val md = MessageDigest.getInstance("SHA")
                    md.update(signature.toByteArray())
                    Log.d("KeyHash:", Base64.encodeToString(md.digest(), Base64.DEFAULT))
                }
            }
        } catch (e: PackageManager.NameNotFoundException) {
        } catch (e: NoSuchAlgorithmException) {
        }
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
        Log.e("login onFailure", "$message")
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
            builder.setTitle("Mensaje del servidor").setIcon(R.drawable.logo)
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
        try {
            var testModel = Gson().fromJson(mensaje, ErrorFacebook::class.java)

            if (loginF==true){
                Log.d("prueba del if", "Ya existe un usuario con el email: ${loginViewModel.email}")
                Log.d("el mensaje", mensaje)
                var posibleerror = "Ya existe un usuario con el email: ${loginViewModel.email}"
                Log.e("posibleerror", posibleerror)
                when (testModel.message) {
                        posibleerror -> {
                            loginViewModel.onLoginButtonClick()
                            /*val builder = AlertDialog.Builder(this)
                            builder.setTitle("Registro Exitoso").setIcon(R.drawable.logo)
                            builder.setMessage("Verifica tu correo electrónico")
                            builder.setPositiveButton("ok"){ dialog, id ->}
                            builder.show()*/
                     }
                        else -> {
                            Log.d("login mensajeE", "$mensaje")
                            var testModel = Gson().fromJson(mensaje, ErrorFacebook::class.java)
                            Log.d("login testModel", "${testModel.message}")

                            val builder = AlertDialog.Builder(this)
                            builder.setTitle("Mensaje del servidor").setIcon(R.drawable.logo)
                            builder.setMessage("${testModel.message}")
                            builder.setPositiveButton("ok"){ dialog, id ->}
                            builder.show()
                        }
                }

            }else{
                Log.d("else", "que no le diste click el botón de facebook")
                Log.d("login mensajeE", "$mensaje")

                Log.d("login testModel", "${testModel.message}")

                val builder = AlertDialog.Builder(this)
                builder.setTitle("Mensaje del servidor").setIcon(R.drawable.logo)
                builder.setMessage("${testModel.message}")
                builder.setPositiveButton("ok"){ dialog, id ->}
                builder.show()
            }

        }catch (e: java.lang.Exception){
            Log.e("ERROR MENSAJEE", e.toString())
        }

    }
}