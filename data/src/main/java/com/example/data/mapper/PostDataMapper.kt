package com.example.data.mapper

import com.example.data.dto.PostDataDto
import com.example.domain.entities.PostEntites
import com.example.data.dto.UserDataDto
import com.example.domain.entities.UserDataEntites

fun List<PostDataDto>.mapdata(): List<PostEntites> {
    return this.map { postDataDto ->
        PostEntites(
            postDataDto.userId,
            postDataDto.id,
            postDataDto.title,
            postDataDto.body,
            UserDataEntites(
                postDataDto.user?.id ?: 0,
                postDataDto.user?.username ?: "",
                postDataDto.user?.website ?: "",
                postDataDto.user?.phone ?: "",
                UserDataEntites.Address(
                    postDataDto.user?.address?.street ?: "",
                    postDataDto.user?.address?.city ?: "",
                )
            )

        )
    }
}

fun List<UserDataDto>.maps(): List<UserDataEntites> {
    return this.map { userDataDto ->
        UserDataEntites(
            userDataDto.id ?: 0,
            userDataDto.name ?: "",
            userDataDto.username ?: "",
            userDataDto.email ?: "",
            UserDataEntites.Address(
                userDataDto.address?.street ?: "",
                userDataDto.address?.city ?: "",
            ),
            userDataDto.phone ?: "",
            userDataDto.website ?: "",
            UserDataEntites.Company(
                userDataDto.company.name ?: "",
            )
        )
    }
}
