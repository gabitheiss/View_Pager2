package com.example.view_pager2

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.view_pager2.adapter.AdapterView
import com.example.view_pager2.databinding.ActivityMainBinding
import com.example.view_pager2.di.ListImages
import com.example.view_pager2.di.TabType
import com.google.android.material.tabs.TabLayoutMediator

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val list = listOf(
            ListImages("Cartão de Crédito", "View pager 1", TabType.CARD),
            ListImages("Pay", "View pager 2", TabType.PAY),
            ListImages("Boleto", "View pager 3", TabType.BILLET),
            ListImages("Lista Bônus", "View pager 4", TabType.BONUS),
            ListImages("Pix", "View pager 5", TabType.PIX)
        )

        with(binding) {
            viewPager2.adapter = AdapterView(this@MainActivity, list)
            TabLayoutMediator(tabLayoutView, viewPager2) { tab, position ->
                when (list[position].type) {
                    TabType.CARD -> {
                        tab.setIcon(R.drawable.ic_card)
                        tab.setText(R.string.card)
                    }

                    TabType.PAY -> {
                        tab.setIcon(R.drawable.ic_pay)
                        tab.setText(R.string.pay)
                    }

                    TabType.BILLET -> {
                        tab.setIcon(R.drawable.ic_billet)
                        tab.setText(R.string.billet)
                    }

                    TabType.BONUS -> {
                        tab.setIcon(R.drawable.ic_bonus)
                        tab.setText(R.string.bonus)
                    }

                    TabType.PIX -> {
                        tab.setIcon(R.drawable.ic_pix)
                        tab.setText(R.string.pix)
                    }
                }
            }.attach()
        }
    }
}