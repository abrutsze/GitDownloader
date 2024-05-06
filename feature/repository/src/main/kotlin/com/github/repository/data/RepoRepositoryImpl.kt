package com.github.repository.data

import com.github.database.DownloadDao
import com.github.database.entity.DownloadEntity
import com.github.network.api.RemoteServiceApi
import com.github.repository.model.UiRepo
import com.github.repository.model.toUiRepo
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.flow.flatMapConcat
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.map
import org.koin.core.annotation.Single

@Single
class RepoRepositoryImpl(
    private val remoteServiceApi: RemoteServiceApi,
    private val downloadDao: DownloadDao
) : RepoRepository {

    override fun getRepos(name: String): Flow<List<UiRepo>> = remoteServiceApi.getRepos(name).map {
        it.map { it.toUiRepo() }
    }.flatMapConcat {
        combine(it.map { repo ->
            remoteServiceApi.getBranches(repo.fullName).map {
                repo.copy(branches = it.map { it.name.orEmpty() })
            }

        }) {
            it.toList()
        }
    }

    override fun saveRepo(branch: String, repo: UiRepo): Flow<Unit> = flow {
        val uniqueId = repo.id.toString() + branch
        val entity = DownloadEntity(
            uniqueId,
            repo.name,
            repo.ownerLogin,
            repo.ownerAvatarUrl,
            repo.fullName,
            branch,
            repo.downloadsUrl,
            repo.url,
            repo.htmlUrl,
            repo.branchesUrl
        )
        emit(downloadDao.insert(entity))
    }
}
