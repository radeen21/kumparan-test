package com.example.detail.di

import com.example.base.di.component.BaseComponent
import com.example.base.di.component.BaseNavigationComponent
import com.example.detail.view.DetailFragment
import com.example.detail.view.profile.ProfileFragment
import dagger.Component

@Component(
    dependencies = [
        BaseComponent::class,
        BaseNavigationComponent::class
    ]
)
interface DetailPageComponent {
    fun inject(detailFragment: DetailFragment)
    fun inject(profileFragment: ProfileFragment)
}