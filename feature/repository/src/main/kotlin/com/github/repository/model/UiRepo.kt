package com.github.repository.model

import com.github.network.entities.RepoItemResponse
import com.github.utils.orDefault

data class UiRepo(
    val id: Int,
    val name: String,
    val ownerLogin: String,
    val ownerAvatarUrl: String,
    val fullName: String,
    val branches: List<String>,
    val downloadsUrl: String,
    val url: String,
    val htmlUrl: String,
    val branchesUrl: String,
)

fun RepoItemResponse.toUiRepo(): UiRepo {
    return UiRepo(
        id = id.orDefault(),
        name = name.orEmpty(),
        ownerLogin = owner?.login.orEmpty(),
        ownerAvatarUrl = owner?.avatarUrl.orEmpty(),
        fullName = fullName.orEmpty(),
        branches = emptyList(),
        downloadsUrl = downloadsUrl.orEmpty(),
        url = url.orEmpty(),
        htmlUrl = htmlUrl.orEmpty(),
        branchesUrl = branchesUrl.orEmpty(),
    )
}