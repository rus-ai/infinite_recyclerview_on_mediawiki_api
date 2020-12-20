package ru.vladikadiroff.mediawikiprojects.di.modules

import dagger.Module
import dagger.Provides
import ru.vladikadiroff.mediawikiprojects.data.RepositoryImpl
import ru.vladikadiroff.mediawikiprojects.di.scopes.ApplicationScope
import ru.vladikadiroff.mediawikiprojects.domain.InteractorImpl
import ru.vladikadiroff.mediawikiprojects.domain.mapper.InteractorConverter
import ru.vladikadiroff.mediawikiprojects.presentation.mapper.PresentationConverter

@Module
class InteractorModule {

    @Provides
    fun provideInteractorConverter() = InteractorConverter()

    @Provides
    fun providePresentationConverter() = PresentationConverter()

    @Provides
    @ApplicationScope
    fun provideInteractor(repository: RepositoryImpl, converter: InteractorConverter) =
        InteractorImpl(repository, converter)

}