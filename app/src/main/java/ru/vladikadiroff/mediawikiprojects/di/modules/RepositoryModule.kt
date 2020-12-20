package ru.vladikadiroff.mediawikiprojects.di.modules

import android.content.SharedPreferences
import dagger.Module
import dagger.Provides
import ru.vladikadiroff.mediawikiprojects.di.scopes.ApplicationScope
import ru.vladikadiroff.mediawikiprojects.data.RepositoryImpl
import ru.vladikadiroff.mediawikiprojects.data.api.WikiService
import ru.vladikadiroff.mediawikiprojects.data.providers.PreferencesProvider
import ru.vladikadiroff.mediawikiprojects.data.providers.ServiceProvider

@Module
class RepositoryModule {

    @Provides
    @ApplicationScope
    fun provideServiceProvider(service: WikiService) = ServiceProvider(service)

    @Provides
    @ApplicationScope
    fun providePreferenceProvider(preferences: SharedPreferences) = PreferencesProvider(preferences)

    @Provides
    @ApplicationScope
    fun provideRepository(service: ServiceProvider, preferences: PreferencesProvider) =
        RepositoryImpl(service, preferences)

}