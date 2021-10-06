package com.example.data.repoimpl

import com.example.data.apis.ApiService
import com.example.data.mapper.mapdata
import com.example.data.mapper.maps
import com.example.domain.entities.*
import com.example.domain.repository.PostRepository
import javax.inject.Inject

class PostRepoImpl @Inject constructor(private val api: ApiService) : PostRepository {

    override suspend fun getAllPosts(): List<PostEntites> =
            api.getAllPosts().mapdata()

    override suspend fun getUsers(): List<UserDataEntites> =
        api.getUsers().maps()

    override suspend fun getComments(postId: Int?): List<CommentEntites> =
        api.getComment(postId).maps()

    override suspend fun getAlbums(userId: Int?): List<AlbumDataEntities> =
        api.getUserAlbums(userId).maps()

    override suspend fun getPhotos(): List<PhotoDataEntities> =
        api.getPhotos().maps()
}


