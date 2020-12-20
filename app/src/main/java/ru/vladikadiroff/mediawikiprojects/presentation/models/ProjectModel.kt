package ru.vladikadiroff.mediawikiprojects.presentation.models

import ru.vladikadiroff.mediawikiprojects.utils.interfaces.ProjectModels

data class ProjectModel(val id: String = "", val name: String = "") : ProjectModels {
    override val VIEW_TYPE = ProjectModels.ViewTypes.MODEL
}