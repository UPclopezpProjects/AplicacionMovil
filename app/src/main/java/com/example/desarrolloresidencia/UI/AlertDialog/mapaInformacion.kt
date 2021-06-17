package com.example.desarrolloresidencia.UI.AlertDialog

import android.app.Dialog
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.DialogFragment
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentStatePagerAdapter
import androidx.viewpager.widget.ViewPager
import com.example.desarrolloresidencia.R
import com.example.desarrolloresidencia.UI.ayudas.ayudaCarru
import com.example.desarrolloresidencia.UI.ayudas.ayudaIcono
import com.example.desarrolloresidencia.UI.ayudas.ayudaQR
import com.example.desarrolloresidencia.databinding.FragmentMapaInformacionBinding

private const val NUM_PAGES = 3

class mapaInformacion : DialogFragment() {
    /**
     * The pager widget, which handles animation and allows swiping horizontally to access previous
     * and next wizard steps.
     */
    private lateinit var mPager: ViewPager

    private var _binding: FragmentMapaInformacionBinding?= null
    private val binding get() = _binding!!

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        //return super.onCreateView(inflater, container, savedInstanceState)
        dialog?.window?.setBackgroundDrawableResource(R.drawable.round_corner)
        _binding = FragmentMapaInformacionBinding.inflate(inflater, container, false)

        val view = binding.root
        return  view
    }
    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
        return super.onCreateDialog(savedInstanceState)
        mPager = dialog!!.findViewById(R.id.pager)
        val pagerAdapter = ScreenSlidePagerAdapter(childFragmentManager)
        mPager.adapter = pagerAdapter
    }
    override fun onStart() {
        super.onStart()
        val width = (resources.displayMetrics.widthPixels * 0.90).toInt()
        val height = (resources.displayMetrics.heightPixels * 0.60).toInt()
        dialog!!.window?.setLayout(width, /*ViewGroup.LayoutParams.WRAP_CONTENT*/height)
    }
    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }

    private inner class ScreenSlidePagerAdapter(fm: FragmentManager) : FragmentStatePagerAdapter(fm) {
        override fun getCount(): Int = NUM_PAGES

        override fun getItem(position: Int): Fragment = ayudaQR()
    }
}
