package com.jonareas.corso.view.adapter

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.lifecycle.Lifecycle
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.jonareas.corso.view.dogs.DogListFragment
import com.jonareas.corso.view.settings.SettingsFragment

class DogPagerAdapter(
    fragmentManager: FragmentManager, lifecycle: Lifecycle,
    private val fragments: List<Fragment> = listOf(DogListFragment(), SettingsFragment())
) :
    FragmentStateAdapter(fragmentManager, lifecycle) {

    companion object {
        val titles = listOf("Dog List", "Settings")
    }

    override fun getItemCount(): Int = fragments.size

    override fun createFragment(position: Int): Fragment =
        fragments[position]
}