package com.example.domain.interactors

import com.example.domain.CoroutineUseCase
import com.example.domain.entities.CommentEntites
import com.example.domain.repository.PostRepository
import javax.inject.Inject

class CommentInteractors @Inject constructor(
    private val repository: PostRepository
) : CoroutineUseCase<List<CommentEntites>, CommentInteractors.Params>() {
    override suspend fun build(param: Params?): List<CommentEntites> =
        repository.getComments(param?.postId)

    data class Params(val postId: Int)
}