package ru.vladikadiroff.mediawikiprojects.ui.fragments

import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatDelegate
import androidx.preference.PreferenceFragmentCompat
import androidx.preference.SwitchPreferenceCompat
import ru.vladikadiroff.mediawikiprojects.R
import ru.vladikadiroff.mediawikiprojects.utils.APP_PREFERENCES_NIGHTMODE

class SettingsFragment : PreferenceFragmentCompat() {

    override fun onCreatePreferences(savedInstanceState: Bundle?, rootKey: String?) {
        addPreferencesFromResource(R.xml.preferences)
        val nightmodePreference = findPreference<SwitchPreferenceCompat>(APP_PREFERENCES_NIGHTMODE)
        nightmodePreference?.setOnPreferenceChangeListener { _, _ ->
            setNightMode(!nightmodePreference.isChecked)
            true
        }
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        listView.removeItemDecorationAt(0) // Remove standard divider
    }

    private fun setNightMode(nightMode: Boolean) {
        if (nightMode) AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_YES)
        else AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO)
    }

}