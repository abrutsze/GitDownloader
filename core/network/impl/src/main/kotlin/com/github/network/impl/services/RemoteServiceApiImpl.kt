package com.github.network.impl.services

import com.github.network.api.RemoteServiceApi
import com.github.network.entities.BranchesItemResponse
import com.github.network.entities.GitHubResponse
import com.github.network.entities.RepoItemResponse
import com.github.network.entities.UserItemResponse
import com.github.network.impl.extention.emitFlow
import io.ktor.client.HttpClient
import io.ktor.client.call.body
import io.ktor.client.request.get
import io.ktor.client.request.parameter
import io.ktor.http.ContentType
import io.ktor.http.appendPathSegments
import io.ktor.http.contentType
import io.ktor.http.path
import kotlinx.coroutines.flow.Flow
import org.koin.core.annotation.Single

@Single
class RemoteServiceApiImpl(private val httpClient: HttpClient) : RemoteServiceApi {

    companion object {
        private const val userUrl = "search/users"
    }

    override suspend fun getSearchUsers(
        query: String,
        limit: Int,
        page: Int
    ): GitHubResponse<UserItemResponse> =
        httpClient.get(userUrl) {
            contentType(ContentType.Application.Json)
            parameter("q", query)
            parameter("pre_page", limit)
            parameter("page", page)
        }.body()

    override  fun getRepos(name:String): Flow<List<RepoItemResponse>> = emitFlow {
        httpClient.get {
            url {
                path("users/$name/repos")
            }
        }.body()
    }

    override fun getBranches(fullName: String): Flow<List<BranchesItemResponse>> = emitFlow {
        httpClient.get {
            url {
                path("repos/$fullName/branches")
            }
        }.body()
    }
}
