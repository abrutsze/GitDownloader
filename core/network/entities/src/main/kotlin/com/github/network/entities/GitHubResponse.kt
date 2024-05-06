package com.github.network.entities

import kotlinx.serialization.Serializable

@Serializable
data class GitHubResponse<T : Any>(
    val items: List<T>
)
