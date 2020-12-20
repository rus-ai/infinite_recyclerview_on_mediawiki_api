package ru.vladikadiroff.mediawikiprojects.di.modules.binding

import dagger.Module
import dagger.android.ContributesAndroidInjector
import ru.vladikadiroff.mediawikiprojects.di.scopes.ActivityScope
import ru.vladikadiroff.mediawikiprojects.ui.activities.SplashActivity
import ru.vladikadiroff.mediawikiprojects.ui.activities.WikiProjectsActivity

@Module
abstract class ActivitiesBindingModule {

    @ActivityScope
    @ContributesAndroidInjector
    abstract fun provideSplashActivity(): SplashActivity

    @ActivityScope
    @ContributesAndroidInjector(modules = [FragmentsBindingModule::class])
    abstract fun provideWikiProjectsActivity(): WikiProjectsActivity

}