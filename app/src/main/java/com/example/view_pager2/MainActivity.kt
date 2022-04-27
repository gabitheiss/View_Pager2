package com.example.view_pager2

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.viewpager2.widget.ViewPager2
import com.example.view_pager2.adapter.AdapterView
import com.example.view_pager2.di.ListImages

class MainActivity : AppCompatActivity() {

    private lateinit var viewPager : ViewPager2
    private lateinit var adapterView : AdapterView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        viewPager = findViewById(R.id.view_pager2)

        val list = listOf(
            ListImages("Fragment 1", "View pager 1"),
            ListImages("Fragment 2", "View pager 2"),
            ListImages("Fragment 3", "View pager 3"),
        )

        adapterView = AdapterView(this, list)
        viewPager.adapter = adapterView

    }
}