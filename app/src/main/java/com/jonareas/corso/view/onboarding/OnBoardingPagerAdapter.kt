package com.jonareas.corso.view.onboarding

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.lifecycle.Lifecycle
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.jonareas.corso.view.onboarding.screens.AboutScreen
import com.jonareas.corso.view.onboarding.screens.FinishSetupScreen
import com.jonareas.corso.view.onboarding.screens.WelcomeScreen

class OnBoardingPagerAdapter(
    fragmentManager : FragmentManager, lifecycle : Lifecycle,
    private val fragments : List<Fragment> = listOf(AboutScreen(), WelcomeScreen(), FinishSetupScreen())) :
    FragmentStateAdapter(fragmentManager, lifecycle) {

    override fun getItemCount(): Int = fragments.size

    override fun createFragment(position: Int): Fragment =
        fragments[position]
}