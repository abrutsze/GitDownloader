package com.github.network.api

import com.github.network.entities.BranchesItemResponse
import com.github.network.entities.GitHubResponse
import com.github.network.entities.RepoItemResponse
import com.github.network.entities.UserItemResponse
import kotlinx.coroutines.flow.Flow

interface RemoteServiceApi {
    suspend fun getSearchUsers(
        query: String,
        limit: Int,
        page: Int
    ): GitHubResponse<UserItemResponse>

    fun getRepos(name: String): Flow<List<RepoItemResponse>>

    fun getBranches(fullName: String): Flow<List<BranchesItemResponse>>
}
