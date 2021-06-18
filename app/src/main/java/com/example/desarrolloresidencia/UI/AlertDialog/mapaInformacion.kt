package com.example.desarrolloresidencia.UI.AlertDialog

import android.app.Dialog
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.*
import androidx.viewpager.widget.ViewPager
import androidx.viewpager2.adapter.FragmentStateAdapter
import androidx.viewpager2.widget.ViewPager2
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
    private lateinit var mPager: ViewPager2

    private var _binding: FragmentMapaInformacionBinding?= null
    private val binding get() = _binding!!

    private var onBoardingPageChangeCallback = object : ViewPager2.OnPageChangeCallback() {
        override fun onPageSelected(position: Int) {
            updateCircleMarker(binding, position)
        }
    }

    private fun updateCircleMarker(binding: FragmentMapaInformacionBinding, position: Int) {
        when (position) {
            0 -> {
                binding.circulo1.setImageResource(R.drawable.circuloverde)
                binding.circulo2.setImageResource(R.drawable.circulogris)
                binding.circulo3.setImageResource(R.drawable.circulogris)
            }
            1 -> {
                binding.circulo1.setImageResource(R.drawable.circulogris)
                binding.circulo2.setImageResource(R.drawable.circuloverde)
                binding.circulo3.setImageResource(R.drawable.circulogris)
            }
            2 -> {
                binding.circulo1.setImageResource(R.drawable.circulogris)
                binding.circulo2.setImageResource(R.drawable.circulogris)
                binding.circulo3.setImageResource(R.drawable.circuloverde)
            }
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        //return super.onCreateView(inflater, container, savedInstanceState)
        dialog?.window?.setBackgroundDrawableResource(R.drawable.round_corner)
        _binding = FragmentMapaInformacionBinding.inflate(inflater, container, false)

        val view = binding.root
        mPager = view!!.findViewById(R.id.pager)
        val pagerAdapter = ScreenSlidePagerAdapter( this.activity)
        mPager.adapter = pagerAdapter
        mPager.registerOnPageChangeCallback(onBoardingPageChangeCallback)

        return  view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)


    }
    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
        val dialog= super.onCreateDialog(savedInstanceState)
        return dialog
    }

    override fun onStart() {
        super.onStart()
        val width = (resources.displayMetrics.widthPixels * 0.90).toInt()
        val height = (resources.displayMetrics.heightPixels * 0.60).toInt()
        dialog!!.window?.setLayout(width, ViewGroup.LayoutParams.WRAP_CONTENT/*height*/)

    }
    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }


    private inner class ScreenSlidePagerAdapter(fa: FragmentActivity?) : FragmentStateAdapter(fa!!) {
        //esto establece el número de componentes del viewpager
        override fun getItemCount(): Int = NUM_PAGES

        //esto va presentando los fragmentos dentro del viewpager
        override fun createFragment(position: Int): Fragment{
            when(position){
                0 -> return ayudaQR()
                1 -> return ayudaCarru()
                2 -> return ayudaIcono()
                else -> return ayudaQR()
            }
        } //=ayudaQR()
    }

}

