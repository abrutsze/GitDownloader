package com.github.repository.domain

import com.github.dispatchers.api.DispatchersProvider
import com.github.repository.data.RepoRepository
import com.github.repository.model.UiRepo
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flowOn
import org.koin.core.annotation.Factory

interface RepoUseCase {
    operator fun invoke(name:String): Flow<List<UiRepo>>
}

@Factory
internal class RepoUseCaseImpl(
    private val repoRepository: RepoRepository,
    private val dispatcher: DispatchersProvider
) : RepoUseCase {

    override operator fun invoke(name: String): Flow<List<UiRepo>> {
        return repoRepository.getRepos(name).flowOn(dispatcher.io)
    }
}
