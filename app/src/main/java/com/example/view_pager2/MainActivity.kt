package com.example.view_pager2

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.core.content.ContentProviderCompat.requireContext
import androidx.viewpager2.widget.ViewPager2
import com.example.view_pager2.adapter.AdapterView
import com.example.view_pager2.databinding.ActivityMainBinding
import com.example.view_pager2.di.ListImages
import com.google.android.material.tabs.TabLayout
import com.google.android.material.tabs.TabLayoutMediator

class MainActivity : AppCompatActivity() {

    private lateinit var adapterView: AdapterView
    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val list = listOf(
            ListImages("Fragment 1", "View pager 1"),
            ListImages("Fragment 2", "View pager 2"),
            ListImages("Fragment 3", "View pager 3"),
            ListImages("Fragment 4", "View pager 4"),
            ListImages("Fragment 5", "View pager 5")
        )

        with(binding) {
            adapterView = AdapterView(this@MainActivity, list)
            viewPager2.adapter = adapterView

            TabLayoutMediator(tabLayoutView, viewPager2) { tab, position ->
                tab.text = "Página ${position + 1}"
            }.attach()

        }
    }
}