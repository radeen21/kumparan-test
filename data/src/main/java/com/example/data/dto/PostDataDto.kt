package com.example.data.dto

import com.google.gson.annotations.SerializedName

data class PostDataDto(
    @SerializedName("userId")
    val userId: Int = 0,
    @SerializedName("id")
    val id: Int = 0,
    @SerializedName("title")
    val title: String = "",
    @SerializedName("body")
    val body: String = "",
    @SerializedName("user")
    var user: UserDataDto? = null
)