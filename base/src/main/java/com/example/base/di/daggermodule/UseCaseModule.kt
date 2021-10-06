package com.example.base.di.daggermodule

import com.example.domain.interactors.PostInteractors
import com.example.domain.repository.PostRepository
import dagger.Module
import dagger.Provides

@Module
class UseCaseModule {

    @Provides
    fun providePostDataInteractor(
        postRepository : PostRepository
    ): PostInteractors {
        return PostInteractors(postRepository)
    }

}