package com.example.desarrolloresidencia

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.DialogFragment
import com.example.desarrolloresidencia.databinding.FragmentBlankBinding
import com.example.desarrolloresidencia.databinding.FragmentMapaInformacionBinding


class BlankFragment : DialogFragment() {

    private var _binding: FragmentBlankBinding?= null
    private val binding get() = _binding!!

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        //return inflater.inflate(R.layout.fragment_blank, container, false)
        dialog?.window?.setBackgroundDrawableResource(R.drawable.round_corner)
        _binding = FragmentBlankBinding.inflate(inflater, container, false)
        val view = binding.root
        return  view
    }

    override fun onStart() {
        super.onStart()
        val width = (resources.displayMetrics.widthPixels * 0.90).toInt()
        val height = (resources.displayMetrics.heightPixels * 0.85).toInt()
        dialog!!.window?.setLayout(width,height)
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }
}