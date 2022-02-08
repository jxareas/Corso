package com.jonareas.corso.view.splash

import android.content.Context
import android.os.Bundle
import android.os.Handler
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.jonareas.corso.R
import com.jonareas.corso.utils.hideToolbar
import com.jonareas.corso.utils.navigateByAction


class SplashFragment : Fragment() {


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Hide toolbar
        hideToolbar()

        Handler().postDelayed({
            if (onBoardingFinished())
                navigateByAction(SplashFragmentDirections.splashToDogViewPagerFragment())
            else navigateByAction(SplashFragmentDirections.splashToOnBoardingViewPagerFragment())
        }, 1500)

        return inflater.inflate(R.layout.fragment_splash, container, false)
    }

    private fun onBoardingFinished(): Boolean = requireActivity()
            .getSharedPreferences("onBoarding", Context.MODE_PRIVATE)
            .getBoolean("Finished", false)


}