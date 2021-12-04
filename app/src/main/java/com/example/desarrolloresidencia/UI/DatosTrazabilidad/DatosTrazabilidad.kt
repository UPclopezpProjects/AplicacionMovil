package com.example.desarrolloresidencia.UI.DatosTrazabilidad

import android.app.AlertDialog
import android.content.Intent
import android.graphics.Color
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Button
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProviders
import com.example.desarrolloresidencia.Network.model.Trazabilidad.consulta
import com.example.desarrolloresidencia.R
import com.example.desarrolloresidencia.UI.AlertDialog.mapaInformacion
import com.example.desarrolloresidencia.ViewModel.ScannerQRViewModel
import com.example.desarrolloresidencia.utils.Auth.AuthQr
import com.example.desarrolloresidencia.utils.FragmentarString
import com.example.desarrolloresidencia.utils.ValidarR
import com.example.desarrolloresidencia.utils.responseUser
import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.OnMapReadyCallback
import com.google.android.gms.maps.SupportMapFragment
import com.google.android.gms.maps.model.*
import com.google.zxing.integration.android.IntentIntegrator


class DatosTrazabilidad : AppCompatActivity(), OnMapReadyCallback, ListaPuntos.MoverCamara, AuthQr {
    lateinit var viewModel: ScannerQRViewModel

