package com.example.desarrolloresidencia.UI.DatosTrazabilidad

import android.annotation.SuppressLint
import android.content.Intent
import android.content.pm.PackageManager
import android.graphics.Color
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.Toast
import androidx.core.app.ActivityCompat
import com.example.desarrolloresidencia.R
import com.google.android.gms.location.FusedLocationProviderClient
import com.google.android.gms.location.LocationCallback
import com.google.android.gms.location.LocationRequest
import com.google.android.gms.location.LocationResult

import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.OnMapReadyCallback
import com.google.android.gms.maps.SupportMapFragment
import com.google.android.gms.maps.model.*

class DatosTrazabilidad : AppCompatActivity(), OnMapReadyCallback {

    private lateinit var mMap: GoogleMap

    val sydney = LatLng(-33.86785899233898, 151.22047064065282)
    val michoacan = LatLng(19.570748230318785, -101.713008550864)
    val oaxaca = LatLng(16.17222473768736, -95.19379468760363)
    val mercado = LatLng(-33.871970665526234, 151.09359167939488)

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


    }

    override fun onMapReady(googleMap: GoogleMap) {
        //mMap.mapType = GoogleMap.MAP_TYPE_HYBRID
        mMap = googleMap
        //mMap.mapType = GoogleMap.MAP_TYPE_HYBRID
        mMap.uiSettings.isZoomControlsEnabled=true

        val polyline1 = googleMap.addPolyline(PolylineOptions()
                .clickable(true)
                .add(michoacan)
                .add(oaxaca)
                .add(sydney)
                .add(mercado)
                .color(Color.RED)
                .width(15F)
                .pattern(arrayListOf<PatternItem>(Dash(50F), Gap(25F)))
        )



                        mMap.addMarker(MarkerOptions().position(michoacan).title("Productora de aguacate"))
        mMap.addMarker(MarkerOptions().position(oaxaca).title("Puerto Exportador de Veracruz"))
        mMap.addMarker(MarkerOptions().position(sydney).title("Puerto de Importación de Sidney"))
        mMap.addMarker(MarkerOptions().position(mercado).title("Usted está comprando aquí"))
        mMap.moveCamera(CameraUpdateFactory.newLatLng(oaxaca))
    }


}