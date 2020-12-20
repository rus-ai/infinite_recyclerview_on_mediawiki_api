package ru.vladikadiroff.mediawikiprojects.di.components

import android.app.Application
import dagger.BindsInstance
import dagger.Component
import dagger.android.AndroidInjector
import dagger.android.support.AndroidSupportInjectionModule
import ru.vladikadiroff.mediawikiprojects.WikiProjectsApplication
import ru.vladikadiroff.mediawikiprojects.di.modules.ApplicationModule
import ru.vladikadiroff.mediawikiprojects.di.modules.InteractorModule
import ru.vladikadiroff.mediawikiprojects.di.modules.RepositoryModule
import ru.vladikadiroff.mediawikiprojects.di.modules.RetrofitModule
import ru.vladikadiroff.mediawikiprojects.di.modules.binding.ActivitiesBindingModule
import ru.vladikadiroff.mediawikiprojects.di.modules.binding.ViewModelFactoryModule
import ru.vladikadiroff.mediawikiprojects.di.scopes.ApplicationScope

@ApplicationScope
@Component(
    modules = [
        AndroidSupportInjectionModule::class,
        ActivitiesBindingModule::class,
        ViewModelFactoryModule::class,
        ApplicationModule::class,
        RetrofitModule::class,
        RepositoryModule::class,
        InteractorModule::class]
)
interface ApplicationComponent : AndroidInjector<WikiProjectsApplication> {
    @Component.Builder
    interface Builder {
        @BindsInstance
        fun application(application: Application): Builder
        fun build(): ApplicationComponent
    }
}