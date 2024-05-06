package com.github.download.model

import com.github.database.entity.DownloadEntity

data class UiDownload(
    val id: String,
    val name: String,
    val ownerLogin: String,
    val ownerAvatarUrl: String,
    val fullName: String,
    val branches: String,
    val downloadsUrl: String,
    val url: String,
    val htmlUrl: String,
    val branchesUrl: String,
)

fun DownloadEntity.toUiDownload(): UiDownload {
    return UiDownload(
        id = id,
        name = name,
        ownerLogin = ownerLogin,
        ownerAvatarUrl = ownerAvatarUrl,
        fullName = fullName,
        branches = branches,
        downloadsUrl = downloadsUrl,
        url = url,
        htmlUrl = htmlUrl,
        branchesUrl = branchesUrl,
    )
}
