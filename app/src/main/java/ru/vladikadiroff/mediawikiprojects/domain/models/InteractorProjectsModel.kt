package ru.vladikadiroff.mediawikiprojects.domain.models

data class InteractorProjectsModel(
    val metric: String? = null,
    val projectsList: List<InteractorProjectModel> = listOf()
)