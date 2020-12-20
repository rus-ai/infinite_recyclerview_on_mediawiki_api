package ru.vladikadiroff.mediawikiprojects.data.providers

import ru.vladikadiroff.mediawikiprojects.data.api.WikiService

class ServiceProvider(private val service: WikiService) {

    suspend fun getWikiProjects(options: Map<String, String>, query: String?, token: String?) =
        service.getProjects(options, query, token)

}