package com.example.domain.interactors

import com.example.domain.CoroutineUseCase
import com.example.domain.entities.PostEntites
import com.example.domain.repository.PostRepository
import javax.inject.Inject

class PostInteractors @Inject constructor(
        private val repository: PostRepository
) : CoroutineUseCase<List<PostEntites>, Void>() {
    override suspend fun build(param: Void?): List<PostEntites> =
            repository.getAllPosts()
}