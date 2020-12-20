package ru.vladikadiroff.mediawikiprojects.domain.mapper

import ru.vladikadiroff.mediawikiprojects.data.models.ProjectApiModel
import ru.vladikadiroff.mediawikiprojects.data.models.WikiApiRequestModel
import ru.vladikadiroff.mediawikiprojects.domain.models.InteractorProjectModel
import ru.vladikadiroff.mediawikiprojects.domain.models.InteractorProjectsModel

class InteractorConverter {

    fun map(model: WikiApiRequestModel) = InteractorProjectsModel(
        metric = model.state.metric,
        projectsList = model.projectsModel.list.map { value -> fromApi(value) }
    )

    private fun fromApi(model: ProjectApiModel) = InteractorProjectModel(
        name = model.name,
        id = model.id.toString()
    )
}