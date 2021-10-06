package com.example.data.mapper

import com.example.data.dto.CommentDataDto
import com.example.domain.entities.CommentEntites

fun List<CommentDataDto>.maps() : List<CommentEntites> {
    return this.map { commentDataDto ->
        CommentEntites(
            commentDataDto.pathId ?: 0,
            commentDataDto.id ?: 0,
            commentDataDto.name ?: "",
            commentDataDto.email ?: "",
            commentDataDto.body ?: ""

        )
    }
}