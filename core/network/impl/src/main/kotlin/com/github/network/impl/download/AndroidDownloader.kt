package com.github.network.impl.download

import android.app.DownloadManager
import android.content.Context
import android.os.Environment
import androidx.core.net.toUri
import com.github.network.api.Downloader
import com.github.network.impl.services.UrlProvider
import org.koin.core.annotation.Single

private const val ZIP_FORMAT = "zipball"
private const val AUTHORIZATION = "Authorization"

@Single
class AndroidDownloader(
    context: Context,
    private val provider: UrlProvider
) : Downloader {
    private val downloadManager = context.getSystemService(DownloadManager::class.java)

    override fun downloadFile(branchName: String, fullName: String): Long {
        val url = provider.getProtocol()+"://" + provider.getUrl()+ "/repos/" + fullName + "/"+ ZIP_FORMAT + "/"+branchName
        val request = DownloadManager.Request(url.toUri())
            .setNotificationVisibility(DownloadManager.Request.VISIBILITY_VISIBLE_NOTIFY_COMPLETED)
            .addRequestHeader(AUTHORIZATION, provider.getToken())
            .setDestinationInExternalPublicDir(Environment.DIRECTORY_DOWNLOADS, "$fullName$branchName.zip")
        return downloadManager.enqueue(request)
    }
}