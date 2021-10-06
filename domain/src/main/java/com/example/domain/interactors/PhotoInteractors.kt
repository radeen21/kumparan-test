package com.example.domain.interactors

import com.example.domain.CoroutineUseCase
import com.example.domain.entities.PhotoDataEntities
import com.example.domain.repository.PostRepository
import javax.inject.Inject

class PhotoInteractors  @Inject constructor(
    private val repository: PostRepository
) : CoroutineUseCase<List<PhotoDataEntities>, Void>() {
    override suspend fun build(param: Void?): List<PhotoDataEntities> =
        repository.getPhotos()
}