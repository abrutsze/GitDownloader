package com.github.repository.domain

import com.github.dispatchers.api.DispatchersProvider
import com.github.repository.data.RepoRepository
import com.github.repository.model.UiRepo
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flowOn
import org.koin.core.annotation.Factory

interface SaveDownloadedRepoUseCase {
    operator fun invoke(branch:String, repo:UiRepo): Flow<Unit>
}

@Factory
internal class SaveDownloadedRepoUseCaseImpl(
    private val repoRepository: RepoRepository,
    private val dispatcher: DispatchersProvider
) : SaveDownloadedRepoUseCase {

    override operator fun invoke(branch:String, repo: UiRepo): Flow<Unit> {
        return repoRepository.saveRepo(branch, repo).flowOn(dispatcher.io)
    }
}
