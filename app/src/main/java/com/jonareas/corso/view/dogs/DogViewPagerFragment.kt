package com.jonareas.corso.view.dogs

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.google.android.material.tabs.TabLayoutMediator
import com.jonareas.corso.databinding.FragmentDogViewPagerBinding
import com.jonareas.corso.view.adapter.DogPagerAdapter


class DogViewPagerFragment : Fragment() {

    private var _binding: FragmentDogViewPagerBinding? = null
    private val binding: FragmentDogViewPagerBinding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentDogViewPagerBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupViewPagerWithTabLayout()
    }

    private fun setupViewPagerWithTabLayout(): Unit = binding.run {
        viewPager.adapter = DogPagerAdapter(requireActivity().supportFragmentManager, lifecycle)
        TabLayoutMediator(tabLayout, viewPager) { tab, index ->
            tab.text = DogPagerAdapter.titles[index]
        }.attach()
    }


    override fun onDestroyView() {
        _binding = null
        super.onDestroyView()
    }

}