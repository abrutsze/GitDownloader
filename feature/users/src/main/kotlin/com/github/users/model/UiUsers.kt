package com.github.users.model

import com.github.network.entities.UserItemResponse
import com.github.utils.orDefault

data class UiUsers(
    val id: Int,
    val login: String,
    val avatarUrl: String,
    val url: String,
    val reposUrl: String
)

fun UserItemResponse.toUiUsers(): UiUsers {
    return UiUsers(
        id.orDefault(), login.orEmpty(), avatarUrl.orEmpty(), url.orEmpty(), reposUrl.orEmpty()
    )
}