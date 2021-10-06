package com.example.domain.interactors

import com.example.domain.CoroutineUseCase
import com.example.domain.entities.AlbumDataEntities
import com.example.domain.repository.PostRepository
import javax.inject.Inject

class AlbumInteractors @Inject constructor(
    private val repository: PostRepository
) : CoroutineUseCase<List<AlbumDataEntities>, AlbumInteractors.Params>() {
    override suspend fun build(param: Params?): List<AlbumDataEntities> =
        repository.getAlbums(param?.userId)

    data class Params(val userId: Int)
}