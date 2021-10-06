package com.example.data.apis

import com.example.data.dto.*
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path

interface ApiService {

    @GET("posts")
    suspend fun getAllPosts(): List<PostDataDto>

    @GET("/users")
    suspend fun getUsers(): List<UserDataDto>

    @GET("posts/{postId}/comments")
    suspend fun getComment(@Path("postId") postId: Int?): List<CommentDataDto>

    @GET("/users/{userId}/albums")
    suspend fun getUserAlbums(
        @Path("userId") userId: Int?
    ): List<AlbumDataDto>

    @GET("/photos")
    suspend fun getPhotos(): List<PhotoDataDto>
}