package com.example.kumparantest.di

import com.example.base.di.component.BaseComponent
import com.example.base.di.daggermodule.UseCaseModule
import com.example.data.internal.di.DataModule
import com.example.detail.di.DetailPageModule
import com.example.home.di.HomePageModule
import com.example.kumparantest.AndroidApplication
import com.example.kumparantest.ApplicationModule
import dagger.Component
import javax.inject.Singleton


@Singleton
@Component(
    modules = [
        ApplicationModule::class,
        ApiModule::class,
        DataModule::class,
        UseCaseModule::class,
        HomePageModule::class,
        DetailPageModule::class
    ]
)
interface ApplicationComponent : BaseComponent {
    fun inject(androidApplication: AndroidApplication)
}