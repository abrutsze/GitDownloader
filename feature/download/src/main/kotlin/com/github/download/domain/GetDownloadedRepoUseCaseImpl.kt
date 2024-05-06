package com.github.download.domain

import com.github.dispatchers.api.DispatchersProvider
import com.github.download.data.DownloadedRepository
import com.github.download.model.UiDownload
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flowOn
import org.koin.core.annotation.Factory

interface GetDownloadedRepoUseCase {
    operator fun invoke(): Flow<List<UiDownload>>
}

@Factory
internal class GetDownloadedRepoUseCaseImpl(
    private val downloadedRepository: DownloadedRepository,
    private val dispatcher: DispatchersProvider
) : GetDownloadedRepoUseCase {

    override operator fun invoke():  Flow<List<UiDownload>> {
        return downloadedRepository.downloadedRepos().flowOn(dispatcher.io)
    }
}
