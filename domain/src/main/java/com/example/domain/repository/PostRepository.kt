package com.example.domain.repository

import com.example.domain.entities.*

interface PostRepository {
    suspend fun getAllPosts(): List<PostEntites>
    suspend fun getUsers(): List<UserDataEntites>
    suspend fun getComments(postId: Int?): List<CommentEntites>
    suspend fun getAlbums(userId: Int?): List<AlbumDataEntities>
    suspend fun getPhotos(): List<PhotoDataEntities>
}