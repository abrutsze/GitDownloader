package com.github.network.entities

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class UserItemResponse(
    val id: Int?,
    val login: String?,
    @SerialName("avatar_url")
    val avatarUrl: String?,
    val url: String?,
    @SerialName("repos_url")
    val reposUrl: String?
)
