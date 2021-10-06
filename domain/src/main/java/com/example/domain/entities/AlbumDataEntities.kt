package com.example.domain.entities

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class AlbumDataEntities(
    val userId: Int = 0,
    val id: Int = 0,
    val title: String = "",
    var photo: MutableList<PhotoDataEntities?> = mutableListOf(),
) : Parcelable
