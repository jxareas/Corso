package com.jonareas.corso.view.settings

import android.os.Bundle
import androidx.preference.PreferenceFragmentCompat
import com.jonareas.corso.R

class SettingsFragment : PreferenceFragmentCompat() {
    override fun onCreatePreferences(savedInstanceState: Bundle?, rootKey: String?) {
        setPreferencesFromResource(R.xml.preferences, rootKey)
    }
}