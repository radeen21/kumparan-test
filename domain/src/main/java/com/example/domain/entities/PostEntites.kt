package com.example.domain.entities

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class PostEntites (
    val userId: Int = 0,
    val id: Int = 0,
    val title: String = "",
    val body: String = "",
    var user: UserDataEntites? = null,
) : Parcelable