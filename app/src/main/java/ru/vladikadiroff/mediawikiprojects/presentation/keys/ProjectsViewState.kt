package ru.vladikadiroff.mediawikiprojects.presentation.keys

import ru.vladikadiroff.mediawikiprojects.utils.interfaces.ProjectModels
import ru.vladikadiroff.mediawikiprojects.utils.interfaces.ViewState

sealed class ProjectsViewState : ViewState {
    object Default : ProjectsViewState()
    object Exception : ProjectsViewState()
    class ShowList(
        val list: List<ProjectModels>,
        val isActiveOnLoadMoreListener: Boolean = true
    ) : ProjectsViewState()
}