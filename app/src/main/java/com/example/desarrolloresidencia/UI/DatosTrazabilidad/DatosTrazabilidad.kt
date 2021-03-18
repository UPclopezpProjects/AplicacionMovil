package com.example.desarrolloresidencia.UI.DatosTrazabilidad

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import com.example.desarrolloresidencia.R

import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.OnMapReadyCallback
import com.google.android.gms.maps.SupportMapFragment
import com.google.android.gms.maps.model.LatLng
import com.google.android.gms.maps.model.MarkerOptions

class DatosTrazabilidad : AppCompatActivity(), OnMapReadyCallback {

    private lateinit var mMap: GoogleMap


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
        mMap = googleMap

        // Add a marker in Sydney and move the camera
        val sydney = LatLng(-33.86785899233898, 151.22047064065282)
        val michoacan = LatLng(19.570748230318785, -101.713008550864)
        val veracruz = LatLng(19.199792279167177, -96.1308935976641)
        val mercado = LatLng(-33.871970665526234, 151.09359167939488)
        mMap.addMarker(MarkerOptions().position(michoacan).title("Productora de aguacate"))
        mMap.addMarker(MarkerOptions().position(veracruz).title("Puerto Exportador de Veracruz"))
        mMap.addMarker(MarkerOptions().position(sydney).title("Puerto de Importación de Sidney"))
        mMap.addMarker(MarkerOptions().position(mercado).title("Usted está comprando aquí"))
        mMap.moveCamera(CameraUpdateFactory.newLatLng(michoacan))
    }
}