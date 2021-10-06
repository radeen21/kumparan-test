package com.example.domain.entities

import java.io.Serializable

data class CommentEntites(
    val pathId: Int = 0,
    val id: Int = 0,
    val name: String = "",
    val email: String = "",
    val body: String = ""
) : Serializable