    private lateinit var mMap: GoogleMap
    var letra = ArrayList<String>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.datos_trazabilidad)

        viewModel = ViewModelProviders.of(this).get(ScannerQRViewModel::class.java)
        viewModel.authListener = this

        // Obtain the SupportMapFragment and get notified when the map is ready to be used.
        val mapFragment = supportFragmentManager.findFragmentById(R.id.map) as SupportMapFragment
        mapFragment.getMapAsync(this)

        var back = findViewById<Button>(R.id.BTVolver)
        back.setOnClickListener {
            finish()
        }

        var escanear = findViewById<Button>(R.id.BTEscanear)
        escanear.setOnClickListener {
            if (ValidarR.hayRed(this)) {
                //Toast.makeText(this, "Hay red", Toast.LENGTH_SHORT).show()
                IntentIntegrator(this).initiateScan()
            } else {
                //Toast.makeText(this, "No hay red", Toast.LENGTH_SHORT).show()
                val builder = AlertDialog.Builder(this)
                builder.setTitle("Error").setIcon(R.drawable.logo)
                builder.setMessage("No hay red")
                builder.setPositiveButton("ok") { dialog, id -> }
                builder.show()
            }
        }

        var infor = findViewById<Button>(R.id.BTInformacion)
        infor.setOnClickListener {
            mapaInformacion().show(supportFragmentManager, "mapaInformacion")
        }

        var mapa = findViewById<View>(R.id.map)
        mapa.setOnClickListener {
            Log.e("DatosTrazabilidad", "Diste click al mapa")
            advertenciaLogin()
        }
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        val result = IntentIntegrator.parseActivityResult(requestCode, resultCode, data)
        val datos = result.contents
        Log.d("datos", "$datos")
        if (datos != null) {
            viewModel.QR = datos
            //viewModel.sobrescribir(datos, baseContext)
            viewModel.mapeoJS()
        }
    }

    override fun onRestart() {
        super.onRestart()
    }

    override fun onMapReady(googleMap: GoogleMap) {
        abecedario()
        mMap = googleMap

        if (responseUser.message != null) {
            mMap.uiSettings.isZoomControlsEnabled = true
        } else {
            mMap.uiSettings.isZoomGesturesEnabled = false
            mMap.uiSettings.isRotateGesturesEnabled = false
            mMap.uiSettings.isScrollGesturesEnabled = false

            advertenciaLogin()
        }

        var polilinea = PolylineOptions()
        Log.d("tamaño", consulta.consulta!!.size.toString())
        var i = consulta.consulta!!.size - 1
        Log.d("DatosTrazabilidad/onMapReady", "Tamaño: ${consulta.consulta!!.size}")
        while (i >= 0) {
            if (consulta.consulta!!.get(i).currentStage == "Carrier") {
                polilinea.add(
                    puntoIntermedio(
                        consulta.consulta!!.get(i).origin,
                        consulta.consulta!!.get(i).destination
                    )
                )
                mMap.addMarker(
                    MarkerOptions().position(
                        puntoIntermedio(
                            consulta.consulta!!.get(i).origin,
                            consulta.consulta!!.get(i).destination
                        )
                    ).icon(BitmapDescriptorFactory.fromResource(R.drawable.transportista_round))
                        .anchor(0.5f, 0.5f)
                        .title("Fase: " + letra?.get(i) + "," + "Transito del transportista")
                )
            } else {
                polilinea.add(FragmentarString().separaLL(consulta.consulta!!.get(i).ubication))
            }

            if (consulta.consulta!!.size > 1) {
                if (i == 0) {
                    verificarEntrada(i)
                } else {
                    if (i == (consulta.consulta!!.size - 1)) {
                        verificarSalida(i)
                    } else {
                        if (consulta.consulta!!.get(i + 1).currentStage == "Carrier") {
                            verificarEntrada(i)
                        } else {
                            if (consulta.consulta!!.get(i - 1).currentStage == "Carrier") {
                                verificarSalida(i)
                            } else {
                                Log.e(
                                    "DatosTrazabilidad/no es entrada ni salida",
                                    "$i, ${consulta.consulta!!.get(i).currentStage}"
                                )
                                when (consulta.consulta!!.get(i).currentStage) {
                                    "Productor" -> mMap.addMarker(
                                        MarkerOptions().position(
                                            FragmentarString().separaLL(consulta.consulta!!.get(i).ubication)
                                        )
                                            .icon(BitmapDescriptorFactory.fromResource(R.drawable.productor_round))
                                            .anchor(0.5f, 0.5f)
                                            .title("Fase: " + letra?.get(i) + "," + " Productor")
                                    )
                                    //"Carrier" -> mMap.addMarker(MarkerOptions().position(FragmentarString().separaLL(consulta.consulta!!.get(i).ubication)).icon(BitmapDescriptorFactory.fromResource(R.drawable.transportista_round)).anchor(0.5f, 0.5f).title("Fase: " + letra?.get(i) + "," + " Productor"))
                                    "Acopio" -> mMap.addMarker(
                                        MarkerOptions().position(
                                            FragmentarString().separaLL(consulta.consulta!!.get(i).ubication)
                                        )
                                            .icon(BitmapDescriptorFactory.fromResource(R.drawable.acopio_round))
                                            .anchor(0.5f, 0.5f)
                                            .title("Fase: " + letra?.get(i) + "," + " Acopio")
                                    )
                                    "Merchant" -> mMap.addMarker(
                                        MarkerOptions().position(
                                            FragmentarString().separaLL(consulta.consulta!!.get(i).ubication)
                                        )
                                            .icon(BitmapDescriptorFactory.fromResource(R.drawable.comerciante_round))
                                            .anchor(0.5f, 0.5f)
                                            .title("Fase: " + letra?.get(i) + "," + " Merchant")
                                    )
                                    else -> { // Note the block
                                        Log.e("DatosTrazabilidad/no es entrada ni salida2", "Else")
                                    }
                                }
                            }
                        }
                    }

                }
            } else {
                when (consulta.consulta!!.get(i).currentStage) {
                    "Productor" -> mMap.addMarker(
                        MarkerOptions().position(
                            FragmentarString().separaLL(consulta.consulta!!.get(i).ubication)
                        )
                            .icon(BitmapDescriptorFactory.fromResource(R.drawable.productor_round))
                            .anchor(0.5f, 0.5f)
                            .title("Fase: " + letra?.get(i) + "," + " Productor")
                    )
                    //"Carrier" -> mMap.addMarker(MarkerOptions().position(FragmentarString().separaLL(consulta.consulta!!.get(i).ubication)).icon(BitmapDescriptorFactory.fromResource(R.drawable.transportista_round)).anchor(0.5f, 0.5f).title("Fase: " + letra?.get(i) + "," + " Productor"))
                    "Acopio" -> mMap.addMarker(
                        MarkerOptions().position(
                            FragmentarString().separaLL(consulta.consulta!!.get(i).ubication)
                        )
                            .icon(BitmapDescriptorFactory.fromResource(R.drawable.acopio_round))
                            .anchor(0.5f, 0.5f)
                            .title("Fase: " + letra?.get(i) + "," + " Acopio")
                    )
                    "Merchant" -> mMap.addMarker(
                        MarkerOptions().position(
                            FragmentarString().separaLL(consulta.consulta!!.get(i).ubication)
                        )
                            .icon(BitmapDescriptorFactory.fromResource(R.drawable.comerciante_round))
                            .anchor(0.5f, 0.5f)
                            .title("Fase: " + letra?.get(i) + "," + " Merchant")
                    )
                    else -> { // Note the block
                        Log.e("DatosTrazabilidad/no es entrada ni salida2", "Else")
                    }
                }
            }

            i = i - 1
        }

        var polyline = googleMap.addPolyline(
            polilinea
                .clickable(true)
                .color(Color.rgb(0, 143, 103))
                .width(15F)
                .pattern(arrayListOf<PatternItem>(Dash(50F), Gap(25F)))
        )

        if (consulta.consulta!!.get(consulta.consulta!!.size - 1).ubication != null) {
            mMap.moveCamera(
                CameraUpdateFactory.newLatLngZoom(
                    FragmentarString().separaLL(
                        consulta.consulta!!.get(
                            consulta.consulta!!.size - 1
                        ).ubication
                    ), 5.0F
                )
            )
        } else {
            mMap.moveCamera(
                CameraUpdateFactory.newLatLngZoom(
                    puntoIntermedio(
                        consulta.consulta!!.get(consulta.consulta!!.size - 1).origin,
                        consulta.consulta!!.get(consulta.consulta!!.size - 1).destination
                    ), 5.0F
                )
            )
        }

    }

    fun verificarSalida(position: Int) {
        if (consulta.consulta!!.get(position - 1).currentStage == "Carrier") {
            when (consulta.consulta!!.get(position).currentStage) {
                "Productor" -> mMap.addMarker(
                    MarkerOptions().position(
                        FragmentarString().separaLL(
                            consulta.consulta!!.get(position).ubication
                        )
                    ).icon(BitmapDescriptorFactory.fromResource(R.drawable.productor_salida))
                        .anchor(0.5f, 0.5f)
                        .title("Fase: " + letra?.get(position) + "," + " Productor Salida")
                )
                "Acopio" -> mMap.addMarker(
                    MarkerOptions().position(
                        FragmentarString().separaLL(
                            consulta.consulta!!.get(position).ubication
                        )
                    ).icon(BitmapDescriptorFactory.fromResource(R.drawable.productor_salida))
                        .anchor(0.5f, 0.5f)
                        .title("Fase: " + letra?.get(position) + "," + " Acopio Salida")
                )
                "Merchant" -> mMap.addMarker(
                    MarkerOptions().position(
                        FragmentarString().separaLL(
                            consulta.consulta!!.get(position).ubication
                        )
                    ).icon(BitmapDescriptorFactory.fromResource(R.drawable.comerciante_salida))
                        .anchor(0.5f, 0.5f)
                        .title("Fase: " + letra?.get(position) + "," + " Comerciante Salida")
                )
                else -> { // Note the block
                    mMap.addMarker(
                        MarkerOptions().position(
                            FragmentarString().separaLL(
                                consulta.consulta!!.get(
                                    position
                                ).ubication
                            )
                        ).icon(BitmapDescriptorFactory.fromResource(R.drawable.acopio_salida))
                            .anchor(0.5f, 0.5f).title(letra?.get(position))
                    )
                }
            }
        } else {
            when (consulta.consulta!!.get(position).currentStage) {
                "Productor" -> mMap.addMarker(
                    MarkerOptions().position(
                        FragmentarString().separaLL(
                            consulta.consulta!!.get(position).ubication
                        )
                    ).icon(BitmapDescriptorFactory.fromResource(R.drawable.productor_round))
                        .anchor(0.5f, 0.5f)
                        .title("Fase: " + letra?.get(position) + "," + " Productor")
                )
                //"Carrier" -> mMap.addMarker(MarkerOptions().position(FragmentarString().separaLL(consulta.consulta!!.get(i).ubication)).icon(BitmapDescriptorFactory.fromResource(R.drawable.transportista_round)).anchor(0.5f, 0.5f).title("Fase: "+letra?.get(i)+","+" Transportista"))
                "Acopio" -> mMap.addMarker(
                    MarkerOptions().position(
                        FragmentarString().separaLL(
                            consulta.consulta!!.get(position).ubication
                        )
                    ).icon(BitmapDescriptorFactory.fromResource(R.drawable.acopio_round))
                        .anchor(0.5f, 0.5f).title("Fase: " + letra?.get(position) + "," + " Acopio")
                )
                "Merchant" -> mMap.addMarker(
                    MarkerOptions().position(
                        FragmentarString().separaLL(
                            consulta.consulta!!.get(position).ubication
                        )
                    ).icon(BitmapDescriptorFactory.fromResource(R.drawable.comerciante_round))
                        .anchor(0.5f, 0.5f)
                        .title("Fase: " + letra?.get(position) + "," + " Comerciante")
                )
                else -> { // Note the block
                    mMap.addMarker(
                        MarkerOptions().position(
                            FragmentarString().separaLL(
                                consulta.consulta!!.get(
                                    position
                                ).ubication
                            )
                        ).icon(BitmapDescriptorFactory.fromResource(R.drawable.acopio_round))
                            .anchor(0.5f, 0.5f).title(letra?.get(position))
                    )
                }
            }
        }
    }

    fun verificarEntrada(position: Int) {
        if (consulta.consulta!!.get(position + 1).currentStage == "Carrier") {
            Log.e("DatosTrazabilidad/Es salida", "$position")
            when (consulta.consulta!!.get(position).currentStage) {
                "Productor" -> mMap.addMarker(
                    MarkerOptions().position(
                        FragmentarString().separaLL(
                            consulta.consulta!!.get(position).ubication
                        )
                    ).icon(BitmapDescriptorFactory.fromResource(R.drawable.productor_entrada))
                        .anchor(0.5f, 0.5f)
                        .title("Fase: " + letra?.get(position) + "," + " Productor Entrada")
                )
                //"Carrier" -> mMap.addMarker(MarkerOptions().position(FragmentarString().separaLL(consulta.consulta!!.get(i).ubication)).icon(BitmapDescriptorFactory.fromResource(R.drawable.transportista_round)).anchor(0.5f, 0.5f).title("Fase: "+letra?.get(i)+","+" Transportista"))
                "Acopio" -> mMap.addMarker(
                    MarkerOptions().position(
                        FragmentarString().separaLL(
                            consulta.consulta!!.get(position).ubication
                        )
                    ).icon(BitmapDescriptorFactory.fromResource(R.drawable.productor_entrada))
                        .anchor(0.5f, 0.5f)
                        .title("Fase: " + letra?.get(position) + "," + " Acopio Entrada")
                )
                "Merchant" -> mMap.addMarker(
                    MarkerOptions().position(
                        FragmentarString().separaLL(
                            consulta.consulta!!.get(position).ubication
                        )
                    ).icon(BitmapDescriptorFactory.fromResource(R.drawable.comerciante_entrada))
                        .anchor(0.5f, 0.5f)
                        .title("Fase: " + letra?.get(position) + "," + " Comerciante Entrada")
                )
                else -> { // Note the block
                    mMap.addMarker(
                        MarkerOptions().position(
                            FragmentarString().separaLL(
                                consulta.consulta!!.get(
                                    position
                                ).ubication
                            )
                        ).icon(BitmapDescriptorFactory.fromResource(R.drawable.acopio_entrada))
                            .anchor(0.5f, 0.5f).title(letra?.get(position))
                    )
                }
            }
        } else {
            Log.e("DatosTrazabilidad/No es salida", "$position")
            when (consulta.consulta!!.get(position).currentStage) {
                "Productor" -> mMap.addMarker(
                    MarkerOptions().position(
                        FragmentarString().separaLL(
                            consulta.consulta!!.get(position).ubication
                        )
                    ).icon(BitmapDescriptorFactory.fromResource(R.drawable.productor_round))
                        .anchor(0.5f, 0.5f)
                        .title("Fase: " + letra?.get(position) + "," + " Productor")
                )
                //"Carrier" -> mMap.addMarker(MarkerOptions().position(FragmentarString().separaLL(consulta.consulta!!.get(i).ubication)).icon(BitmapDescriptorFactory.fromResource(R.drawable.transportista_round)).anchor(0.5f, 0.5f).title("Fase: "+letra?.get(i)+","+" Transportista"))
                "Acopio" -> mMap.addMarker(
                    MarkerOptions().position(
                        FragmentarString().separaLL(
                            consulta.consulta!!.get(position).ubication
                        )
                    ).icon(BitmapDescriptorFactory.fromResource(R.drawable.acopio_round))
                        .anchor(0.5f, 0.5f).title("Fase: " + letra?.get(position) + "," + " Acopio")
                )
                "Merchant" -> mMap.addMarker(
                    MarkerOptions().position(
                        FragmentarString().separaLL(
                            consulta.consulta!!.get(position).ubication
                        )
                    ).icon(BitmapDescriptorFactory.fromResource(R.drawable.comerciante_round))
                        .anchor(0.5f, 0.5f)
                        .title("Fase: " + letra?.get(position) + "," + " Comerciante")
                )

                "Carrier" -> mMap.addMarker(
                    MarkerOptions().position(
                        puntoIntermedio(
                            consulta.consulta!!.get(position).origin,
                            consulta.consulta!!.get(position).destination
                        )
                    ).icon(BitmapDescriptorFactory.fromResource(R.drawable.transportista_round))
                        .anchor(0.5F, 0.5F)
                        .title("Fase: " + letra?.get(position) + "," + " Comerciante")
                )
                else -> {
                    // Note the block
                    Log.d(
                        "DatosTrazabilidad/verificarEntrada/417",
                        "position: $position, ubication: ${consulta.consulta!!.get(position).ubication}"
                    )
                    mMap.addMarker(
                        MarkerOptions().position(
                            FragmentarString().separaLL(
                                consulta.consulta!!.get(
                                    position
                                ).ubication
                            )
                        ).icon(BitmapDescriptorFactory.fromResource(R.drawable.acopio_round))
                            .anchor(0.5f, 0.5f).title(letra?.get(position))
                    )
                }
            }
        }
    }

    override fun obtenerPosicion(latlong: String) {
        super.obtenerPosicion(latlong)
        mMap.moveCamera(
            CameraUpdateFactory.newLatLngZoom(
                FragmentarString().separaLL(latlong),
                15.0F
            )
        )
    }

    private fun abecedario() {
        letra!!.add("A")
        letra!!.add("B")
        letra!!.add("C")
        letra!!.add("D")
        letra!!.add("E")
        letra!!.add("F")
        letra!!.add("G")
        letra!!.add("H")
        letra!!.add("I")
        letra!!.add("J")
        letra!!.add("K")
        letra!!.add("L")
        letra!!.add("M")
        letra!!.add("N")
        letra!!.add("Ñ")
        letra!!.add("O")
        letra!!.add("P")
        letra!!.add("Q")
        letra!!.add("R")
        letra!!.add("S")
        letra!!.add("T")
        letra!!.add("U")
        letra!!.add("V")
        letra!!.add("W")
        letra!!.add("X")
        letra!!.add("Y")
        letra!!.add("Z")
    }

    fun puntoIntermedio(origin: String, destination: String): LatLng {
        Log.e(
            "DatosTrazabilidad/puntoIntermedio",
            "origin: $origin" + ", destination: $destination"
        )
        var p1 = FragmentarString().separaLL(origin)
        var p2 = FragmentarString().separaLL(destination)
        var latI = (p1.latitude + p2.latitude) / 2
        var lonI = (p1.longitude + p2.longitude) / 2
        return LatLng(latI, lonI)
    }

    override fun onStarted() {
        Toast.makeText(applicationContext, "Cargando...", Toast.LENGTH_LONG).show()
    }

    override fun onSuccess() {
        finish()
        var intent1: Intent = Intent(applicationContext, DatosTrazabilidad::class.java)
        startActivity(intent1)
    }

    override fun onFailure(message: String) {
        mensajeE(message)
    }

    fun mensajeE(mensaje: String) {
        Log.d("ScannerQR", "El mensaje: $mensaje")
        val builder = AlertDialog.Builder(this)
        builder.setTitle("Error").setIcon(R.drawable.logo)
        builder.setMessage("$mensaje")
        builder.setPositiveButton("ok") { dialog, id -> }
        builder.show()
    }

    fun advertenciaLogin() {
        val builder = AlertDialog.Builder(this)
        builder.setTitle("Atención").setIcon(R.drawable.logo)
        builder.setMessage("Para conocer todas las ubicaciones del aguacate debes de iniciar sesión")
        builder.setPositiveButton("ok") { dialog, id -> }
        builder.show()
    }
}