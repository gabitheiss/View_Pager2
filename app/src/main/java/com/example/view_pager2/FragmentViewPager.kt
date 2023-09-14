package com.example.view_pager2

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import com.example.view_pager2.databinding.FragmentViewPagerBinding
import com.example.view_pager2.di.ListImages

class FragmentViewPager : Fragment(R.layout.fragment_view_pager) {

    private lateinit var binding: FragmentViewPagerBinding

    companion object{
        const val KEY_TITLE = "title"
        const val KEY_DESCRIPTION = "description"

        fun newInstance(list : ListImages) : FragmentViewPager{
            val args = Bundle().apply {
                putString(KEY_TITLE, list.title)
                putString(KEY_DESCRIPTION, list.description)
            }

            val fragment = FragmentViewPager()
            fragment.arguments = args
            return fragment
        }
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = FragmentViewPagerBinding.bind(view)
        val title = binding.idName
        val description = binding.idDescription

        arguments?.let {
            title.text = it.getString(KEY_TITLE)
            description.text = it.getString(KEY_DESCRIPTION)
        }
    }
}