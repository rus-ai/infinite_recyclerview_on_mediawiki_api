package ru.vladikadiroff.mediawikiprojects.ui.activities

import android.os.Bundle
import androidx.lifecycle.LiveData
import androidx.lifecycle.Observer
import androidx.navigation.NavController
import androidx.navigation.ui.setupActionBarWithNavController
import dagger.android.support.DaggerAppCompatActivity
import ru.vladikadiroff.mediawikiprojects.R
import ru.vladikadiroff.mediawikiprojects.databinding.ActivityWikiProjectsBinding
import ru.vladikadiroff.mediawikiprojects.utils.extensions.setupWithNavController

class WikiProjectsActivity : DaggerAppCompatActivity() {

    private lateinit var viewBinding: ActivityWikiProjectsBinding

    private var currentNavController: LiveData<NavController>? = null
    private val navGraphIds =
        listOf(R.navigation.navigation_projects, R.navigation.navigation_settings)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewBinding = ActivityWikiProjectsBinding.inflate(layoutInflater)
        setContentView(viewBinding.root)
        setSupportActionBar(viewBinding.toolbar)
        if (savedInstanceState == null) initNavigation()
    }

    private fun initNavigation() {
        val controller = viewBinding.bottomNavigation.setupWithNavController(
            navGraphIds = navGraphIds,
            fragmentManager = supportFragmentManager,
            containerId = R.id.navHost,
            intent = intent
        )
        controller.observe(this, Observer { navController ->
            setupActionBarWithNavController(navController)
        })
        currentNavController = controller
    }

    override fun onRestoreInstanceState(savedInstanceState: Bundle) {
        super.onRestoreInstanceState(savedInstanceState)
        initNavigation()
    }

    override fun onSupportNavigateUp() = currentNavController?.value?.navigateUp() ?: false

}