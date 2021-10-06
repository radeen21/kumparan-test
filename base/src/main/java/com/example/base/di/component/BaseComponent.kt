package com.example.base.di.component

import android.content.Context
import com.example.domain.repository.PostRepository

interface BaseComponent {
    fun repository(): PostRepository
}