package ru.vladikadiroff.mediawikiprojects.di.modules.binding

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap
import kotlinx.coroutines.ExperimentalCoroutinesApi
import ru.vladikadiroff.mediawikiprojects.di.factories.ViewModelFactory
import ru.vladikadiroff.mediawikiprojects.di.keys.ViewModelKey
import ru.vladikadiroff.mediawikiprojects.di.scopes.ApplicationScope
import ru.vladikadiroff.mediawikiprojects.presentation.viewmodels.ProjectDetailViewModel
import ru.vladikadiroff.mediawikiprojects.presentation.viewmodels.ProjectsViewModel

@Module
@ExperimentalCoroutinesApi
abstract class ViewModelFactoryModule {

    @Binds
    @ApplicationScope
    internal abstract fun bindViewModelFactory(factory: ViewModelFactory): ViewModelProvider.Factory

    @Binds
    @IntoMap
    @ViewModelKey(ProjectsViewModel::class)
    internal abstract fun projectsViewModel(viewModel: ProjectsViewModel): ViewModel

    @Binds
    @IntoMap
    @ViewModelKey(ProjectDetailViewModel::class)
    internal abstract fun projectDetailViewModel(viewModel: ProjectDetailViewModel): ViewModel

}