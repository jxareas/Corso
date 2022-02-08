package com.jonareas.corso.view.onboarding

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.jonareas.corso.databinding.FragmentOnBoardingViewPagerBinding
import com.jonareas.corso.utils.hideToolbar
import com.jonareas.corso.utils.navigateByAction

class OnBoardingViewPagerFragment : Fragment() {

    private var _binding : FragmentOnBoardingViewPagerBinding? = null
    private val binding : FragmentOnBoardingViewPagerBinding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        hideToolbar()
        _binding = FragmentOnBoardingViewPagerBinding.inflate(inflater, container, false)
        setupViews()
        return binding.root
    }

    private fun setupViews() = binding.viewPagerOnBoarding.run {
        val onBoardingAdapter = OnBoardingPagerAdapter(requireActivity().supportFragmentManager, lifecycle)

        adapter = onBoardingAdapter

        binding.next.setOnClickListener {
            if(currentItem == onBoardingAdapter.itemCount - 1) {
                navigateToDogViewPager()
                finishOnBoarding()
            }
            else currentItem++
        }

    }

    private fun finishOnBoarding() =
        requireActivity().getSharedPreferences("onBoarding", Context.MODE_PRIVATE)
            .edit()
            .putBoolean("Finished", true)
            .apply()


    override fun onDestroyView() {
        _binding = null
        super.onDestroyView( )
    }

    private fun navigateToDogViewPager() : Unit =
        navigateByAction(OnBoardingViewPagerFragmentDirections.toDogViewPagerFragment())

}