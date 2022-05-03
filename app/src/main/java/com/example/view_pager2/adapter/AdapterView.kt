package com.example.view_pager2.adapter

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.example.view_pager2.FragmentViewPager1
import com.example.view_pager2.di.ListImages

class AdapterView(fragmentActivity: FragmentActivity, private val list : List<ListImages>) : FragmentStateAdapter(fragmentActivity) {

    override fun getItemCount(): Int  = list.size * Int.MAX_VALUE

    override fun createFragment(position: Int): Fragment {
        return FragmentViewPager1.newInstance(list[position % list.size ])
    }
}