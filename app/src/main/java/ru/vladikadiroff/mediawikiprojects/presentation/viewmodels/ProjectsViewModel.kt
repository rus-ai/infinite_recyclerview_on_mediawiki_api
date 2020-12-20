package ru.vladikadiroff.mediawikiprojects.presentation.viewmodels

import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch
import ru.vladikadiroff.mediawikiprojects.domain.InteractorImpl
import ru.vladikadiroff.mediawikiprojects.domain.keys.InteractorLoadingStatus
import ru.vladikadiroff.mediawikiprojects.presentation.keys.ProjectsViewState
import ru.vladikadiroff.mediawikiprojects.presentation.mapper.PresentationConverter
import ru.vladikadiroff.mediawikiprojects.presentation.models.FooterExceptionModel
import ru.vladikadiroff.mediawikiprojects.presentation.models.FooterLoadingModel
import ru.vladikadiroff.mediawikiprojects.utils.abstracts.BaseViewModel
import ru.vladikadiroff.mediawikiprojects.utils.extensions.set
import ru.vladikadiroff.mediawikiprojects.utils.extensions.showLog
import ru.vladikadiroff.mediawikiprojects.utils.interfaces.ProjectModels
import javax.inject.Inject

@ExperimentalCoroutinesApi
class ProjectsViewModel @Inject constructor(
    private val interactor: InteractorImpl,
    private val converter: PresentationConverter
) : BaseViewModel<ProjectsViewState>(ProjectsViewState.Default) {

    private var pagerToken: String? = null
    private var searchQuery: String? = null
    private val projectList = mutableListOf<ProjectModels>()

    fun refresh() {
        viewModelScope.launch {
            projectList.clear()
            observeOnLoadingState(interactor.getProjects(searchQuery))
        }
    }

    fun loadNewItems() {
        viewModelScope.launch {
            observeOnLoadingState(interactor.getProjects(searchQuery, pagerToken))
        }
    }

    fun searchNewItems(query: String) {
        if (searchQuery != query) {
            searchQuery = query
            pagerToken = null
            if (projectList.isNotEmpty() &&
                projectList[0].VIEW_TYPE == ProjectModels.ViewTypes.FOOTER_LOADING
            )
                return
            projectList.clear()
            projectList.add(FooterLoadingModel())
            viewState.set(ProjectsViewState.ShowList(createNewListForDiffUtil(), false))
            viewModelScope.launch {
                observeOnLoadingState(interactor.getProjects(query))
            }
        }
    }

    fun getSearchQuery() = searchQuery

    fun getPreloadStatus() = interactor.getPreference().preload

    private suspend fun observeOnLoadingState(state: StateFlow<InteractorLoadingStatus>) {
        state.collect { status ->
            when (status) {
                is InteractorLoadingStatus.Default -> showLog("observe on FlowState")
                is InteractorLoadingStatus.Success -> onSuccess(status)
                is InteractorLoadingStatus.Loading -> onLoading()
                is InteractorLoadingStatus.Exception -> onException()
            }
        }
    }

    private fun onSuccess(status: InteractorLoadingStatus.Success) {
        if (!projectList.isNullOrEmpty()) {
            val lastPosition = projectList.count() - 1
            if (projectList[lastPosition].VIEW_TYPE != ProjectModels.ViewTypes.MODEL)
                projectList.removeAt(lastPosition)
        }
        pagerToken = status.data.metric
        projectList.addAll(status.data.projectsList.map(converter::mapper))
        viewState.set(ProjectsViewState.ShowList(createNewListForDiffUtil()))
    }

    private fun onLoading() {
        if (!projectList.isNullOrEmpty()) addFooter(ProjectModels.ViewTypes.FOOTER_LOADING)
    }

    private fun onException() {
        if (projectList.isNotEmpty() && projectList.count() > 2)
            addFooter(ProjectModels.ViewTypes.FOOTER_EXCEPTION)
        else viewState.set(ProjectsViewState.Exception)
    }

    private fun addFooter(type: ProjectModels.ViewTypes) {
        val lastPosition = projectList.count() - 1
        if (projectList[lastPosition].VIEW_TYPE == type) return
        if (projectList[lastPosition].VIEW_TYPE != ProjectModels.ViewTypes.MODEL)
            projectList.removeAt(lastPosition)
        when (type) {
            ProjectModels.ViewTypes.FOOTER_LOADING -> {
                projectList.add(FooterLoadingModel())
                viewState.set(ProjectsViewState.ShowList(createNewListForDiffUtil(), false))
            }
            ProjectModels.ViewTypes.FOOTER_EXCEPTION -> {
                projectList.add(FooterExceptionModel())
                viewState.set(ProjectsViewState.ShowList(createNewListForDiffUtil(), false))
            }
            else -> return
        }
    }

    private fun createNewListForDiffUtil(): List<ProjectModels> {
        val list = mutableListOf<ProjectModels>()
        list.addAll(projectList)
        return list
    }

}