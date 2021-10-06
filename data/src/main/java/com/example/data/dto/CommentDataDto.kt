package com.example.data.dto

import com.google.gson.annotations.SerializedName
import java.io.Serializable

data class CommentDataDto(
    @SerializedName("pathId")
    val pathId: Int = 0,
    @SerializedName("id")
    val id: Int = 0,
    @SerializedName("name")
    val name: String = "",
    @SerializedName("email")
    val email: String = "",
    @SerializedName("body")
    val body: String = ""
) : Serializable
