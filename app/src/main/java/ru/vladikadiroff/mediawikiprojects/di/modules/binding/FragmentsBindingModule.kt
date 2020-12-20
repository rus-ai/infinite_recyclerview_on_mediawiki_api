package ru.vladikadiroff.mediawikiprojects.di.modules.binding

import dagger.Module
import dagger.android.ContributesAndroidInjector
import kotlinx.coroutines.ExperimentalCoroutinesApi
import ru.vladikadiroff.mediawikiprojects.di.scopes.FragmentScope
import ru.vladikadiroff.mediawikiprojects.ui.fragments.ProjectDetailFragment
import ru.vladikadiroff.mediawikiprojects.ui.fragments.ProjectsFragment

@Module
abstract class FragmentsBindingModule {

    @FragmentScope
    @ExperimentalCoroutinesApi
    @ContributesAndroidInjector
    internal abstract fun provideProjectsFragment(): ProjectsFragment

    @FragmentScope
    @ContributesAndroidInjector
    internal abstract fun provideProjectDetailFragment(): ProjectDetailFragment

}