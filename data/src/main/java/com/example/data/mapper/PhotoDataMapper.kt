package com.example.data.mapper

import com.example.data.dto.PhotoDataDto
import com.example.domain.entities.PhotoDataEntities

fun List<PhotoDataDto>.maps(): List<PhotoDataEntities> {
    return this.map { photoDataEntities ->
        PhotoDataEntities(
            photoDataEntities.albumId ?: 0,
            photoDataEntities.id ?: 0,
            photoDataEntities.title ?: "",
            photoDataEntities.url ?: "",
            photoDataEntities.thumbnailUrl ?: "",
        )
    }
}