package com.example.dte_2603_prosjekt.screens.settings

import android.os.Bundle
import androidx.preference.PreferenceFragmentCompat
import com.example.dte_2603_prosjekt.R

class PreferenceFragment : PreferenceFragmentCompat() {

    override fun onCreatePreferences(savedInstanceState: Bundle?, rootKey: String?) {
        setPreferencesFromResource(R.xml.preference_ui, rootKey)
    }
}