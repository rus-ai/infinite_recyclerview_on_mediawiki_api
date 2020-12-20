package ru.vladikadiroff.mediawikiprojects.domain.keys

import ru.vladikadiroff.mediawikiprojects.domain.models.InteractorProjectsModel

sealed class InteractorLoadingStatus {
    object Default : InteractorLoadingStatus()
    object Loading : InteractorLoadingStatus()
    class Success(val data: InteractorProjectsModel) : InteractorLoadingStatus()
    class Exception(val exception: java.lang.Exception) : InteractorLoadingStatus()
}