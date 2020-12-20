package ru.vladikadiroff.mediawikiprojects.data.providers

import android.content.SharedPreferences
import ru.vladikadiroff.mediawikiprojects.utils.*

class PreferencesProvider(private val preferences: SharedPreferences) {

    val preload: Boolean
        get() = getPreferenceValue(APP_PREFERENCES_PRELOAD, false) as Boolean
    val nightmode: Boolean
        get() = getPreferenceValue(APP_PREFERENCES_NIGHTMODE, false) as Boolean
    val requestOptions: Map<String, String>
        get() = makeRequestOptions()
    val limit: String
        get() = getPreferenceValue(APP_PREFERENCES_DOWNLOADS_ITEMS_LIMIT,
            DEFAULT_DOWNLOAD_ITEMS_LIMIT) as String

    private fun makeRequestOptions(): Map<String, String> {
        val map = mutableMapOf<String, String>()
        with(map) {
            put(QUERY_ACTION, DEFAULT_QUERY_ACTION)
            put(QUERY_FORMAT, DEFAULT_QUERY_FORMAT)
            put(QUERY_PAGES, DEFAULT_QUERY_PAGES)
            put(QUERY_ITEMS_LIMIT, limit)
        }
        return map
    }

    private fun getPreferenceValue(preferenceName: String, defaultValue: Any): Any {
        return when (defaultValue) {
            is Int -> preferences.getInt(preferenceName, defaultValue)
            is String -> preferences.getString(preferenceName, defaultValue) ?: defaultValue
            is Boolean -> preferences.getBoolean(preferenceName, defaultValue)
            else -> defaultValue
        }
    }

}