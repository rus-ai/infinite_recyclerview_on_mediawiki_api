package ru.vladikadiroff.mediawikiprojects.di.modules

import android.app.Application
import android.content.Context
import androidx.preference.PreferenceManager
import dagger.Module
import dagger.Provides
import ru.vladikadiroff.mediawikiprojects.di.qualifiers.ApplicationContext
import ru.vladikadiroff.mediawikiprojects.di.scopes.ApplicationScope

@Module
class ApplicationModule {

    @Provides
    @ApplicationScope
    @ApplicationContext
    fun prividesApplicationContext(application: Application) = application.applicationContext

    @Provides
    @ApplicationScope
    fun provideSharedPreferences(@ApplicationContext context: Context) =
        PreferenceManager.getDefaultSharedPreferences(context)

}