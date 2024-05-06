package com.github.download.data

import com.github.download.model.UiDownload
import kotlinx.coroutines.flow.Flow

interface DownloadedRepository {
    fun downloadedRepos(): Flow<List<UiDownload>>
}
