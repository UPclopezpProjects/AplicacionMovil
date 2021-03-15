package com.example.desarrolloresidencia.UI.DatosTrazabilidad

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.desarrolloresidencia.Network.model.Trazabilidad.Puntos
import com.example.desarrolloresidencia.R

class ListaPuntos : Fragment() {

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle? ): View? {
        // Inflate the layout for this fragment
        val vista = inflater.inflate(R.layout.fragment_lista_puntos, container, false)
        return vista

    }

    private fun inflarRecycler(){
    }


    }
