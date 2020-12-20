package ru.vladikadiroff.mediawikiprojects.domain

import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.StateFlow
import ru.vladikadiroff.mediawikiprojects.data.providers.PreferencesProvider
import ru.vladikadiroff.mediawikiprojects.domain.keys.InteractorLoadingStatus

@ExperimentalCoroutinesApi
interface Interactor {

    fun getProjects(query: String? = null, token: String? = null)
            : StateFlow<InteractorLoadingStatus>

    fun getPreference(): PreferencesProvider

}