package com.example.detail.di

import com.example.detail.view.DetailViewModel
import com.example.detail.view.profile.ProfileViewModel
import com.example.domain.interactors.AlbumInteractors
import com.example.domain.interactors.CommentInteractors
import com.example.domain.interactors.PhotoInteractors
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class DetailPageModule {
    @Provides
    @Singleton
    fun provideDetail(commentInteractors: CommentInteractors)
            : DetailViewModel {
        return DetailViewModel(commentInteractors)
    }

    @Provides
    @Singleton
    fun provideProfile(albumInteractors: AlbumInteractors, photoInteractors: PhotoInteractors)
            : ProfileViewModel {
        return ProfileViewModel(albumInteractors, photoInteractors)
    }
}