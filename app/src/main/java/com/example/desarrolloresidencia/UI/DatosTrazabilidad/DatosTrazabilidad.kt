package com.example.desarrolloresidencia.UI.DatosTrazabilidad

import android.graphics.Color
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.desarrolloresidencia.Network.model.Trazabilidad.consulta
import com.example.desarrolloresidencia.R
import com.example.desarrolloresidencia.UI.AlertDialog.Guacamole
import com.example.desarrolloresidencia.UI.AlertDialog.mapaInformacion
import com.example.desarrolloresidencia.utils.FragmentarString
import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.OnMapReadyCallback
import com.google.android.gms.maps.SupportMapFragment
import com.google.android.gms.maps.model.*


class DatosTrazabilidad : AppCompatActivity(), OnMapReadyCallback, ListaPuntos.MoverCamara {

    private lateinit var mMap: GoogleMap
    var letra= ArrayList<String>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.datos_trazabilidad)

        // Obtain the SupportMapFragment and get notified when the map is ready to be used.
        val mapFragment = supportFragmentManager.findFragmentById(R.id.map) as SupportMapFragment
        mapFragment.getMapAsync(this)

        var back = findViewById<Button>(R.id.BTVolver)
        back.setOnClickListener {
            finish()
        }
        //Toast.makeText(this, "Scrollea la lista de ubicaciones y da click sobre el elemento que quieras ver en el mapa", Toast.LENGTH_LONG).show()

        var infor = findViewById<Button>(R.id.BTInformacion)
        infor.setOnClickListener {
            mapaInformacion().show(supportFragmentManager, "mapaInformacion")
        }
    }

    override fun onMapReady(googleMap: GoogleMap) {
        abecedario()
        mMap = googleMap
        mMap.uiSettings.isZoomControlsEnabled=true

        var polilinea = PolylineOptions()
        Log.d("tamaño", consulta.consulta!!.size.toString())
        var i = consulta.consulta!!.size-1
        while (i >=0){
        polilinea.add(FragmentarString().separaLL(consulta.consulta!!.get(i).ubication))

            when (consulta.consulta!!.get(i).currentStage) {
                "Productor" -> mMap.addMarker(MarkerOptions().position(FragmentarString().separaLL(consulta.consulta!!.get(i).ubication)).icon(BitmapDescriptorFactory.fromResource(R.drawable.productor_round)).anchor(0.5f, 0.5f).title("Fase: "+letra?.get(i)+","+" Productor"))
                "Carrier" -> mMap.addMarker(MarkerOptions().position(FragmentarString().separaLL(consulta.consulta!!.get(i).ubication)).icon(BitmapDescriptorFactory.fromResource(R.drawable.transportista_round)).anchor(0.5f, 0.5f).title("Fase: "+letra?.get(i)+","+" Transportista"))
                "Acopio" -> mMap.addMarker(MarkerOptions().position(FragmentarString().separaLL(consulta.consulta!!.get(i).ubication)).icon(BitmapDescriptorFactory.fromResource(R.drawable.acopio_round)).anchor(0.5f, 0.5f).title("Fase: "+letra?.get(i)+","+" Acopio"))
                "Merchant" -> mMap.addMarker(MarkerOptions().position(FragmentarString().separaLL(consulta.consulta!!.get(i).ubication)).icon(BitmapDescriptorFactory.fromResource(R.drawable.comerciante_round)).anchor(0.5f, 0.5f).title("Fase: "+letra?.get(i)+","+" Comerciante"))
                else -> { // Note the block
                    mMap.addMarker(MarkerOptions().position(FragmentarString().separaLL(consulta.consulta!!.get(i).ubication)).icon(BitmapDescriptorFactory.fromResource(R.drawable.acopio_round)).anchor(0.5f, 0.5f).title(letra?.get(i)))
                }
            }

            i=i-1
        }
        var polyline = googleMap.addPolyline(polilinea
                .clickable(true)
                .color(Color.rgb(0, 143, 103))
                .width(15F)
                .pattern(arrayListOf<PatternItem>(Dash(50F), Gap(25F)))
        )
        //mMap.moveCamera(CameraUpdateFactory.newLatLng(FragmentarString().separaLL(consulta.consulta!!.get(consulta.consulta!!.size-1).ubication)))
        mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(FragmentarString().separaLL(consulta.consulta!!.get(consulta.consulta!!.size-1).ubication), 5.0F))
    }

    override fun obtenerPosicion(latlong: String) {
        super.obtenerPosicion(latlong)
        mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(FragmentarString().separaLL(latlong), 15.0F))
    }

    private fun abecedario(){
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
}