package com.example.view_pager2

import android.graphics.PorterDuff
import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import androidx.core.view.isVisible
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
            linearLayoutInverted.isVisible = false
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

            buttonBlack.setOnClickListener {
                linearLayoutInverted.isVisible = true
                if (!linearLayout.isVisible) {
                    linearLayout.isVisible = true
                }
                tabLayoutView.setSelectedTabIndicatorColor(ContextCompat.getColor(this@MainActivity, R.color.black))
                setupTabLayoutPrime(tabLayoutView, R.color.black, R.color.black)
            }

            buttonBrown.setOnClickListener {
                linearLayoutInverted.isVisible = true
                if (!linearLayout.isVisible) {
                    linearLayout.isVisible = true
                }
                tabLayoutView.setSelectedTabIndicatorColor(ContextCompat.getColor(this@MainActivity, R.color.brown_mongoose))
                setupTabLayoutPrime(tabLayoutView, R.color.brown_mongoose, R.color.black)
            }

            buttonBlackInverted.setOnClickListener {
                linearLayoutInverted.isVisible = false
                linearLayout.isVisible = true
                if (!linearLayoutInverted.isVisible) {
                    linearLayout.isVisible = true
                }
                tabLayoutView.setSelectedTabIndicatorColor(ContextCompat.getColor(this@MainActivity, R.color.black))
                setupTabLayoutPrime(tabLayoutView, R.color.black, R.color.black)
            }

            buttonBrownInverted.setOnClickListener {
                linearLayoutInverted.isVisible = false
                linearLayout.isVisible = true
                if (!linearLayoutInverted.isVisible) {
                    linearLayout.isVisible = true
                }
                tabLayoutView.setSelectedTabIndicatorColor(ContextCompat.getColor(this@MainActivity, R.color.brown_mongoose))
                setupTabLayoutPrime(tabLayoutView, R.color.brown_mongoose, R.color.black)
            }

        }
    }


    private fun setupTabLayoutPrime(tabIndicatorMethodView: TabLayout, colorSelected: Int, colorUnselected: Int) {
        tabIndicatorMethodView.addOnTabSelectedListener(object : TabLayout.OnTabSelectedListener {
            override fun onTabSelected(tab: TabLayout.Tab?) {
                tab?.let {
                    updateColorIconAndLabel(tabIndicatorMethodView, it.position, colorSelected)
                }
            }

            override fun onTabUnselected(tab: TabLayout.Tab?) {
                tab?.let {
                    updateColorIconAndLabel(tabIndicatorMethodView, it.position, colorUnselected)
                }
            }

            override fun onTabReselected(tab: TabLayout.Tab?) {
                //do nothing
            }
        })
    }

    private fun updateColorIconAndLabel(tabLayoutView: TabLayout, position: Int, color: Int) {
        val tab = tabLayoutView.getTabAt(position)
        tab?.let {
            if (it.customView is View) {
                TabSelectorIconViewBinding.bind(it.customView!!).apply {
                    tabIconView.setColorFilter(ContextCompat.getColor(this@MainActivity, color), PorterDuff.Mode.SRC_IN)
                    tabLabelView.setTextColor(ContextCompat.getColor(this@MainActivity, color))
                }
            }
        }
    }

    private fun setupIconAndLabelItemTabLayout(tabLayoutView: TabLayout, icon: Int, label: Int) {
        tabLayoutBinding = TabSelectorIconViewBinding.inflate(layoutInflater)
        val newTabIndicator = tabLayoutView.newTab()
        tabLayoutBinding.apply {
            tabIconView.setImageResource(icon)
            tabLabelView.setText(label)
        }
        newTabIndicator.customView = tabLayoutBinding.root
        tabLayoutView.addTab(newTabIndicator)
    }
}