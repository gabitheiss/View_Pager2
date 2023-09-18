package com.example.view_pager2

import android.graphics.PorterDuff
import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import com.example.view_pager2.adapter.AdapterView
import com.example.view_pager2.databinding.ActivityMainBinding
import com.example.view_pager2.databinding.TabSelectorIconViewBinding
import com.example.view_pager2.di.ListImages
import com.example.view_pager2.di.TabType
import com.google.android.material.tabs.TabLayout
import com.google.android.material.tabs.TabLayoutMediator

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private lateinit var tabLayoutBinding: TabSelectorIconViewBinding

    val list = listOf(
        ListImages("Cartão de Crédito", "View pager 1", TabType.CARD),
        ListImages("Pay", "View pager 2", TabType.PAY),
        ListImages("Boleto", "View pager 3", TabType.BILLET),
        ListImages("Lista Bônus", "View pager 4", TabType.BONUS),
        ListImages("Pix", "View pager 5", TabType.PIX)
    )

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        with(binding) {
            viewPager2.adapter = AdapterView(this@MainActivity, list)
            TabLayoutMediator(tabLayoutView, viewPager2) { _, position ->
                when (list[position].type) {
                    TabType.CARD -> {
                        setupIconAndLabelItemTabLayout(tabLayoutView, R.drawable.ic_card, R.string.card)
                    }

                    TabType.PAY -> {
                        setupIconAndLabelItemTabLayout(tabLayoutView, R.drawable.ic_pay, R.string.pay)
                    }

                    TabType.BILLET -> {
                        setupIconAndLabelItemTabLayout(tabLayoutView, R.drawable.ic_billet, R.string.billet)
                    }

                    TabType.BONUS -> {
                        setupIconAndLabelItemTabLayout(tabLayoutView, R.drawable.ic_bonus, R.string.bonus)
                    }

                    TabType.PIX -> {
                        setupIconAndLabelItemTabLayout(tabLayoutView, R.drawable.ic_pix, R.string.pix)
                    }
                }
            }.attach()

            buttonBrown.setOnClickListener {
                tabLayoutView.addOnTabSelectedListener(object : TabLayout.OnTabSelectedListener {
                    override fun onTabSelected(tab: TabLayout.Tab?) {
                        tab?.let {
                            updateTabIcon(tabLayoutView, it.position, R.color.brown_mongoose)
                        }
                    }

                    override fun onTabUnselected(tab: TabLayout.Tab?) {
                        tab?.let {
                            updateTabIcon(tabLayoutView, it.position, R.color.black)
                        }
                    }

                    override fun onTabReselected(tab: TabLayout.Tab?) {
                        //do nothing
                    }
                })
            }
        }
    }


    private fun updateTabIcon(tabLayoutView: TabLayout, position: Int, color: Int) {
        val tab = tabLayoutView.getTabAt(position)
        tab?.let {
            if (it.customView is View) {
                val binding = TabSelectorIconViewBinding.bind(it.customView!!)
                binding.apply {
                    iconTabView.setColorFilter(ContextCompat.getColor(this@MainActivity, color), PorterDuff.Mode.SRC_IN)
                    tabLabelView.setTextColor(ContextCompat.getColor(this@MainActivity, color))
                }
            }
        }
    }

    private fun setupIconAndLabelItemTabLayout(tabLayoutView: TabLayout, icon: Int, label: Int) {
        tabLayoutBinding = TabSelectorIconViewBinding.inflate(layoutInflater)
        val newTabIndicator = tabLayoutView.newTab()
        tabLayoutBinding.apply {
            iconTabView.setImageResource(icon)
            tabLabelView.setText(label)
        }
        newTabIndicator.customView = tabLayoutBinding.root
        tabLayoutView.addTab(newTabIndicator)
    }
}