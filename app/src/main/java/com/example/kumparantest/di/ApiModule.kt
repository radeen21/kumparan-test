package com.example.kumparantest.di

import com.example.data.webapi.WebApiProvider
import com.example.kumparantest.BuildConfig
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class ApiModule {

    @Provides
    @Singleton
    fun provideWebApiProvider(): WebApiProvider {
        return WebApiProvider(BuildConfig.API_BASE_URL)
    }
}