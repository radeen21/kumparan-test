package com.example.domain.entities

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize
import java.io.Serializable

@Parcelize
data class UserDataEntites(
    val id: Int = 0,
    val name: String = "",
    val username: String = "",
    val email: String = "",
    val address: Address = Address(),
    val phone: String = "",
    val website: String = "",
    val company: Company = Company()
): Parcelable {
    @Parcelize
    data class Address(
        val street: String = "",
        val suite: String = "",
        val city: String = "",
        val zipcode: String = "",
        val geo: Geo = Geo(),
    ): Parcelable {
        @Parcelize
        data class Geo(
            val lat: String = "",
            val lng: String = ""
        ): Parcelable
    }
    @Parcelize
    data class Company(
        val name: String = "",
        val catchPhrase: String = "",
        val bs: String = ""
    ): Parcelable
}