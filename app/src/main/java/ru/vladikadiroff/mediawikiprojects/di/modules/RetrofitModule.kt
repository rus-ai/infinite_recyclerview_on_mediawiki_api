package ru.vladikadiroff.mediawikiprojects.di.modules

import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import dagger.Module
import dagger.Provides
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import ru.vladikadiroff.mediawikiprojects.data.api.WikiService
import ru.vladikadiroff.mediawikiprojects.di.scopes.ApplicationScope
import ru.vladikadiroff.mediawikiprojects.utils.BASE_URL

@Module
class RetrofitModule {

    private val moshi = Moshi.Builder()
        .add(KotlinJsonAdapterFactory())
        .build()

    @Provides
    @ApplicationScope
    fun provideRetrofit(client: OkHttpClient) = Retrofit.Builder()
        .baseUrl(BASE_URL)
        .client(client)
        .addConverterFactory(MoshiConverterFactory.create(moshi).asLenient())
        .build()

    @Provides
    @ApplicationScope
    fun provideOkHttpClient(): OkHttpClient {
        val loggingInterceptor = HttpLoggingInterceptor()
        loggingInterceptor.level = HttpLoggingInterceptor.Level.BODY
        return OkHttpClient.Builder().addInterceptor(loggingInterceptor).build()
    }

    @Provides
    @ApplicationScope
    fun provideWikiService(retrofit: Retrofit) = retrofit.create(WikiService::class.java)

}