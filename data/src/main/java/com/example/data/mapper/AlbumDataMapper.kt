package com.example.data.mapper

import com.example.data.dto.AlbumDataDto
import com.example.domain.entities.AlbumDataEntities

fun List<AlbumDataDto>.maps(): List<AlbumDataEntities> {
    return this.map { albumDataEntities ->
        AlbumDataEntities(
            albumDataEntities.userId ?: 0,
            albumDataEntities.id ?: 0,
            albumDataEntities.title ?: "",
        )
    }
}