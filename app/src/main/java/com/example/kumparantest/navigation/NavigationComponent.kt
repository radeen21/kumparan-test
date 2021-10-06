package com.example.kumparantest.navigation

import com.example.base.di.component.BaseNavigationComponent
import dagger.Component
import javax.inject.Singleton


@Singleton
@Component(modules = [NavigationModule::class])
interface NavigationComponent :
    BaseNavigationComponent {}