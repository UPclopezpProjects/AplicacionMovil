package com.example.desarrolloresidencia.utils

import android.util.Log
import com.google.android.gms.maps.model.LatLng

class FragmentarString {
     fun separaLL(coordenadas: String): LatLng {
        val latlong = coordenadas.split(", ".toRegex()).toTypedArray()
        val latitude = latlong[0].toDouble()
        val longitude = latlong[1].toDouble()
        Log.d("las cordenadas", latitude.toString()+" "+longitude.toString())
        return LatLng(latitude, longitude)
    }
}