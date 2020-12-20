package ru.vladikadiroff.mediawikiprojects.data.api

import retrofit2.http.GET
import retrofit2.http.Query
import retrofit2.http.QueryMap
import ru.vladikadiroff.mediawikiprojects.data.models.WikiApiRequestModel
import ru.vladikadiroff.mediawikiprojects.utils.QUERY_BY_NAME
import ru.vladikadiroff.mediawikiprojects.utils.QUERY_METRIC

interface WikiService {

    @GET("./api.php")
    suspend fun getProjects(
        @QueryMap options: Map<String, String>,
        @Query(QUERY_BY_NAME) query: String?,
        @Query(QUERY_METRIC) token: String?
    ): WikiApiRequestModel

}