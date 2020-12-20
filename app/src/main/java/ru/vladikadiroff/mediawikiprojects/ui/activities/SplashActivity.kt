package ru.vladikadiroff.mediawikiprojects.ui.activities

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatDelegate
import dagger.android.support.DaggerAppCompatActivity
import ru.vladikadiroff.mediawikiprojects.R
import ru.vladikadiroff.mediawikiprojects.data.providers.PreferencesProvider
import ru.vladikadiroff.mediawikiprojects.utils.extensions.withPostDelay
import javax.inject.Inject

class SplashActivity : DaggerAppCompatActivity(R.layout.activity_splash) {

    @Inject
    lateinit var preferences: PreferencesProvider

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setNightMode()
        withPostDelay {
            val intent = Intent(this, WikiProjectsActivity::class.java)
            startActivity(intent)
            overridePendingTransition(android.R.anim.fade_in, android.R.anim.fade_out)
            finish()
        }
    }

    private fun setNightMode() {
        if (preferences.nightmode)
            AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_YES)
        else AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO)
    }

}