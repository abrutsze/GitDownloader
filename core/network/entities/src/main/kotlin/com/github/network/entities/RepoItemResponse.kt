package com.github.network.entities

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class RepoItemResponse(
    val id: Int?,
    val name: String?,
    val owner: OwnerResponse?,
    @SerialName("full_name")
    val fullName: String?,
    @SerialName("downloads_url")
    val downloadsUrl: String?,
    val url: String?,
    @SerialName("html_url")
    val htmlUrl: String?,
    @SerialName("branches_url")
    val branchesUrl: String?
)


