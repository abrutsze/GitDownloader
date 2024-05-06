package com.github.network.entities

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class OwnerResponse(
    val login: String?,
    @SerialName("avatar_url")
    val avatarUrl: String?,
    val id: Int?,
)