package com.example.desarrolloresidencia.UI

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.lifecycle.Lifecycle
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.example.desarrolloresidencia.databinding.ActivityAboutBinding
import com.google.android.material.tabs.TabLayoutMediator

private const val NUM_TABS = 2
val animalsArray = arrayOf(
    "Creditos",
    "Desarrolladores"
)

class About : AppCompatActivity() {

    private lateinit var binding: ActivityAboutBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityAboutBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val viewPager = binding.pager
        val tabLayout = binding.TLListas

        val adapter = ViewPagerAdapter(supportFragmentManager, lifecycle)
        viewPager.adapter = adapter

        TabLayoutMediator(tabLayout, viewPager) { tab, position ->
            tab.text = animalsArray[position]
        }.attach()

        binding.BTVolver.setOnClickListener {
            finish()
        }
    }

    public class ViewPagerAdapter(fragmentManager: FragmentManager, lifecycle: Lifecycle) :
        FragmentStateAdapter(fragmentManager, lifecycle) {

        override fun getItemCount(): Int {
            return NUM_TABS
        }

        override fun createFragment(position: Int): Fragment {
            when (position) {
                0 -> return Creditos()
                1 -> return Desarrolladores()
            }
            return Creditos()
        }
    }
}
