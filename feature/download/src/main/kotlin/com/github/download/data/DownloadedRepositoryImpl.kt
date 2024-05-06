package com.github.download.data

import com.github.database.DownloadDao
import com.github.download.model.UiDownload
import com.github.download.model.toUiDownload
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import org.koin.core.annotation.Single

@Single
class DownloadedRepositoryImpl(
    private val downloadDao: DownloadDao
) : DownloadedRepository {

    override fun downloadedRepos(): Flow<List<UiDownload>> {
       return downloadDao.getDownloads().map {
            it.map { it.toUiDownload() }
        }
    }

}
