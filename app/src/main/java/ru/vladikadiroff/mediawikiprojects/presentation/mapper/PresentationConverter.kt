package ru.vladikadiroff.mediawikiprojects.presentation.mapper

import ru.vladikadiroff.mediawikiprojects.domain.models.InteractorProjectModel
import ru.vladikadiroff.mediawikiprojects.presentation.models.ProjectModel

class PresentationConverter {
    fun mapper(model: InteractorProjectModel) = ProjectModel(id = model.id, name = model.name)
}