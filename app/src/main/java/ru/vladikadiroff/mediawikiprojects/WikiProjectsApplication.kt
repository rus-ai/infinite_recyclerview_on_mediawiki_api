package ru.vladikadiroff.mediawikiprojects

import dagger.android.AndroidInjector
import dagger.android.DaggerApplication
import ru.vladikadiroff.mediawikiprojects.di.components.DaggerApplicationComponent

class WikiProjectsApplication : DaggerApplication() {
    override fun applicationInjector(): AndroidInjector<out DaggerApplication> {
        return DaggerApplicationComponent.builder().application(this).build()
    }
}