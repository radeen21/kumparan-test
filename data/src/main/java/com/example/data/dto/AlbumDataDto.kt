package com.example.data.dto

import com.google.gson.annotations.SerializedName
import java.io.Serializable

data class AlbumDataDto(
    @SerializedName("userId")
    val userId: Int = 0,
    @SerializedName("id")
    val id: Int = 0,
    @SerializedName("title")
    val title: String = ""

) : Serializable