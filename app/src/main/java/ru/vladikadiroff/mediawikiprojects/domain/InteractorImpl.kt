package ru.vladikadiroff.mediawikiprojects.domain

import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import ru.vladikadiroff.mediawikiprojects.data.RepositoryImpl
import ru.vladikadiroff.mediawikiprojects.domain.keys.InteractorLoadingStatus
import ru.vladikadiroff.mediawikiprojects.domain.mapper.InteractorConverter
import ru.vladikadiroff.mediawikiprojects.utils.abstracts.CoroutineInteractor
import ru.vladikadiroff.mediawikiprojects.utils.extensions.set

@ExperimentalCoroutinesApi
class InteractorImpl(
    private val repository: RepositoryImpl,
    private val converter: InteractorConverter
) : CoroutineInteractor(), Interactor {

    override fun getPreference() = repository.getPreferences()

    override fun getProjects(query: String?, token: String?) = loadProjects(query, token) as
            StateFlow<InteractorLoadingStatus>

    private fun loadProjects(query: String? = null, token: String? = null)
            : MutableStateFlow<InteractorLoadingStatus> {
        val status = MutableStateFlow<InteractorLoadingStatus>(InteractorLoadingStatus.Default)
        launchBackground {
            status.set(InteractorLoadingStatus.Loading)
            try {
                val request = repository.getWikiProjects(query, token)
                status.set(InteractorLoadingStatus.Success(converter.map(request)))

            } catch (e: Exception) {
                status.set(InteractorLoadingStatus.Exception(e))
            }
        }
        return status
    }

}