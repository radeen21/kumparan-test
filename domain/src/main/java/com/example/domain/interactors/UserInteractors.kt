package com.example.domain.interactors

import com.example.domain.CoroutineUseCase
import com.example.domain.repository.PostRepository
import com.example.domain.entities.UserDataEntites
import javax.inject.Inject

class UserInteractors @Inject constructor(
    private val repository: PostRepository
) : CoroutineUseCase<List<UserDataEntites>, Void>() {
    override suspend fun build(param: Void?): List<UserDataEntites> =
        repository.getUsers()
}