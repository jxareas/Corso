package com.jonareas.corso.view.onboarding.screens

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.jonareas.corso.databinding.OnboardingScreenAboutBinding


class AboutScreen : Fragment() {

    private var _binding : OnboardingScreenAboutBinding? = null
    private val binding : OnboardingScreenAboutBinding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = OnboardingScreenAboutBinding.inflate(inflater, container, false)
        return binding.root
    }


    override fun onDestroyView() {
        _binding = null
        super.onDestroyView()
    }

}