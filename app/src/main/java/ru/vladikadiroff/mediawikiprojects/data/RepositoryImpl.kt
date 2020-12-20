package ru.vladikadiroff.mediawikiprojects.data

import ru.vladikadiroff.mediawikiprojects.data.providers.PreferencesProvider
import ru.vladikadiroff.mediawikiprojects.data.providers.ServiceProvider

class RepositoryImpl(
    private val service: ServiceProvider,
    private val preferences: PreferencesProvider
) : Repository {

    override suspend fun getWikiProjects(query: String?, token: String?) =
        service.getWikiProjects(preferences.requestOptions, query, token)

    override fun getPreferences() = preferences

}