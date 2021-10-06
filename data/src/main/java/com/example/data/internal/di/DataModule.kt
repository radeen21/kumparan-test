package com.example.data.internal.di

import com.example.data.apis.ApiService
import com.example.data.repoimpl.PostRepoImpl
import com.example.data.webapi.WebApiProvider
import com.example.domain.repository.PostRepository
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class DataModule {

    @Provides
    @Singleton
    fun provideApiService(webApiProvider: WebApiProvider): ApiService {
        return webApiProvider.getRetrofit()
            .create(ApiService::class.java)
    }

    @Provides
    @Singleton
    fun providePostRepository(apiService: ApiService): PostRepository {
        return PostRepoImpl(apiService)
    }

}