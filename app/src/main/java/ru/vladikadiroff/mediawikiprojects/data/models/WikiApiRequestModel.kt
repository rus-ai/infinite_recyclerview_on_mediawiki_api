package ru.vladikadiroff.mediawikiprojects.data.models

import com.squareup.moshi.Json

data class WikiApiRequestModel(
    @Json(name = "continue")
    val state: StateApiModel = StateApiModel(),
    @Json(name = "query")
    val projectsModel: ProjectsApiModel = ProjectsApiModel()
)

data class StateApiModel(
    @Json(name = "apcontinue")
    val metric: String = "",
    @Json(name = "continue")
    val status: String = ""
)

data class ProjectsApiModel(
    @Json(name = "allpages")
    val list: List<ProjectApiModel> = listOf()
)

data class ProjectApiModel(
    @Json(name = "pageid")
    val id: Int = 0,
    @Json(name = "ns")
    val ns: Int = 0,
    @Json(name = "title")
    val name: String = ""
)