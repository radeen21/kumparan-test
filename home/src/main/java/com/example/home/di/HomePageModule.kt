package com.example.home.di

import android.content.Context
import com.example.domain.interactors.PostInteractors
import com.example.domain.interactors.UserInteractors
import com.example.home.view.PostViewModel
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class HomePageModule {

    @Provides
    @Singleton
    fun provideSearch(interactor: PostInteractors, userInteractors: UserInteractors)
    : PostViewModel {
        return PostViewModel(interactor, userInteractors)
    }

}