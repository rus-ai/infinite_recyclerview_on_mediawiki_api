package ru.vladikadiroff.mediawikiprojects.data

import ru.vladikadiroff.mediawikiprojects.data.models.WikiApiRequestModel
import ru.vladikadiroff.mediawikiprojects.data.providers.PreferencesProvider

interface Repository {

    suspend fun getWikiProjects(query: String? = null, token: String? = null): WikiApiRequestModel

    fun getPreferences(): PreferencesProvider

}